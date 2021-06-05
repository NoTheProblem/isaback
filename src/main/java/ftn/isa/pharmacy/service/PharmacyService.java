package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.dto.PharmacyDto;
import ftn.isa.pharmacy.model.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PharmacyService {

    List<Pharmacy> getAll();
    Pharmacy getById(Long id);
    Pharmacy getPharmacyByAdmin();
    Boolean subscribe(Long id);
    Set<Dermatologist> getDermaByPhaID(Long id);
    Set<Pharmacist> getPharmaByPhaID(Long id);
    Collection<DermatologistDto> getDermaForPhaAdmin();

    void addPharmacy(PharmacyDto pharmacyDto);

    void updatePharmacyInfo(PharmacyDto pharmacyDto);

    Collection<Medicine> getAvailableMedicines(Long id);

    Collection<Examination> getAvailableExaminations(Long id);

    void deleteEmployee(Long id, String type);
}
