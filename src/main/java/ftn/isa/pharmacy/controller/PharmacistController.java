package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.service.PharmacistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/pharmacist")
public class PharmacistController {

    private final PharmacistService pharmacistService;

    public PharmacistController(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PharmacistDTO>> getAll() {
        Collection<PharmacistDTO>  pharmacistDTOS= pharmacistService.getAll();
        return ResponseEntity.ok(pharmacistDTOS);
    }

    @GetMapping(value = "/getAllFree")
    public ResponseEntity<Collection<PharmacistDTO>> getAllFree() {
        Collection<PharmacistDTO>  pharmacistDTOS= pharmacistService.getAllFree();
        return ResponseEntity.ok(pharmacistDTOS);
    }

    @PostMapping("/addToPharmacy")
    public void addToPharmacy(@RequestBody PharmacistDTO pharmacistDTO) {
        pharmacistService.addToPharmacy(pharmacistDTO);
    }

    @PostMapping("/registerToPharmacy")
    public void registerToPharmacy(@RequestBody PharmacistDTO pharmacistDTO) {
        pharmacistService.registerToPharmacy(pharmacistDTO);
    }



}



