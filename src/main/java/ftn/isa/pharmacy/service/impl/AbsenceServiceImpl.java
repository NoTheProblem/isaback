package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.AbsenceDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.impl.AbsenceMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.AbsenceRequestRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.service.AbsenceService;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRequestRepository absenceRequestRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final AbsenceMapperImpl absenceMapper;
    private final MailServiceImpl mailService;

    @Autowired
    public AbsenceServiceImpl(AbsenceRequestRepository absenceRequestRepository,
                              PharmacyAdminRepository pharmacyAdminRepository,AbsenceMapperImpl absenceMapper,
                              MailServiceImpl mailService) {
        this.absenceRequestRepository = absenceRequestRepository;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.absenceMapper = absenceMapper;
        this.mailService = mailService;
    }


    @Override
    public Collection<AbsenceRequest> getAllDermatologistRequests() {
        return absenceRequestRepository.getAllByTypeOfEmployeeAndStatus("ROLE_DERMATOLOGIST","nov");
    }
    @Override
    public Collection<AbsenceRequest> getAllPharmacistRequests() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        return absenceRequestRepository.getAllByPharmacyAndTypeOfEmployeeAndStatus(pharmacyAdmin.getPharmacy(),"ROLE_PHARMACIST","nov");
    }

    @Override
    @Transactional(readOnly = false)
    public AbsenceRequest getAbsenceByID(Long id) {
        return absenceRequestRepository.findOneById(id);
    }

    @Transactional(readOnly = false)
    public AbsenceRequest getAbsenceForUpdate(Long id){
        AbsenceRequest absenceRequest = absenceRequestRepository.findOneById(id);
        return absenceRequest;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AbsenceRequest answer(AbsenceDTO absenceDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        AbsenceRequest absenceRequest = getAbsenceForUpdate(absenceDTO.getId());
        if(!absenceRequest.getStatus().equals("nov")){
            throw new ResourceConflictException(1l,"Promenjen u medjuvremenu!");
        }
        absenceRequest.setStatus(absenceDTO.getStatus());
        if(absenceDTO.getStartDate().equals("Odbijeno")){
            absenceRequest.setAnswerText(absenceDTO.getAnswerText());
        }
        absenceRequest.setAdminId(pharmacyAdmin.getId());
        absenceRequestRepository.save(absenceRequest);
        mailService.absenceDeclinedNotification(absenceRequest);
        return absenceRequest;
    }

    @Override
    public Collection<AbsenceRequest> getByEmployeeId(Long id) {
        Date date = new Date();
        return absenceRequestRepository.findAllByEmployeeIdAndStatusIsLikeAndStartDateAfterOrEmployeeIdAndStatusIsLikeAndEndDateAfter(id,"Odobreno",date,id,"Odobreno", date);
    }


    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            return pharmacyAdmin;
        }
        throw new ResourceConflictException(1l,"Ne postoji administrator apoteke!");
    }
}
