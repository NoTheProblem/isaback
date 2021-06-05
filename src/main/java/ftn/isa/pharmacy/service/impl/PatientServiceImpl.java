package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.mapper.MedicineMapper;
import ftn.isa.pharmacy.model.Medicine;
import ftn.isa.pharmacy.model.Patient;
import ftn.isa.pharmacy.model.User;
import ftn.isa.pharmacy.repository.PatientRepository;
import ftn.isa.pharmacy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final MedicineMapper medicineMapper;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(MedicineMapper medicineMapper, PatientRepository patientRepository) {
        this.medicineMapper = medicineMapper;
        this.patientRepository = patientRepository;
    }

    @Override
    public void addAllergy(MedicineDto medicineDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Patient patient = (Patient) authentication.getPrincipal(); --> ovo radi, ali ne povuce allergicMedicines jer je lazy
        Optional<Patient> patientOptional = patientRepository.findById(((User) authentication.getPrincipal()).getId());
        if(patientOptional.isPresent()) {
            Medicine medicine = medicineMapper.bean2Entity(medicineDto);
            Patient patient = patientOptional.get();
            patient.getAllergicMedicines().add(medicine);
            patientRepository.saveAndFlush(patient);
        }
    }

    @Override
    public Collection<Patient> getAll() {
        return patientRepository.findAll();
    }
}
