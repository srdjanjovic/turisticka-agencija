package com.turisticka_agencija.dodatne_aktivnosti.service.impl;

import com.turisticka_agencija.dodatne_aktivnosti.model.AdditionalActivity;
import com.turisticka_agencija.dodatne_aktivnosti.model.Arrangement;
import com.turisticka_agencija.dodatne_aktivnosti.model.Category;
import com.turisticka_agencija.dodatne_aktivnosti.repository.AdditionalActivityRepository;
import com.turisticka_agencija.dodatne_aktivnosti.repository.ArrangementRepository;
import com.turisticka_agencija.dodatne_aktivnosti.repository.CategoryRepository;
import com.turisticka_agencija.dodatne_aktivnosti.service.IAdditionalActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalActivityService implements IAdditionalActivityService {

    private final AdditionalActivityRepository additionalActivityRepository;
    private final CategoryRepository categoryRepository;
    private final ArrangementRepository arrangementRepository;

    @Autowired
    public AdditionalActivityService(AdditionalActivityRepository additionalActivityRepository,
                                     CategoryRepository categoryRepository,
                                     ArrangementRepository arrangementRepository) {
        this.additionalActivityRepository = additionalActivityRepository;
        this.categoryRepository = categoryRepository;
        this.arrangementRepository = arrangementRepository;
    }

    @Override
    public List<AdditionalActivity> findAll() {
        return additionalActivityRepository.findAll();
    }

    @Override
    public AdditionalActivity findById(Long id) {
        return additionalActivityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Additional activity not found with id: " + id));
    }

    @Override
    public AdditionalActivity save(AdditionalActivity activity) {
        return additionalActivityRepository.save(activity);
    }

    @Override
    public AdditionalActivity update(Long id, AdditionalActivity activity) {
        AdditionalActivity existingActivity = findById(id);

        existingActivity.setName(activity.getName());
        existingActivity.setDescription(activity.getDescription());
        existingActivity.setPrice(activity.getPrice());
        existingActivity.setLocation(activity.getLocation());
        existingActivity.setDuration(activity.getDuration());
        existingActivity.setMaxCapacity(activity.getMaxCapacity());
        existingActivity.setArrangement(activity.getArrangement());
        existingActivity.setCategory(activity.getCategory());

        return additionalActivityRepository.save(existingActivity);
    }

    @Override
    public void delete(Long id) {
        additionalActivityRepository.deleteById(id);
    }

    @Override
    public AdditionalActivity addCategoryToActivity(Long activityId, Long categoryId) {
        AdditionalActivity activity = findById(activityId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        activity.setCategory(category);
        return additionalActivityRepository.save(activity);
    }

    @Override
    public AdditionalActivity removeCategoryFromActivity(Long activityId) {
        AdditionalActivity activity = findById(activityId);
        activity.setCategory(null);
        return additionalActivityRepository.save(activity);
    }

    @Override
    public AdditionalActivity addArrangementToActivity(Long activityId, Long arrangementId) {
        AdditionalActivity activity = findById(activityId);
        Arrangement arrangement = arrangementRepository.findById(arrangementId)
                .orElseThrow(() -> new RuntimeException("Arrangement not found with id: " + arrangementId));

        activity.setArrangement(arrangement);
        return additionalActivityRepository.save(activity);
    }

    @Override
    public AdditionalActivity removeArrangementFromActivity(Long activityId) {
        AdditionalActivity activity = findById(activityId);
        activity.setArrangement(null);
        return additionalActivityRepository.save(activity);
    }
}