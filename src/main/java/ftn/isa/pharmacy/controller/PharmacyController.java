package ftn.isa.pharmacy.controller;

import java.util.Collection;

import ftn.isa.pharmacy.dto.*;
import ftn.isa.pharmacy.mapper.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ftn.isa.pharmacy.service.PharmacyService;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;
    private final PharmacyMapperImpl pharmacyMapper;
    private final DermatologistMapperImpl dermatologistMapper;
    private final PharmacistMapperImpl pharmacistMapper;
    private final MedicineMapperImpl medicineMapper;
    private final ExaminationMapperImpl examinationMapper;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService, PharmacyMapperImpl pharmacyMapper, DermatologistMapperImpl dermatologistMapper, PharmacistMapperImpl pharmacistMapper, MedicineMapperImpl medicineMapper, ExaminationMapperImpl examinationMapper) {
        this.pharmacyService = pharmacyService;
        this.pharmacyMapper = pharmacyMapper;
        this.dermatologistMapper = dermatologistMapper;
        this.pharmacistMapper = pharmacistMapper;
        this.medicineMapper = medicineMapper;
        this.examinationMapper = examinationMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PharmacyDto>> getAll() {
        Collection<PharmacyDto> pharmacyDtoList = pharmacyMapper.entity2Bean(pharmacyService.getAll());
        return ResponseEntity.ok(pharmacyDtoList);
    }

    @GetMapping(value = "/unauth/pharmacy/{id}")
    public ResponseEntity<PharmacyDto> getPharmacyByID(@PathVariable Long id) {
        PharmacyDto pharmacyDto = pharmacyMapper.entity2Bean(pharmacyService.getById(id));
        return ResponseEntity.ok(pharmacyDto);
    }

    @GetMapping(value = "/admin")
    public ResponseEntity<PharmacyDto> getPharmacyByID() {
        PharmacyDto pharmacyDto = pharmacyMapper.entity2Bean(pharmacyService.getPharmacyByAdmin());
        return ResponseEntity.ok(pharmacyDto);
    }

    @GetMapping(value = "/subscribe/{id}")
    public Boolean subscribe(@PathVariable Long id) {
        return pharmacyService.subscribe(id);
    }

    @GetMapping(value = "/getDerma/{id}")
    public ResponseEntity<Collection<DermatologistDto>> getDerma(@PathVariable Long id) {
        Collection<DermatologistDto> dermatologistDtoList = dermatologistMapper.entity2Bean(pharmacyService.getDermaByPhaID(id));
        return ResponseEntity.ok(dermatologistDtoList);
    }

    @GetMapping(value = "/getPharma/{id}")
    public ResponseEntity<Collection<PharmacistDTO>> getPharma(@PathVariable Long id) {
        Collection<PharmacistDTO> pharmacistDTOS = pharmacistMapper.entity2Bean(pharmacyService.getPharmaByPhaID(id));
        return ResponseEntity.ok(pharmacistDTOS);
    }

    @GetMapping(value = "/getDerma/phaadmin")
    public ResponseEntity<Collection<DermatologistDto>> getDermaForPhaAdmin() {
        Collection<DermatologistDto> dermatologistDtoList = pharmacyService.getDermaForPhaAdmin();
        return ResponseEntity.ok(dermatologistDtoList);
    }

    @PostMapping("/addPharmacy")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    public void addPharmacy(@RequestBody PharmacyDto pharmacyDto) {
        pharmacyService.addPharmacy(pharmacyDto);
    }

    @PostMapping("/updatePharmacyInfo")
    public void updatePharmacyInfo(@RequestBody PharmacyDto pharmacyDto) {
        pharmacyService.updatePharmacyInfo(pharmacyDto);
    }

    @GetMapping(value = "/unauth/medicines/{id}")
    public ResponseEntity<Collection<MedicineDto>> getAvailableMedicines(@PathVariable Long id) {
        Collection<MedicineDto> medicineDtos = medicineMapper.entity2Bean(pharmacyService.getAvailableMedicines(id));
        return ResponseEntity.ok(medicineDtos);
    }

    @GetMapping(value = "/unauth/examinations/{id}")
    public ResponseEntity<Collection<ExaminationDto>> getAvailableExaminations(@PathVariable Long id) {
        Collection<ExaminationDto> examinationDtos = examinationMapper.entity2Bean(pharmacyService.getAvailableExaminations(id));
        return ResponseEntity.ok(examinationDtos);
    }
    @PostMapping(value = "/deleteEmployee/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id,@RequestBody String type) {
        pharmacyService.deleteEmployee(id,type);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

