package ftn.isa.pharmacy.dto;

import java.util.Date;

public class PriceMediceDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private float price;
    private PharmacyDto pharmacy;
    private MedicineDto medicine;

    public PriceMediceDTO() {
    }

    public PriceMediceDTO(Long id, Date startDate, Date endDate, float price, PharmacyDto pharmacy, MedicineDto medicine) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.pharmacy = pharmacy;
        this.medicine = medicine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public PharmacyDto getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDto pharmacy) {
        this.pharmacy = pharmacy;
    }

    public MedicineDto getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineDto medicine) {
        this.medicine = medicine;
    }
}


