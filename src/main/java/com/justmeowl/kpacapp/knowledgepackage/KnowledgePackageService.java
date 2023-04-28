package com.justmeowl.kpacapp.knowledgepackage;

import com.justmeowl.kpacapp.exception.ResourceNotFoundException;
import com.justmeowl.kpacapp.search.Order;
import com.justmeowl.kpacapp.search.SearchManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class KnowledgePackageService {
    private final KnowledgePackageRepository knowledgePackageRepository;

    public KnowledgePackageService(KnowledgePackageRepository knowledgePackageRepository) {
        this.knowledgePackageRepository = knowledgePackageRepository;
    }

    public List<KnowledgePackage> getAllKnowledgePackages() {
        return knowledgePackageRepository.findAll();
    }

    public KnowledgePackage getKnowledgePackageById(Long id) {
        return knowledgePackageRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void addKnowledgePackage(KnowledgePackage knowledgePackage) {
        knowledgePackageRepository.save(knowledgePackage);
    }

    public void deleteKnowledgePackageById(Long id) {
        if (!knowledgePackageRepository.existsById(id))
            throw new ResourceNotFoundException();
        knowledgePackageRepository.deleteById(id);
    }

    public List<KnowledgePackage> getFilteredAndSortedKnowledgePackages(Set<Long> id,
                                                                        Set<String> title,
                                                                        Set<String> description,
                                                                        Set<LocalDate> creationDate,
                                                                        KnowledgePackageField sort,
                                                                        Order order) {
        List<KnowledgePackage> knowledgePackages = getAllKnowledgePackages();
        SearchManager<KnowledgePackage> searchManager = new SearchManager<>(knowledgePackages);
        return searchManager.filterByField(KnowledgePackageField.ID, id)
                .filterByField(KnowledgePackageField.TITLE, title)
                .filterByField(KnowledgePackageField.DESCRIPTION, creationDate)
                .filterByField(KnowledgePackageField.CREATION_DATE, description)
                .sortByField(sort, order)
                .search();
    }

}
