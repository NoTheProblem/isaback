package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_DERMATOLOGIST")
public class Dermatologist extends User{

    @Column
    private Float evaluationGrade;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkingHours> workingHours = new HashSet<WorkingHours>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Examination> examinations = new HashSet<Examination>();

    @ManyToMany(mappedBy = "dermatologists")
    private Set<Pharmacy> pharmacys = new HashSet<Pharmacy>();

    public Dermatologist() {
    }

    public Dermatologist(Float evaluationGrade, Set<WorkingHours> workingHours, Set<Examination> examinations, Set<Pharmacy> pharmacys) {
        this.evaluationGrade = evaluationGrade;
        this.workingHours = workingHours;
        this.examinations = examinations;
        this.pharmacys = pharmacys;
    }

    public Float getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(Float evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public Set<WorkingHours> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Set<WorkingHours> workingHours) {
        this.workingHours = workingHours;
    }

    public Set<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(Set<Examination> examinations) {
        this.examinations = examinations;
    }

    public Set<Pharmacy> getPharmacys() {
        return pharmacys;
    }

    public void setPharmacys(Set<Pharmacy> pharmacys) {
        this.pharmacys = pharmacys;
    }

}
