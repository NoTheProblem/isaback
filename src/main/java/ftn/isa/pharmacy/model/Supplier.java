package ftn.isa.pharmacy.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("ROLE_SUPPLIER")
public class Supplier extends User {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineQuantitySupplier> medicines = new HashSet<MedicineQuantitySupplier>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Bid> bids = new HashSet<Bid>();


    public Supplier() {
    }

    public Supplier(Set<MedicineQuantitySupplier> medicines, Set<Bid> bids, Set<PurchaseOrder> wonPurchaseOrders) {
        this.medicines = medicines;
        this.bids = bids;
    }

    public Supplier(Long id, String tip, String firstName, String lastName, String username, String password, String email, String country, String city, String address, String phoneNumber, Date birthDate, Set<MedicineQuantitySupplier> medicines, Set<Bid> bids) {
        super(id, tip, firstName, lastName, username, password, email, country, city, address, phoneNumber, birthDate);
        this.medicines = medicines;
        this.bids = bids;
    }

    public Set<MedicineQuantitySupplier> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<MedicineQuantitySupplier> medicines) {
        this.medicines = medicines;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

}
