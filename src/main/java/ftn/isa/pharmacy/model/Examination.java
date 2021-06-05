package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean penalty;

    @Column
    private Boolean isFree;

    @Column
    private String examinationReport;

    @Column
    private int loyaltyScore;

    @Column
    private Date date;

    @Column
    private Time time;

    @Column
    private int durationMinutes;

    @Column
    private float price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Dermatologist dermatologist;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    public Examination() {
    }

    public Examination(Long id, Boolean penalty, Boolean isFree, String examinationReport, int loyaltyScore, Date date, Time time, int durationMinutes, float price, Patient patient, Dermatologist dermatologist, Pharmacy pharmacy) {
        this.id = id;
        this.penalty = penalty;
        this.isFree = isFree;
        this.examinationReport = examinationReport;
        this.loyaltyScore = loyaltyScore;
        this.date = date;
        this.time = time;
        this.durationMinutes = durationMinutes;
        this.price = price;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.pharmacy = pharmacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public String getExaminationReport() {
        return examinationReport;
    }

    public void setExaminationReport(String examinationReport) {
        this.examinationReport = examinationReport;
    }

    public int getLoyaltyScore() {
        return loyaltyScore;
    }

    public void setLoyaltyScore(int loyaltyScore) {
        this.loyaltyScore = loyaltyScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", penalty=" + penalty +
                ", isFree=" + isFree +
                ", examinationReport='" + examinationReport + '\'' +
                ", loyaltyScore=" + loyaltyScore +
                ", date=" + date +
                ", time=" + time +
                ", durationMinutes=" + durationMinutes +
                ", price=" + price +
                ", patient=" + patient +
                ", dermatologist=" + dermatologist +
                ", pharmacy=" + pharmacy +
                '}';
    }
}
