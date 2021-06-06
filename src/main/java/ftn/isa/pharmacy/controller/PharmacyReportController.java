package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.*;
import ftn.isa.pharmacy.service.PharmacyReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharmacyReport")
public class PharmacyReportController {

    private final PharmacyReportService pharmacyReportService;

    public PharmacyReportController(PharmacyReportService pharmacyReportService) {
        this.pharmacyReportService = pharmacyReportService;
    }

    @GetMapping(value = "/examination/monthly/{year}/{month}")
    public ResponseEntity<PharmacyReportDTO> monthlyExamination(@PathVariable String year, @PathVariable String month) {
        PharmacyReportDTO pharmacyReportDTO =  pharmacyReportService.monthlyExamination(year, month);
        return ResponseEntity.ok(pharmacyReportDTO);
    }

    @GetMapping(value = "/examination/yearly/{year}")
    public ResponseEntity<PharmacyReportDTO> yearlyExamination(@PathVariable String year) {
        PharmacyReportDTO pharmacyReportDTO =  pharmacyReportService.yearlyExamination(year);
        return ResponseEntity.ok(pharmacyReportDTO);
    }

    @GetMapping(value = "/examination/quartally/{year}/{period}")
    public ResponseEntity<PharmacyReportDTO> quartallyExamination(@PathVariable String year, @PathVariable String period) {
        PharmacyReportDTO pharmacyReportDTO =  pharmacyReportService.quartallyExamination(year,period);
        return ResponseEntity.ok(pharmacyReportDTO);
    }

    @GetMapping(value = "/medicine/monthly/{year}/{month}")
    public ResponseEntity<MedicineReportDTO> monthlyMedicine(@PathVariable String year,@PathVariable String month) {
        MedicineReportDTO medicineReportDTO =  pharmacyReportService.monthlyMedicine(year, month);
        return ResponseEntity.ok(medicineReportDTO);
    }

    @GetMapping(value = "/medicine/yearly/{year}")
    public ResponseEntity<MedicineReportDTO> yearlyMedicine(@PathVariable String year) {
        MedicineReportDTO medicineReportDTO =  pharmacyReportService.yearlyMedicine(year);
        return ResponseEntity.ok(medicineReportDTO);

    }

    @GetMapping(value = "/medicine/quartally/{year}/{period}")
    public ResponseEntity<MedicineReportDTO> quartallyMedicine(@PathVariable String year,@PathVariable String period) {
        MedicineReportDTO medicineReportDTO =  pharmacyReportService.quartallyMedicine(year,period);
        return ResponseEntity.ok(medicineReportDTO);
    }

    @GetMapping(value = "/income/{start}/{end}")
    public ResponseEntity<PharmacyReportDTO> incomeReport(@PathVariable String start,@PathVariable String end) {

        PharmacyReportDTO pharmacyReportDTO =  pharmacyReportService.incomeReport(start,end);
        return ResponseEntity.ok(pharmacyReportDTO);
    }



}
