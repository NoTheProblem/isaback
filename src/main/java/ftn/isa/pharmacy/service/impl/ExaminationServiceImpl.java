package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.impl.ExaminationMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;
    private final ExaminationMapperImpl examinationMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final DermatologistRepository dermatologistRepository;
    private final WorkingHoursRepository workingHoursRepository;
    private final AbsenceRequestRepository absenceRequestRepository;


    @Autowired
    public ExaminationServiceImpl(ExaminationRepository examinationRepository, ExaminationMapperImpl examinationMapper, PharmacyAdminRepository pharmacyAdminRepository, DermatologistRepository dermatologistRepository, WorkingHoursRepository workingHoursRepository, AbsenceRequestRepository absenceRequestRepository) {
        this.examinationRepository = examinationRepository;
        this.examinationMapper = examinationMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.workingHoursRepository = workingHoursRepository;
        this.absenceRequestRepository = absenceRequestRepository;
    }

    @Override
    public List<Examination> getAllFree(Boolean free) {
        return examinationRepository.findAllByIsFree(free);
    }

    @Override
    public boolean addExamination(ExaminationDto examinationDto) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        List<AbsenceRequest> absenceRequests = absenceRequestRepository.
                findAllByEmployeeIdAndStartDateBeforeAndEndDateAfterAndStatusIsLike(examinationDto.getDermatologistId(),examinationDto.getDate(),examinationDto.getDate(), "Odobreno");
        if(absenceRequests.size()!=0){
            throw new ResourceConflictException(1L,"Odsustvo!");
        }
        Date today = new Date();
        if(examinationDto.getDate().before(today)){
            throw new ResourceConflictException(1L,"Zakazivanje u proslost!");
        }
        String time = examinationDto.getTime();
        Dermatologist dermatologist = dermatologistRepository.getOne(examinationDto.getDermatologistId());
        Examination examination = examinationMapper.bean2Entity(examinationDto);
        Date date = examination.getDate();
        int hours = Integer.parseInt(time.substring(0,2));
        date.setHours(hours);
        int minutes = Integer.parseInt(time.substring(3));
        date.setMinutes(minutes);
        examination.setDate(date);
        Date endDate = new Date();
        endDate.setTime(date.getTime());
        endDate.setMinutes(date.getMinutes()+examination.getDurationMinutes());
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        WorkingHours wk = workingHoursRepository.getWorkingHoursByPharmacyAndDermatologist(pharmacy,dermatologist);
        Time startShift = wk.getStartTime();
        Time endShift = wk.getEndTime();
        Date dateStartShift = new Date();
        dateStartShift.setTime(date.getTime());
        dateStartShift.setMinutes(startShift.getMinutes());
        dateStartShift.setHours(startShift.getHours());
        Date dateEndShift = new Date();
        dateEndShift.setTime(date.getTime());
        dateEndShift.setMinutes(endShift.getMinutes());
        dateEndShift.setHours(endShift.getHours());
        if(date.after(dateEndShift) || endDate.before(dateStartShift) || endDate.after(dateEndShift)){
            throw new ResourceConflictException(1L,"Nije u radnom vremenu!");
        }
        Date startDate = new Date();
        startDate.setTime(date.getTime());
        startDate.setHours(1);
        Date nextDay =  new Date();
        nextDay.setTime(date.getTime());
        nextDay.setHours(23);
        List<Examination> examinationsOnThatDay = examinationRepository.findAllByDateBetween(startDate,nextDay);
        for (Examination exa: examinationsOnThatDay) {
            Date exaDate = exa.getDate();
            Date exaStart =  new Date();
            exaStart.setTime(exaDate.getTime());
            Date exaEnd =  new Date();
            exaEnd.setTime(exaDate.getTime());
            exaEnd.setMinutes(exa.getDate().getHours()+ exa.getDurationMinutes());
            if (date.after(exaStart) && date.before(exaEnd)){
                throw new ResourceConflictException(1L,"Preklapa se sa terminom!");
            }
            if(endDate.after(exaStart) && endDate.before(exaEnd)){
                throw new ResourceConflictException(1L,"Preklapa se sa terminom!");
            }
        }
        examination.setPharmacy(pharmacy);
        examination.setDermatologist(dermatologist);
        examination.setFree(true);
        examinationRepository.save(examination);
        return  true;
    }

    @Override
    public Collection<Examination> getByDermaIdAndDateForPhaAdmin(Long id, String date) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Dermatologist dermatologist = dermatologistRepository.getOne(id);
        return examinationRepository.customByPharmacyDermatologistAndDate(pharmacyAdmin.getPharmacy().getId(),dermatologist.getId(),date);
    }


    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            return pharmacyAdminOptional.get();
        }
        throw new ResourceConflictException(1L,"Ne postoji administrator apoteke!");
    }
}
