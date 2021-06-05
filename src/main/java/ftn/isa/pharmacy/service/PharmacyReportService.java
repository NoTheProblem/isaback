package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.MedicineReportDTO;
import ftn.isa.pharmacy.dto.PharmacyReportDTO;

import java.util.Date;

public interface PharmacyReportService {

    MedicineReportDTO yearlyMedicine(String year);

    MedicineReportDTO quartallyMedicine(String year, String quartal);

    MedicineReportDTO monthlyMedicine(String year, String month);

    PharmacyReportDTO yearlyExamination(String year);

    PharmacyReportDTO quartallyExamination(String year, String quartal);

    PharmacyReportDTO monthlyExamination(String year, String month);

    PharmacyReportDTO incomeReport(String startDate, String endDate);
}
