package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.AbsenceDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.AbsenceRequestRepository;
import ftn.isa.pharmacy.repository.PharmacyAdminRepository;
import ftn.isa.pharmacy.repository.UserRepository;
import ftn.isa.pharmacy.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRequestRepository absenceRequestRepository;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final UserRepository userRepository;
    private final MailServiceImpl mailService;

    @Autowired
    public AbsenceServiceImpl(AbsenceRequestRepository absenceRequestRepository,
                              PharmacyAdminRepository pharmacyAdminRepository, UserRepository userRepository,
                              MailServiceImpl mailService) {
        this.absenceRequestRepository = absenceRequestRepository;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }


    @Override
    public Collection<AbsenceRequest> getAllDermatologistRequests() {
        return absenceRequestRepository.getAllByTypeOfEmployeeAndStatus("ROLE_DERMATOLOGIST","nov");
    }
    @Override
    public Collection<AbsenceRequest> getAllPharmacistRequests() {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        return absenceRequestRepository.getAllByPharmacyAndTypeOfEmployeeAndStatus(pharmacyAdmin.getPharmacy(),"ROLE_PHARMACIST","nov");
    }

    @Override
    public AbsenceRequest getAbsenceByID(Long id) {
        return absenceRequestRepository.getOne(id);
    }

    @Transactional(readOnly = false)
    public AbsenceRequest getAbsenceForUpdate(Long id){
        return absenceRequestRepository.findOneById(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AbsenceRequest answer(AbsenceDTO absenceDTO) {
        Long adminID = getAdminID();
        AbsenceRequest absenceRequest = getAbsenceForUpdate(absenceDTO.getId());
        if(!absenceRequest.getStatus().equals("nov")){
            throw new ResourceConflictException(1L,"Promenjen u medjuvremenu!");
        }
        absenceRequest.setStatus(absenceDTO.getStatus());
        if(absenceDTO.getStartDate().equals("Odbijeno")){
            absenceRequest.setAnswerText(absenceDTO.getAnswerText());
            mailService.absenceDeclinedNotification(absenceRequest);
        }
        else {
            mailService.absenceAcceptedNotification(absenceRequest);
        }
        absenceRequest.setAdminId(adminID);
        absenceRequestRepository.save(absenceRequest);
        return absenceRequest;
    }

    @Override
    public Collection<AbsenceRequest> getByEmployeeId(Long id) {
        Date date = new Date();
        return absenceRequestRepository.findAllByEmployeeIdAndStatusIsLikeAndStartDateAfterOrEmployeeIdAndStatusIsLikeAndEndDateAfter(id,"Odobreno",date,id,"Odobreno", date);
    }


    private Long getAdminID(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findById(((User) authentication.getPrincipal()).getId());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getId();
        }
        throw new ResourceConflictException(1L,"Ne postoji administrator apoteke!");
    }

    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            return pharmacyAdminOptional.get();
        }
        throw new ResourceConflictException(1L,"Ne postoji administrator apoteke!");
    }
}
