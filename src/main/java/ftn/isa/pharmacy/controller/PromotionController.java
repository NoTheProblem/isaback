package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.PromotionDTO;
import ftn.isa.pharmacy.mapper.impl.PromotionMapperImpl;
import ftn.isa.pharmacy.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    private final PromotionService promotionService;
    private final PromotionMapperImpl promotionMapper;

    @Autowired
    public PromotionController(PromotionService promotionService, PromotionMapperImpl promotionMapper) {
        this.promotionService = promotionService;
        this.promotionMapper = promotionMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PromotionDTO>> getAll() {
        Collection<PromotionDTO> promotionDTOS = promotionMapper.entity2Bean(promotionService.getAll());
        return ResponseEntity.ok(promotionDTOS);
    }

    @GetMapping(value = "/getAllByPharmacyID/{id}")
    public ResponseEntity<Collection<PromotionDTO>> getAllByPharmacyID(@PathVariable Long id) {
        Collection<PromotionDTO> promotionDTOS = promotionMapper.entity2Bean(promotionService.getAllByPharmacyID(id));
        return ResponseEntity.ok(promotionDTOS);
    }


    @GetMapping(value = "/getAllActive")
    public ResponseEntity<Collection<PromotionDTO>> getAllActive() {
        Collection<PromotionDTO> promotionDTOS = promotionMapper.entity2Bean(promotionService.getAllActive());
        return ResponseEntity.ok(promotionDTOS);
    }

    @GetMapping(value = "/getAllActiveByPharmacyID/{id}")
    public ResponseEntity<Collection<PromotionDTO>> getAllActiveByPharmacyID(@PathVariable Long id) {
        Collection<PromotionDTO> promotionDTOS = promotionMapper.entity2Bean(promotionService.getAllActiveByPharmacyID(id));
        return ResponseEntity.ok(promotionDTOS);
    }

    @GetMapping(value = "/getAllActiveForPharmacyAdmin")
    public ResponseEntity<Collection<PromotionDTO>> getAllActiveForPharmacyAdmin() {
        Collection<PromotionDTO> promotionDTOS = promotionMapper.entity2Bean(promotionService.getAllActiveForPharmacyAdmin());
        return ResponseEntity.ok(promotionDTOS);
    }

    @GetMapping(value = "/getAllForPharmacyAdmin")
    public ResponseEntity<Collection<PromotionDTO>> getAllForPharmacyAdmin() {
        Collection<PromotionDTO> promotionDTOS = promotionMapper.entity2Bean(promotionService.getAllForPharmacyAdmin());
        return ResponseEntity.ok(promotionDTOS);
    }




    @PostMapping("/addPromotion")
    public void addPromotion(@RequestBody PromotionDTO promotionDTO) {
        promotionService.addPromotion(promotionDTO);
    }



}
