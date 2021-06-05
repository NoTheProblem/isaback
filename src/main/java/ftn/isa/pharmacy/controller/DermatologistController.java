package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.mapper.impl.DermatologistMapperImpl;
import ftn.isa.pharmacy.service.DermatologistService;
import ftn.isa.pharmacy.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/dermatologist")
public class DermatologistController {

    private final DermatologistService dermatologistService;
    private final DermatologistMapperImpl dermatologistMapper;

    @Autowired
    public DermatologistController(DermatologistService dermatologistService, DermatologistMapperImpl dermatologistMapper) {
        this.dermatologistService = dermatologistService;
        this.dermatologistMapper = dermatologistMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<DermatologistDto>> getAll() {
        Collection<DermatologistDto> dermatologistDtoList = dermatologistService.getAll();
        return ResponseEntity.ok(dermatologistDtoList);
    }

    @GetMapping(value = "/getAllDermatologistsCandidates")
    public ResponseEntity<Collection<DermatologistDto>> getAllDermatologistsCandidates() {
        Collection<DermatologistDto> dermatologistDtoList = dermatologistService.getAllDermatologistsCandidates();
        return ResponseEntity.ok(dermatologistDtoList);
    }

    @PostMapping("/addToPharmacy")
    public void addToPharmacy(@RequestBody DermatologistDto dermatologistDto) {
        dermatologistService.addToPharmacy(dermatologistDto);
    }








}
