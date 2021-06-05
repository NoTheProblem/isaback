package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.AbsenceDTO;
import ftn.isa.pharmacy.mapper.impl.AbsenceMapperImpl;
import ftn.isa.pharmacy.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/absence")
public class AbsenceController {

    private final AbsenceService absenceService;
    private final AbsenceMapperImpl absenceMapper;

    @Autowired
    public AbsenceController(AbsenceService absenceService, AbsenceMapperImpl absenceMapper) {
        this.absenceService = absenceService;
        this.absenceMapper = absenceMapper;
    }
    @GetMapping(value = "/getAllDermatologistRequests")
    public ResponseEntity<Collection<AbsenceDTO>> getAllDermatologistRequests() {
        Collection<AbsenceDTO> absenceDTOCollection = absenceMapper.entity2Bean(absenceService.getAllDermatologistRequests());
        return ResponseEntity.ok(absenceDTOCollection);
    }


    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<AbsenceDTO> getAbsenceByID(@PathVariable Long id){
        AbsenceDTO absenceDTO = absenceMapper.entity2Bean(absenceService.getAbsenceByID(id));
        return ResponseEntity.ok(absenceDTO);
    }

    @GetMapping(value = "/getByEmployeeId/{id}")
    public ResponseEntity<Collection<AbsenceDTO>> getByEmployeeId(@PathVariable Long id){
        Collection<AbsenceDTO> absenceDTOS = absenceMapper.entity2Bean(absenceService.getByEmployeeId(id));
        return ResponseEntity.ok(absenceDTOS);
    }


    @GetMapping(value = "/getAllPharmacistRequests")
    public ResponseEntity<Collection<AbsenceDTO>> getAllPharmacistRequests() {
        Collection<AbsenceDTO> absenceDTOCollection = absenceMapper.entity2Bean(absenceService.getAllPharmacistRequests());
        return ResponseEntity.ok(absenceDTOCollection);
    }

    @PutMapping("/answer")
    public ResponseEntity<AbsenceDTO> acceptAbsence(@RequestBody AbsenceDTO absenceDTO)  throws Exception  {
        try{
            absenceDTO = absenceMapper.entity2Bean(absenceService.answer(absenceDTO));
        } catch(Exception e) {
            return new ResponseEntity<AbsenceDTO>(HttpStatus.I_AM_A_TEAPOT); // :)
        }
        return ResponseEntity.ok(absenceDTO);
    }




}
