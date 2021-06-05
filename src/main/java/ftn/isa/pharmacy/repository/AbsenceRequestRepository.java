package ftn.isa.pharmacy.repository;

import ftn.isa.pharmacy.model.AbsenceRequest;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import ftn.isa.pharmacy.model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;


public interface AbsenceRequestRepository extends JpaRepository<AbsenceRequest, Long> {


    List<AbsenceRequest> getAllByTypeOfEmployeeAndStatus(String type, String status);
    List<AbsenceRequest> getAllByPharmacyAndTypeOfEmployeeAndStatus(Pharmacy pharmacy, String type, String status);
    //List<AbsenceRequest> findAllByEmployeeIdAndStartDateAfterAndEndDateBeforeAndStatusIsLike(Long id, Date startDate, Date endDate, String status);
    List<AbsenceRequest> findAllByEmployeeIdAndStartDateBeforeAndEndDateAfterAndStatusIsLike(Long id, Date start, Date end, String status);
    List<AbsenceRequest> findAllByEmployeeIdAndStartDateAfterAndStatusIsNotLike(Long id, Date date, String status);

    List<AbsenceRequest> findAllByEmployeeIdAndStatusIsLikeAndStartDateAfterOrEmployeeIdAndStatusIsLikeAndEndDateAfter(Long id, String status, Date date,Long id2, String status2, Date datee);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select ar from AbsenceRequest ar WHERE ar.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    AbsenceRequest findOneById(@Param("id")Long id);


}
