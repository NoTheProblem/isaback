package ftn.isa.pharmacy.repository;


import ftn.isa.pharmacy.model.Bid;
import ftn.isa.pharmacy.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findAllByPurchaseOrder(PurchaseOrder purchaseOrder);
}
