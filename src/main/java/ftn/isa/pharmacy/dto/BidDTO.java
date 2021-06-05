package ftn.isa.pharmacy.dto;
import java.util.Date;

public class BidDTO {

    private Long id;
    private float price;
    private String status;
    private Date endDate;
    private SupplierDTO supplier;
    private PurchaseOrderDTO purchaseOrder;

    public BidDTO() {
    }

    public BidDTO(Long id, float price, String status, Date endDate, SupplierDTO supplier, PurchaseOrderDTO purchaseOrder) {
        this.id = id;
        this.price = price;
        this.status = status;
        this.endDate = endDate;
        this.supplier = supplier;
        this.purchaseOrder = purchaseOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public PurchaseOrderDTO getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrderDTO purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
}
