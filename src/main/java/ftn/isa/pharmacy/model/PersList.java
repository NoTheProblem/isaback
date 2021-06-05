package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class PersList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;

    @Column
    private Date dateOfPurchase;

    @Column
    private String wayOfUse;

    @Column
    private float price;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Medicine medicine;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EPrescription ePrescription;

    public PersList() {
    }

    public PersList(Long id, int quantity, Date dateOfPurchase, String wayOfUse, float price, Medicine medicine, Pharmacy pharmacy, EPrescription ePrescription) {
        this.id = id;
        this.quantity = quantity;
        this.dateOfPurchase = dateOfPurchase;
        this.wayOfUse = wayOfUse;
        this.price = price;
        this.medicine = medicine;
        this.pharmacy = pharmacy;
        this.ePrescription = ePrescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getWayOfUse() {
        return wayOfUse;
    }

    public void setWayOfUse(String wayOfUse) {
        this.wayOfUse = wayOfUse;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public EPrescription getePrescription() {
        return ePrescription;
    }

    public void setePrescription(EPrescription ePrescription) {
        this.ePrescription = ePrescription;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
