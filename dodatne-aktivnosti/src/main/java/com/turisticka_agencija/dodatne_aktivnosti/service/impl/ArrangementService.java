package com.turisticka_agencija.dodatne_aktivnosti.service.impl;

import com.turisticka_agencija.dodatne_aktivnosti.model.Arrangement;
import com.turisticka_agencija.dodatne_aktivnosti.repository.ArrangementRepository;
import com.turisticka_agencija.dodatne_aktivnosti.service.IArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrangementService implements IArrangementService {

    private final ArrangementRepository arrangementRepository;

    @Autowired
    public ArrangementService(ArrangementRepository arrangementRepository) {
        this.arrangementRepository = arrangementRepository;
    }

    @Override
    public List<Arrangement> findAll() {
        return arrangementRepository.findAll();
    }

    @Override
    public Arrangement findById(Long id) {
        return arrangementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arrangement not found with id: " + id));
    }

    @Override
    public Arrangement save(Arrangement arrangement) {
        return arrangementRepository.save(arrangement);
    }

    @Override
    public Arrangement update(Long id, Arrangement arrangement) {
        Arrangement existingArrangement = findById(id);

        existingArrangement.setName(arrangement.getName());
        existingArrangement.setDestination(arrangement.getDestination());
        existingArrangement.setStartDate(arrangement.getStartDate());
        existingArrangement.setEndDate(arrangement.getEndDate());
        existingArrangement.setPrice(arrangement.getPrice());
        existingArrangement.setCapacity(arrangement.getCapacity());

        return arrangementRepository.save(existingArrangement);
    }

    @Override
    public void delete(Long id) {
        arrangementRepository.deleteById(id);
    }
}