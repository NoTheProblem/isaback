package ftn.isa.pharmacy.dto;

import java.util.List;
import java.util.Stack;

public class MedicineReportDTO {

    private List<String> categories;
    private List<MedQuanReportDTO> data;

    public MedicineReportDTO() {
        this.categories = new Stack<>();
        this.data = new Stack<>();
    }

    public MedicineReportDTO(List<String> categories, List<MedQuanReportDTO> data) {
        this.categories = categories;
        this.data = data;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<MedQuanReportDTO> getData() {
        return data;
    }

    public void setData(List<MedQuanReportDTO> data) {
        this.data = data;
    }
}
