package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository  extends JpaRepository<Complaint, Long> {
}
