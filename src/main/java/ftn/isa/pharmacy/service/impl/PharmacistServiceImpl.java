package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.impl.PharmacistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.PharmacyMapperImpl;
import ftn.isa.pharmacy.mapper.impl.WorkingHoursPharmacistMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.AuthorityService;
import ftn.isa.pharmacy.service.PharmacistService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    private final PharmacistRepository pharmacistRepository;
    private final DermatologistRepository dermatologistRepository;
    private final PharmacistMapperImpl pharmacistMapper;
    private final PharmacyMapperImpl pharmacyMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final PharmacyRepository pharmacyRepository;
    private final WorkingHoursPharmacistMapperImpl workingHoursPharmacistMapper;
    private final WorkingHoursPharmacistRepository workingHoursPharmacistRepository;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;

    public PharmacistServiceImpl(PharmacistRepository pharmacistRepository, DermatologistRepository dermatologistRepository, PharmacistMapperImpl pharmacistMapper, PharmacyMapperImpl pharmacyMapper, PharmacyAdminRepository pharmacyAdminRepository, PharmacyRepository pharmacyRepository, WorkingHoursPharmacistMapperImpl workingHoursPharmacistMapper, WorkingHoursPharmacistRepository workingHoursPharmacistRepository, AuthorityService authorityService, PasswordEncoder passwordEncoder) {
        this.pharmacistRepository = pharmacistRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacistMapper = pharmacistMapper;
        this.pharmacyMapper = pharmacyMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.workingHoursPharmacistMapper = workingHoursPharmacistMapper;
        this.workingHoursPharmacistRepository = workingHoursPharmacistRepository;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Collection<PharmacistDTO> getAll() {
        Collection<Pharmacist> pharmacists = pharmacistRepository.findAll();
        Collection<PharmacistDTO> pharmacistDTOS = new HashSet<>();
        for (Pharmacist pharmacist: pharmacists) {
            PharmacistDTO pharmacistDTO = pharmacistMapper.entity2Bean(pharmacist);
            pharmacistDTO.setPharmacy(pharmacyMapper.entity2Bean(pharmacist.getPharmacy()));
            pharmacistDTOS.add(pharmacistDTO);
        }
        return pharmacistDTOS;
    }

    @Override
    public Collection<PharmacistDTO> getAllFree() {
        return pharmacistMapper.entity2Bean(pharmacistRepository.findAllByPharmacyIsNull());
    }

    @Override
    public void addToPharmacy(PharmacistDTO pharmacistDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Pharmacist pharmacist = pharmacistRepository.getOne(pharmacistDTO.getId());
        Set<Pharmacist> pharmacistSet = pharmacy.getPharmacists();
        pharmacistSet.add(pharmacist);
        pharmacy.setPharmacists(pharmacistSet);
        pharmacyRepository.save(pharmacy);
        pharmacist.setPharmacy(pharmacy);
        pharmacistRepository.save(pharmacist);
    }

    @Override
    public void registerToPharmacy(PharmacistDTO pharmacistDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Pharmacist pharmacist = pharmacistMapper.bean2Entity(pharmacistDTO);
        WorkingHoursPharmacist workingHoursPharmacist = workingHoursPharmacistMapper.bean2Entity(pharmacistDTO.getWorkingHours());
        pharmacist.setPharmacy(pharmacy);
        workingHoursPharmacist.setPharmacist(pharmacist);
        workingHoursPharmacist.setPharmacy(pharmacy);
        pharmacist.setEnabled(false);

        pharmacist.setPassword(passwordEncoder.encode(pharmacist.getFirstName() + "2021"));
        List<Authority> auth = authorityService.findByName("ROLE_PHARMACIST");
        pharmacist.setAuthorities(auth);
        pharmacistRepository.save(pharmacist);
        Set<Pharmacist> pharmacistSet = pharmacy.getPharmacists();
        pharmacistSet.add(pharmacist);
        pharmacy.setPharmacists(pharmacistSet);
        pharmacyRepository.save(pharmacy);
        workingHoursPharmacistRepository.save(workingHoursPharmacist);
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
