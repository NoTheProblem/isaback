package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.ExaminationDto;
import ftn.isa.pharmacy.mapper.impl.ExaminationMapperImpl;
import ftn.isa.pharmacy.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/examination")
public class ExaminationController {

    private final ExaminationService examinationService;
    private final ExaminationMapperImpl examinationMapper;


    @Autowired
    public ExaminationController(ExaminationService examinationService, ExaminationMapperImpl examinationMapper) {
        this.examinationService = examinationService;
        this.examinationMapper = examinationMapper;
    }

    @GetMapping(value = "/getAllFree")
    public ResponseEntity<Collection<ExaminationDto>> getAll() {
        Collection<ExaminationDto> examinationDtoList = examinationMapper.entity2Bean(examinationService.getAllFree(Boolean.TRUE));
        return ResponseEntity.ok(examinationDtoList);
    }

    @PostMapping("/addExamination")
    public boolean declineAbsence(@RequestBody ExaminationDto examinationDto) {
        return examinationService.addExamination(examinationDto);
    }

    @GetMapping(value = "/getByDermaIdAndDateForPhaAdmin/{id}/{date}")
    public ResponseEntity<Collection<ExaminationDto>> getByDermaIdAndDateForPhaAdmin(@PathVariable Long id, @PathVariable String date) {
        Collection<ExaminationDto> examinationDtoList = examinationMapper.entity2Bean(examinationService.getByDermaIdAndDateForPhaAdmin(id,date));
        return ResponseEntity.ok(examinationDtoList);
    }



}
