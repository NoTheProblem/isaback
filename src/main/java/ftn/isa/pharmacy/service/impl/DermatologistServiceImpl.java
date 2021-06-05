package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.dto.WorkingHoursDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.impl.DermatologistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PharmacyMapperImpl;
import ftn.isa.pharmacy.mapper.impl.WorkingHoursMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.DermatologistRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.WorkingHoursRepository;
import ftn.isa.pharmacy.service.DermatologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DermatologistServiceImpl implements DermatologistService  {

    private final DermatologistRepository dermatologistRepository;
    private final DermatologistMapperImpl dermatologistMapper;
    private final PharmacyMapperImpl pharmacyMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final WorkingHoursMapperImpl workingHoursMapper;
    private final WorkingHoursRepository workingHoursRepository;

    @Autowired
    public DermatologistServiceImpl(DermatologistRepository dermatologistRepository, DermatologistMapperImpl dermatologistMapper, PharmacyMapperImpl pharmacyMapper, PharmacyAdminRepository pharmacyAdminRepository, WorkingHoursMapperImpl workingHoursMapper, WorkingHoursRepository workingHoursRepository) {
        this.dermatologistRepository = dermatologistRepository;
        this.dermatologistMapper = dermatologistMapper;
        this.pharmacyMapper = pharmacyMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.workingHoursMapper = workingHoursMapper;
        this.workingHoursRepository = workingHoursRepository;
    }

    @Override
    public List<DermatologistDto> getAll() {
        List<Dermatologist> dermatologists = dermatologistRepository.findAll();
        List<DermatologistDto> dermatologistDtos = new Stack<>();
        for (Dermatologist dermatologist: dermatologists) {
            DermatologistDto dermatologistDto = dermatologistMapper.entity2Bean(dermatologist);
            Set<Pharmacy> pharmacySet =  dermatologist.getPharmacys();
            List<PharmacyDto> pharmacyDtos = new Stack<>();
            for (Pharmacy pharmacy:pharmacySet) {
                PharmacyDto pharmacyDto = pharmacyMapper.entity2Bean(pharmacy);
                pharmacyDtos.add(pharmacyDto);
            }
            dermatologistDto.setPharmacys(pharmacyDtos);
            dermatologistDtos.add(dermatologistDto);
        }
        return dermatologistDtos;
    }

    @Override
    public Collection<DermatologistDto> getAllDermatologistsCandidates() {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Collection<Dermatologist> dermatologists = dermatologistRepository.findAll();
        Collection<Dermatologist> workingDermatologists = pharmacy.getDermatologists();

        dermatologists.removeAll(workingDermatologists);
        Collection<DermatologistDto> dermatologistDtos =  new HashSet<>();
        for (Dermatologist dermatologist:dermatologists) {
            DermatologistDto dermatologistDto = dermatologistMapper.entity2Bean(dermatologist);
            //List<WorkingHoursDTO> workingHoursDTO = (List<WorkingHoursDTO>) workingHoursMapper.entity2Bean(dermatologist.getWorkingHours());
            List<WorkingHoursDTO> workingHoursDTO = (List<WorkingHoursDTO>) workingHoursMapper.entity2Bean(workingHoursRepository.findAllByDermatologist(dermatologist));
            dermatologistDto.setWorkingHours(workingHoursDTO);
            dermatologistDtos.add(dermatologistDto);
        }
        return dermatologistDtos;
    }

    @Override
    public void addToPharmacy(DermatologistDto dermatologistDto) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Dermatologist dermatologist = dermatologistRepository.getOne(dermatologistDto.getId());
        List<WorkingHoursDTO> workingHoursDTOS = dermatologistDto.getWorkingHours();
        WorkingHoursDTO workingHoursDTO = workingHoursDTOS.get(0);
        System.out.println("debag");
        System.out.println(workingHoursDTOS.size());
        System.out.println(workingHoursDTOS.get(0));
        WorkingHours newWorkingHours = workingHoursMapper.bean2Entity(workingHoursDTOS.get(0));
        System.out.println(newWorkingHours);
        Set<WorkingHours> existingWorkingHours = workingHoursRepository.findAllByDermatologistOrderById(dermatologist);
        String workDay = newWorkingHours.getWorkDay();
        Date startTime = new Date();
        startTime.setTime(workingHoursDTO.getStartTime().getTime());
        Date endTime = new Date();
        endTime.setTime(workingHoursDTO.getEndTime().getTime());

        switch (workDay){
            case "Svaki dan":
                for (WorkingHours wk: existingWorkingHours){
                    CheckOverlapTime(startTime, endTime, wk);
                }
                break;
            case "Vikend":
                for (WorkingHours wk: existingWorkingHours){
                    if(wk.getWorkDay().equals("Vikend") || wk.getWorkDay().equals("Subota") || wk.getWorkDay().equals("Nedelja")){
                        CheckOverlapTime(startTime, endTime, wk);
                    }
                }
            case "Radni dan":
                for (WorkingHours wk: existingWorkingHours) {
                    if (!wk.getWorkDay().equals("Vikend") && !wk.getWorkDay().equals("Subota") && !wk.getWorkDay().equals("Nedelja")) {
                        CheckOverlapTime(startTime, endTime, wk);
                    }
                }
                break;
            default:
                for (WorkingHours wk: existingWorkingHours) {
                    if (wk.getWorkDay().equals(workDay)) {
                        CheckOverlapTime(startTime, endTime, wk);
                    }
                }
        }
        newWorkingHours.setPharmacy(pharmacyAdmin.getPharmacy());
        newWorkingHours.setDermatologist(dermatologist);
        existingWorkingHours.add(newWorkingHours);
        dermatologist.setWorkingHours(existingWorkingHours);
        dermatologistRepository.save(dermatologist);
        workingHoursRepository.save(newWorkingHours);
    }

    private void CheckOverlapTime(Date startTime, Date endTime, WorkingHours wk) {
        Date start = new Date();
        start.setTime(wk.getStartTime().getTime());
        Date end = new Date();
        end.setTime(wk.getEndTime().getTime());
        if (startTime.after(start) && startTime.before(end)) {
            throw new ResourceConflictException(1l,"Preklapa se termin!");
        }
        if (endTime.after(start) && endTime.before(end)) {
            throw new ResourceConflictException(1l,"Preklapa se termin!");
        }
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
