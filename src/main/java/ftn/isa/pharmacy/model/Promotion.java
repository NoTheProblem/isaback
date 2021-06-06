package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String type;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;


    public Promotion() {
    }

    public Promotion(Long id, String title, String text, Date startDate, Date endDate, String type, Pharmacy pharmacy) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.pharmacy = pharmacy;
    }

    public Promotion(Long id, String title, String text, String type, Date startDate, Date endDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

}

