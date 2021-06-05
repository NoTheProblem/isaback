package ftn.isa.pharmacy.model;

import javax.persistence.*;

@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String complaintText;

    @Column
    private String status;

    @Column
    private String complaintAnswer;

    @Column
    private Long idOfSubject;

    @Column
    private String typeOfComplaint;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    @Column
    private Long idOfAdmin;


    public Complaint() {
    }

    public Complaint(Long id, String complaintText, String status, String complaintAnswer, Long idOfSubject, String typeOfComplaint, Patient patient, Long idOfAdmin) {
        this.id = id;
        this.complaintText = complaintText;
        this.status = status;
        this.complaintAnswer = complaintAnswer;
        this.idOfSubject = idOfSubject;
        this.typeOfComplaint = typeOfComplaint;
        this.patient = patient;
        this.idOfAdmin = idOfAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComplaintAnswer() {
        return complaintAnswer;
    }

    public void setComplaintAnswer(String complaintAnswer) {
        this.complaintAnswer = complaintAnswer;
    }

    public Long getIdOfSubject() {
        return idOfSubject;
    }

    public void setIdOfSubject(Long idOfSubject) {
        this.idOfSubject = idOfSubject;
    }

    public String getTypeOfComplaint() {
        return typeOfComplaint;
    }

    public void setTypeOfComplaint(String typeOfComplaint) {
        this.typeOfComplaint = typeOfComplaint;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getIdOfAdmin() {
        return idOfAdmin;
    }

    public void setIdOfAdmin(Long idOfAdmin) {
        this.idOfAdmin = idOfAdmin;
    }
}
