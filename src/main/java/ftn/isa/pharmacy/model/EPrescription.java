package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
public class EPrescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private float price;

    @Column
    private String status;

    @Column
    private Date dateOfIssue;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PersList> medicines = new HashSet<PersList>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patient patient;

    public EPrescription() {
    }

    public EPrescription(Long id, String code, float price, String status, Date dateOfIssue, Set<PersList> medicines, Patient patient) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.status = status;
        this.dateOfIssue = dateOfIssue;
        this.medicines = medicines;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Set<PersList> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<PersList> medicines) {
        this.medicines = medicines;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
