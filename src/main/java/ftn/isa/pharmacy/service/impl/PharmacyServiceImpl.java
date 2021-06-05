package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.dto.WorkingHoursDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.PharmacyMapper;
import ftn.isa.pharmacy.mapper.impl.DermatologistMapperImpl;
import ftn.isa.pharmacy.mapper.impl.ExaminationMapperImpl;
import ftn.isa.pharmacy.mapper.impl.WorkingHoursMapperImpl;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final PatientRepository patientRepository;
    private final PromotionRepository promotionRepository;
    private final MailServiceImpl mailService;
    private final DermatologistMapperImpl dermatologistMapper;
    private final WorkingHoursMapperImpl workingHoursMapper;
    private final WorkingHoursRepository workingHoursRepository;
    private final ExaminationRepository examinationRepository;
    private final ExaminationMapperImpl examinationMapper;
    private final PharmacyMapper pharmacyMapper;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;
    private final DermatologistRepository dermatologistRepository;
    private final PharmacistRepository pharmacistRepository;



    // https://www.vojtechruzicka.com/field-dependency-injection-considered-harmful/#gatsby-focus-wrapper
    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository, PharmacyAdminRepository pharmacyAdminRepository, PatientRepository patientRepository, PromotionRepository promotionRepository, MailServiceImpl mailService, DermatologistMapperImpl dermatologistMapper, WorkingHoursMapperImpl workingHoursMapper, WorkingHoursRepository workingHoursRepository, ExaminationRepository examinationRepository, ExaminationMapperImpl examinationMapper, PharmacyMapper pharmacyMapper, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository, DermatologistRepository dermatologistRepository, PharmacistRepository pharmacistRepository) {
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.patientRepository = patientRepository;
        this.promotionRepository = promotionRepository;
        this.mailService = mailService;
        this.dermatologistMapper = dermatologistMapper;
        this.workingHoursMapper = workingHoursMapper;
        this.workingHoursRepository = workingHoursRepository;
        this.examinationRepository = examinationRepository;
        this.examinationMapper = examinationMapper;
        this.pharmacyMapper = pharmacyMapper;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
        this.dermatologistRepository = dermatologistRepository;
        this.pharmacistRepository = pharmacistRepository;
    }

    @Override
    public List<Pharmacy> getAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy getById(Long id) {
        Optional<Pharmacy> pharmacyOptional =  pharmacyRepository.findById(id);
        if(pharmacyOptional.isPresent()){
            Pharmacy pharmacy = pharmacyOptional.get();
            return pharmacy;
        }
        throw new ResourceConflictException(1l,"Ne postoji apoteka");

    }

    @Override
    public Pharmacy getPharmacyByAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
            return pharmacy;
        }
        throw new ResourceConflictException(1l,"Ne postoje promocije/akcije");

    }

    @Override
    public Boolean subscribe(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            Set<Pharmacy> promotionSet = patient.getSubscribedPharmacies();
            Pharmacy pharmacy = pharmacyRepository.getOne(id);
            promotionSet.add(pharmacy);
            patient.setSubscribedPharmacies(promotionSet);
            patientRepository.saveAndFlush(patient);
            mailService.newSubscriptionForPromotion(pharmacy,patient);
            return true;
        }
        throw new ResourceConflictException(1l,"Ne postoji pacijent");

    }

    @Override
    public Set<Dermatologist> getDermaByPhaID(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        Set<Dermatologist> dermatologists = pharmacy.getDermatologists();
        return dermatologists;
    }

    @Override
    public Set<Pharmacist> getPharmaByPhaID(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        Set<Pharmacist> pharmacists =  pharmacy.getPharmacists();
        return  pharmacists;
    }

    @Override
    public Collection<DermatologistDto> getDermaForPhaAdmin() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Set<Dermatologist> dermatologists = pharmacy.getDermatologists();
        Set<DermatologistDto> dermatologistDtos = new HashSet<>();
        for(Dermatologist d : dermatologists){
            DermatologistDto dermatologistDto = dermatologistMapper.entity2Bean(d);
            List<WorkingHoursDTO> workingHoursDTOS = new Stack<>();
            WorkingHours wk = workingHoursRepository.getWorkingHoursByPharmacyAndDermatologist(pharmacy,d);
            workingHoursDTOS.add(workingHoursMapper.entity2Bean(wk));
            dermatologistDto.setWorkingHours(workingHoursDTOS);
            List<ExaminationDto> examinationDtos = new Stack<>();
            examinationDtos.addAll(examinationMapper.entity2Bean(examinationRepository.findAllByDermatologist(d)));
            dermatologistDto.setExaminations(examinationDtos);
            dermatologistDtos.add(dermatologistDto);
        }
        return dermatologistDtos;
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

    @Override
    public void addPharmacy(PharmacyDto pharmacyDto){
        Pharmacy pharmacy = pharmacyMapper.bean2Entity(pharmacyDto);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public void updatePharmacyInfo(PharmacyDto pharmacyDto) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyMapper.bean2Entity(pharmacyDto);
        pharmacyRepository.save(pharmacy);
    }

    @Override
    public Collection<Medicine> getAvailableMedicines(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        //Collection<MedicineQuantityPharmacy> medicationQuantities = pharmacy.getMedicationQuantities();
        List<MedicineQuantityPharmacy> medicationQuantities = medicineQuantityPharmacyRepository.findAllByPharmacy(pharmacy);
        Collection<Medicine> medicines = new HashSet<>();
        for (MedicineQuantityPharmacy medQuan:medicationQuantities) {
            if(medQuan.getQuantity()>0){
                medicines.add(medQuan.getMedicine());
            }
        }
        return medicines;
    }

    @Override
    public Collection<Examination> getAvailableExaminations(Long id) {
        Pharmacy pharmacy = pharmacyRepository.getOne(id);
        return examinationRepository.findAllByPharmacyAndIsFree(pharmacy,true);

    }

    @Override
    public void deleteEmployee(Long id, String type) {
        PharmacyAdmin pharmacyAdmin  =getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Date today = new Date();
        if(dermatologistRepository.existsById(id)){
            Dermatologist dermatologist = dermatologistRepository.getOne(id);
            Set<Examination> examinations = dermatologist.getExaminations();
            for (Examination examination:examinations) {
                if(examination.getDate().after(today)){
                    throw new ResourceConflictException(1l,"Greska!");
                }
            }
            Set<Dermatologist> dermatologists = pharmacy.getDermatologists();
            dermatologists.remove(dermatologist);
            pharmacy.setDermatologists(dermatologists);
            pharmacyRepository.save(pharmacy);
        }else {
            Pharmacist pharmacist = pharmacistRepository.getOne(id);
            Set<Counseling> counselings = pharmacist.getCounselings();
            for (Counseling counseling: counselings){
                if(counseling.getDate().after(today)){
                    throw new ResourceConflictException(1l,"Greska!");
                }
            }
            Set<Pharmacist> pharmacists = pharmacy.getPharmacists();
            pharmacists.remove(pharmacist);
            pharmacy.setPharmacists(pharmacists);
            pharmacyRepository.save(pharmacy);

        }
    }
}
