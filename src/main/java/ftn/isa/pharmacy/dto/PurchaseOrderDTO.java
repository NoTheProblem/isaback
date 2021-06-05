package ftn.isa.pharmacy.dto;

import java.util.Date;

public class PurchaseOrderDTO {
    Long id;
    Date endDate;
    Date createDate;
    String status;
    Long pharmacyAdmin;
    Long chosenSupplier;
    Long orderMedicines;
    Long supplierID;
    float price;
    MedicineQuantityDto medQuan;
    //bids

    public PurchaseOrderDTO() {
    }

    public PurchaseOrderDTO(Long id, Date endDate, Date createDate, String status, Long pharmacyAdmin, Long chosenSupplier, Long orderMedicines, Long supplierID, float price, MedicineQuantityDto medQuan) {
        this.id = id;
        this.endDate = endDate;
        this.createDate = createDate;
        this.status = status;
        this.pharmacyAdmin = pharmacyAdmin;
        this.chosenSupplier = chosenSupplier;
        this.orderMedicines = orderMedicines;
        this.supplierID = supplierID;
        this.price = price;
        this.medQuan = medQuan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPharmacyAdmin() {
        return pharmacyAdmin;
    }

    public void setPharmacyAdmin(Long pharmacyAdmin) {
        this.pharmacyAdmin = pharmacyAdmin;
    }

    public Long getChosenSupplier() {
        return chosenSupplier;
    }

    public void setChosenSupplier(Long chosenSupplier) {
        this.chosenSupplier = chosenSupplier;
    }

    public Long getOrderMedicines() {
        return orderMedicines;
    }

    public void setOrderMedicines(Long orderMedicines) {
        this.orderMedicines = orderMedicines;
    }

    public Long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public MedicineQuantityDto getMedQuan() {
        return medQuan;
    }

    public void setMedQuan(MedicineQuantityDto medQuan) {
        this.medQuan = medQuan;
    }
}


