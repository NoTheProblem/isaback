package ftn.isa.pharmacy.dto;

import java.util.List;

public class PharmacyReportDTO {
    private List<String> categories;
    private List<Float> data;

    public PharmacyReportDTO() {
    }

    public PharmacyReportDTO(List<String> categories, List<Float> data) {
        this.categories = categories;
        this.data = data;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Float> getData() {
        return data;
    }

    public void setData(List<Float> data) {
        this.data = data;
    }
}
