package ftn.isa.pharmacy.dto;

import java.util.List;
import java.util.Stack;

public class MedQuanReportDTO {

    private String  medName;
    private List<Integer> medQuan;

    public MedQuanReportDTO() {
        this.medQuan = new Stack<>();
    }

    public MedQuanReportDTO(String medName, List<Integer> medQuan) {
        this.medName = medName;
        this.medQuan = medQuan;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public List<Integer> getMedQuan() {
        return medQuan;
    }

    public void setMedQuan(List<Integer> medQuan) {
        this.medQuan = medQuan;
    }
}
