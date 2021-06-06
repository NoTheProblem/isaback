package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PriceMediceDTO;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.dto.MedicineRegisterDto;


import java.util.Collection;
import java.util.List;

public interface MedicineService {

    List<Medicine> getAll();
    List<Medicine> getMedicinesForPhaAdmin();
    PriceMediceDTO getMedPriceForPhaAdmin(Long medID);
    void addNewMedPrice(PriceMediceDTO priceMediceDTO);
    List<Medicine> getAllReg();
    void addMedicine(MedicineRegisterDto medicineRegisterDto);

    Collection<Medicine> getMissingMedicines();

    void removeMedicineFromPhamracy(MedicineDto medicineDto);
}
