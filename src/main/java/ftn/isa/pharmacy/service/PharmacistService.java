package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.PharmacistDTO;
import ftn.isa.pharmacy.model.Pharmacist;

import java.util.Collection;

public interface PharmacistService {
    Collection<PharmacistDTO> getAll();

    Collection<PharmacistDTO> getAllFree();

    void addToPharmacy(PharmacistDTO pharmacistDTO);

    void registerToPharmacy(PharmacistDTO pharmacistDTO);

}
