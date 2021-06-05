package ftn.isa.pharmacy.service;

import ftn.isa.pharmacy.dto.DermatologistDto;
import ftn.isa.pharmacy.model.Dermatologist;
import ftn.isa.pharmacy.model.Examination;

import java.util.Collection;
import java.util.List;

public interface DermatologistService {

    public List<DermatologistDto> getAll();

    Collection<DermatologistDto> getAllDermatologistsCandidates();

    void addToPharmacy(DermatologistDto dermatologistDto);
}
