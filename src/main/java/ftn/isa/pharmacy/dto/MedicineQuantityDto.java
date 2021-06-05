package ftn.isa.pharmacy.dto;

import java.util.List;

public class MedicineQuantityDto {
    Long id;
    List<Long> medicineIDs;
    List<Long> quantity;

    public MedicineQuantityDto(Long id, List<Long> medicineIDs, List<Long> quantity) {
        this.id = id;
        this.medicineIDs = medicineIDs;
        this.quantity = quantity;
    }

    public MedicineQuantityDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getMedicineIDs() {
        return medicineIDs;
    }

    public void setMedicineIDs(List<Long> medicineIDs) {
        this.medicineIDs = medicineIDs;
    }

    public List<Long> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Long> quantity) {
        this.quantity = quantity;
    }
}
