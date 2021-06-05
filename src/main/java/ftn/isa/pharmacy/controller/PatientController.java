package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.MedicineDto;
import ftn.isa.pharmacy.dto.PatientDTO;
import ftn.isa.pharmacy.dto.UserDTO;
import ftn.isa.pharmacy.mapper.impl.PatientMapperImpl;
import ftn.isa.pharmacy.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/patient")
// Nije samo za korisnika
public class PatientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;
    private final PatientMapperImpl patientMapper;
    @Autowired
    public PatientController(PatientService patientService, PatientMapperImpl patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping("/addAllergy")
    public void addAllergy(@RequestBody MedicineDto medicineDto) {
        patientService.addAllergy(medicineDto);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PatientDTO>> getAll() {
        Collection<PatientDTO> userDtoList = patientMapper.entity2Bean(patientService.getAll());
        return ResponseEntity.ok(userDtoList);
    }

}
