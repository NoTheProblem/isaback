package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_USER")
public class Patient extends User{

    @Column
    private int loyaltyScore;

    @Column
    private int penaltyScore;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LoyaltyProgram loyaltyProgram;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EPrescription> ePrescriptions = new HashSet<EPrescription>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Complaint> complaints = new HashSet<Complaint>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Counseling> counselings = new HashSet<Counseling>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<Examination>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<Reservation>();

    @ManyToMany
    @JoinTable(name = "allergicMedicines", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"))
    private Set<Medicine> allergicMedicines = new HashSet<Medicine>();

    @ManyToMany
    @JoinTable(name = "patient_pharmacy_promotions", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"))
    private Set<Pharmacy> subscribedPharmacies = new HashSet<Pharmacy>();

    public Patient() {
    }

    public Patient(int loyaltyScore, int penaltyScore, LoyaltyProgram loyaltyProgram, Set<EPrescription> ePrescriptions, Set<Complaint> complaints, Set<Counseling> counselings, Set<Examination> examinations, Set<Reservation> reservations, Set<Medicine> allergicMedicines, Set<Pharmacy> subscribedPharmacies) {
        this.loyaltyScore = loyaltyScore;
        this.penaltyScore = penaltyScore;
        this.loyaltyProgram = loyaltyProgram;
        this.ePrescriptions = ePrescriptions;
        this.complaints = complaints;
        this.counselings = counselings;
        this.examinations = examinations;
        this.reservations = reservations;
        this.allergicMedicines = allergicMedicines;
        this.subscribedPharmacies = subscribedPharmacies;
    }

    public int getLoyaltyScore() {
        return loyaltyScore;
    }

    public void setLoyaltyScore(int loyaltyScore) {
        this.loyaltyScore = loyaltyScore;
    }

    public int getPenaltyScore() {
        return penaltyScore;
    }

    public void setPenaltyScore(int penaltyScore) {
        this.penaltyScore = penaltyScore;
    }

    public LoyaltyProgram getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public Set<EPrescription> getePrescriptions() {
        return ePrescriptions;
    }

    public void setePrescriptions(Set<EPrescription> ePrescriptions) {
        this.ePrescriptions = ePrescriptions;
    }

    public Set<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(Set<Complaint> complaints) {
        this.complaints = complaints;
    }

    public Set<Counseling> getCounselings() {
        return counselings;
    }

    public void setCounselings(Set<Counseling> counselings) {
        this.counselings = counselings;
    }

    public Set<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(Set<Examination> examinations) {
        this.examinations = examinations;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Medicine> getAllergicMedicines() {
        return allergicMedicines;
    }

    public void setAllergicMedicines(Set<Medicine> allergicMedicines) {
        this.allergicMedicines = allergicMedicines;
    }

    public Set<Pharmacy> getSubscribedPharmacies() {
        return subscribedPharmacies;
    }

    public void setSubscribedPharmacies(Set<Pharmacy> subscribedPharmacies) {
        this.subscribedPharmacies = subscribedPharmacies;
    }
}
