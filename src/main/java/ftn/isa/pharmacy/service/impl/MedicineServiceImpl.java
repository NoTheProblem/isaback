package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PriceMediceDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.PriceMediceMapper;
import ftn.isa.pharmacy.mapper.impl.MedicineMapperImpl;
import ftn.isa.pharmacy.repository.MedicineQuantityPharmacyRepository;
import ftn.isa.pharmacy.repository.MedicineRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.PriceMediceListRepository;
import ftn.isa.pharmacy.dto.MedicineRegisterDto;
import ftn.isa.pharmacy.mapper.MedicineRegisterMapper;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.SysAdminRepository;
import ftn.isa.pharmacy.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.User;

import java.util.*;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;
    private final PriceMediceListRepository priceMediceListRepository;
    private final MedicineMapperImpl medicineMapper;
    private final PriceMediceMapper priceMediceMapper;
  
    private final SysAdminRepository sysAdminRepository;
    private final MedicineRegisterMapper medicineRegisterMapper;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository, PharmacyAdminRepository pharmacyAdminRepository, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository, PriceMediceListRepository priceMediceListRepository, MedicineMapperImpl medicineMapper, PriceMediceMapper priceMediceMapper, SysAdminRepository sysAdminRepository, MedicineRegisterMapper medicineRegisterMapper) {
        this.medicineRepository = medicineRepository;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
        this.priceMediceListRepository = priceMediceListRepository;
        this.medicineMapper = medicineMapper;
        this.priceMediceMapper = priceMediceMapper;
        this.sysAdminRepository = sysAdminRepository;
        this.medicineRegisterMapper = medicineRegisterMapper;
    }

    @Override
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> getMedicinesForPhaAdmin() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        List<MedicineQuantityPharmacy> medicineQuantityPharmacySet = medicineQuantityPharmacyRepository.findAllByPharmacy(pharmacy);
        //Set<MedicineQuantityPharmacy> medicineQuantityPharmacySet = pharmacy.getMedicationQuantities();
        List<Medicine> medicines = new Stack<>();
        for (MedicineQuantityPharmacy medQ: medicineQuantityPharmacySet) {
            medicines.add(medQ.getMedicine());
        }
        return medicines;
    }

    @Override
    public PriceMediceDTO getMedPriceForPhaAdmin(Long medID) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        List<PriceMediceList> priceMediceLists = new Stack<>();
        Medicine medicine = medicineRepository.getOne(medID);
        MedicineDto medicineDto = medicineMapper.entity2Bean(medicine);
        priceMediceLists = priceMediceListRepository.findAllByPharmacyAndMedicineOrderByEndDateDesc(pharmacy,medicine);
        if(priceMediceLists.isEmpty()){
            PriceMediceList priceMediceList = new PriceMediceList();
            PriceMediceDTO priceMediceDTO = priceMediceMapper.entity2Bean(priceMediceList);
            priceMediceDTO.setPrice(-1);
            priceMediceDTO.setMedicine(medicineDto);
            return priceMediceDTO;
        }
        PriceMediceList priceMediceList = priceMediceLists.get(0);
        PriceMediceDTO priceMediceDTO = priceMediceMapper.entity2Bean(priceMediceList);
        priceMediceDTO.setMedicine(medicineDto);
        return priceMediceDTO;
    }

    @Override
    public void addNewMedPrice(PriceMediceDTO priceMediceDTO) {
        Medicine medicine = medicineMapper.bean2Entity(priceMediceDTO.getMedicine());
        PriceMediceList priceMediceList = priceMediceMapper.bean2Entity(priceMediceDTO);
        Date date = new Date();
        if(priceMediceList.getStartDate().before(date)){
            throw new ResourceConflictException(1l,"Ne moze se unazad stavljati vreme");
        }
        priceMediceList.setMedicine(medicine);
        date.setTime(priceMediceList.getStartDate().getTime());
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        List<PriceMediceList> priceMediceLists = priceMediceListRepository.
                findAllByPharmacyAndMedicineOrderByStartDateDesc(pharmacy,medicine);
        int i = 0;
        for (PriceMediceList priceMed: priceMediceLists) {
            if (date.before(priceMed.getStartDate())){
                throw new ResourceConflictException(1l,"Postoji vec cena za taj period");
            }
            if(i==0){
                priceMed.setEndDate(date);
                priceMediceListRepository.save(priceMed);
            }
            i = i + 1;
        }
        priceMediceList.setPharmacy(pharmacy);
        priceMediceListRepository.save(priceMediceList);
    }

    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            return pharmacyAdminOptional.get();
        }
        throw new ResourceConflictException(1l,"Greska!");
    }
    public List<Medicine> getAllReg() {
        return medicineRepository.findAll();
    }

    @Override
    public void addMedicine(MedicineRegisterDto medicineRegisterDto){
        Medicine medicine = medicineRegisterMapper.bean2Entity(medicineRegisterDto);
        medicineRepository.save(medicine);
    }

    @Override
    public Collection<Medicine> getMissingMedicines() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        return pharmacy.getMissingMedicine();
    }

    @Override
    public void removeMedicineFromPhamracy(MedicineDto medicineDto) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        Medicine medicine = medicineMapper.bean2Entity(medicineDto);
        Set<PersList>  medicinePersLists =medicine.getPersLists();
        for(PersList perscription: medicinePersLists){
            if(perscription.getePrescription().getStatus().equals("NotTaken")){
                throw new ResourceConflictException(1l,"Greska!");
            }
        }
        medicineQuantityPharmacyRepository.deleteByPharmacyAndMedicine(pharmacy,medicine);
    }

    private SysAdmin getSysAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<SysAdmin> sysAdminOptional = sysAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(sysAdminOptional.isPresent()) {
            SysAdmin sysAdmin = sysAdminOptional.get();
            return sysAdmin;
        }
        return null;
    }

}
