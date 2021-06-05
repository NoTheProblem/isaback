package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class LoyaltyProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int downScore;

    @Column
    private int upScore;

    @Column
    private float discountPercentage;

    @Column
    private String typeOfLoyalty;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Patient> patients = new HashSet<Patient>();

    public LoyaltyProgram() {
    }

    public LoyaltyProgram(Long id, int downScore, int upScore, float discountPercentage, String typeOfLoyalty, Set<Patient> patients) {
        this.id = id;
        this.downScore = downScore;
        this.upScore = upScore;
        this.discountPercentage = discountPercentage;
        this.typeOfLoyalty = typeOfLoyalty;
        this.patients = patients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDownScore() {
        return downScore;
    }

    public void setDownScore(int downScore) {
        this.downScore = downScore;
    }

    public int getUpScore() {
        return upScore;
    }

    public void setUpScore(int upScore) {
        this.upScore = upScore;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getTypeOfLoyalty() {
        return typeOfLoyalty;
    }

    public void setTypeOfLoyalty(String typeOfLoyalty) {
        this.typeOfLoyalty = typeOfLoyalty;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
