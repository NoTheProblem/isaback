package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class WorkingHoursPharmacist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String startTime;

    @Column
    private String endTime;

    @Column
    private String workDay;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacist pharmacist;

    public WorkingHoursPharmacist() {
    }

    public WorkingHoursPharmacist(Long id, String startTime, String endTime, String workDay, Pharmacy pharmacy, Pharmacist pharmacist) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workDay = workDay;
        this.pharmacy = pharmacy;
        this.pharmacist = pharmacist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}
