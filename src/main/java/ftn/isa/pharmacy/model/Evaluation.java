package ftn.isa.pharmacy.model;

import javax.persistence.*;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @Column
    private Long idOfEvaluated;

    @Column
    private String typeOfEvaluation;


    public Evaluation() {
    }

    public Evaluation(Long id, Patient patient, Long idOfEvaluated, String typeOfEvaluation) {
        this.id = id;
        this.patient = patient;
        this.idOfEvaluated = idOfEvaluated;
        this.typeOfEvaluation = typeOfEvaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getIdOfEvaluated() {
        return idOfEvaluated;
    }

    public void setIdOfEvaluated(Long idOfEvaluated) {
        this.idOfEvaluated = idOfEvaluated;
    }

    public String getTypeOfEvaluation() {
        return typeOfEvaluation;
    }

    public void setTypeOfEvaluation(String typeOfEvaluation) {
        this.typeOfEvaluation = typeOfEvaluation;
    }
}
