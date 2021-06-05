package ftn.isa.pharmacy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_ADMIN")
public class PharmacyAdmin extends User {



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pharmacy pharmacy;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<PurchaseOrder>();

    public PharmacyAdmin() {
    }

    public PharmacyAdmin(Pharmacy pharmacy, Set<PurchaseOrder> purchaseOrders) {
        this.pharmacy = pharmacy;
        this.purchaseOrders = purchaseOrders;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}
