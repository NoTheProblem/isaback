package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.model.Patient;

import java.util.Collection;

public interface PatientService {

    void addAllergy(MedicineDto medicineDto);

    Collection<Patient> getAll();
}
