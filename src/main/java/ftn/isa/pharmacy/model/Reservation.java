package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean pickedUp;

    @Column
    private Date pickUpDate;

    @Column
    private Date endDate;

    @Column
    private float price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantityReservation> medicineis = new HashSet<MedicineQuantityReservation>();

    public Reservation() {
    }

    public Reservation(Long id, Boolean pickedUp, Date pickUpDate, Date endDate, float price, Pharmacy pharmacy, Patient patient, Set<MedicineQuantityReservation> medicineis) {
        this.id = id;
        this.pickedUp = pickedUp;
        this.pickUpDate = pickUpDate;
        this.endDate = endDate;
        this.price = price;
        this.pharmacy = pharmacy;
        this.patient = patient;
        this.medicineis = medicineis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(Boolean pickedUp) {
        this.pickedUp = pickedUp;
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

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<MedicineQuantityReservation> getMedicineis() {
        return medicineis;
    }

    public void setMedicineis(Set<MedicineQuantityReservation> medicineis) {
        this.medicineis = medicineis;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }
}
