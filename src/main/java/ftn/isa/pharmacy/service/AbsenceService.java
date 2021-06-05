package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.AbsenceDTO;
import ftn.isa.pharmacy.model.AbsenceRequest;

import java.util.Collection;

public interface AbsenceService {
    Collection<AbsenceRequest> getAllDermatologistRequests();

    Collection<AbsenceRequest> getAllPharmacistRequests();

    AbsenceRequest getAbsenceByID(Long id);
    AbsenceRequest getAbsenceForUpdate(Long id);

    AbsenceRequest answer(AbsenceDTO absenceDTO);

    Collection<AbsenceRequest> getByEmployeeId(Long id);
}
