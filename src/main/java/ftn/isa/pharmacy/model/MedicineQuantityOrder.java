package ftn.isa.pharmacy.model;

import javax.persistence.*;

@Entity
public class MedicineQuantityOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long quantity;

    @Column
    private Long medicineID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PurchaseOrder purchaseOrder;

    public MedicineQuantityOrder() {
    }

    public MedicineQuantityOrder(Long id, Long quantity, Long medicineID, PurchaseOrder purchaseOrder) {
        this.id = id;
        this.quantity = quantity;
        this.medicineID = medicineID;
        this.purchaseOrder = purchaseOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getMedicine() {
        return medicineID;
    }

    public void setMedicine(Long medicineID) {
        this.medicineID = medicineID;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
