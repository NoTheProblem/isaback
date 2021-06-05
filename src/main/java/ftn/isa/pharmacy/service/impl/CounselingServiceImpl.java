package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.model.Counseling;
import ftn.isa.pharmacy.repository.CounselingRepository;
import ftn.isa.pharmacy.service.CounselingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounselingServiceImpl implements CounselingService {

    private final CounselingRepository counselingRepository;


    @Autowired
    public CounselingServiceImpl(CounselingRepository counselingRepository) {
        this.counselingRepository = counselingRepository;
    }

    @Override
    public List<Counseling> getAll() {
        return counselingRepository.findAll();
    }
}

