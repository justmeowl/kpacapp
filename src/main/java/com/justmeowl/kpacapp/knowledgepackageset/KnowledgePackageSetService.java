package com.justmeowl.kpacapp.knowledgepackageset;

import com.justmeowl.kpacapp.exception.ResourceNotFoundException;
import com.justmeowl.kpacapp.knowledgepackage.KnowledgePackage;
import com.justmeowl.kpacapp.knowledgepackage.KnowledgePackageRepository;
import com.justmeowl.kpacapp.search.Order;
import com.justmeowl.kpacapp.search.SearchManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class KnowledgePackageSetService {
    private final KnowledgePackageSetRepository knowledgePackageSetRepository;
    private final KnowledgePackageRepository knowledgePackageRepository;

    public KnowledgePackageSetService(KnowledgePackageSetRepository knowledgePackageSetRepository,
                                      KnowledgePackageRepository knowledgePackageRepository) {
        this.knowledgePackageSetRepository = knowledgePackageSetRepository;
        this.knowledgePackageRepository = knowledgePackageRepository;
    }
    
    public List<KnowledgePackageSet> getAllKnowledgePackageSets() {
        return knowledgePackageSetRepository.findAll();
    }

    public KnowledgePackageSet getKnowledgePackageSetById(Long id) {
        KnowledgePackageSet knowledgePackageSet = knowledgePackageSetRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        Set<KnowledgePackage> knowledgePackages = knowledgePackageRepository
                .findByKnowledgePackageSetId(knowledgePackageSet.getId());
        knowledgePackageSet.setKnowledgePackages(knowledgePackages);
        return knowledgePackageSet;
    }

    public void addKnowledgePackageSet(KnowledgePackageSet knowledgePackageSet) {
        knowledgePackageSetRepository.save(knowledgePackageSet);
    }

    public void deleteKnowledgePackageSetById(Long id) {
        if (!knowledgePackageSetRepository.existsById(id))
            throw new ResourceNotFoundException();
        knowledgePackageSetRepository.deleteById(id);
    }

    public List<KnowledgePackageSet> getFilteredAndSortedKnowledgePackageSets(
            Set<Long> id,
            Set<String> title,
            KnowledgePackageSetField sort,
            Order order) {
        List<KnowledgePackageSet> knowledgePackageSets = getAllKnowledgePackageSets();
        SearchManager<KnowledgePackageSet> searchManager = new SearchManager<>(knowledgePackageSets);
        return searchManager.filterByField(KnowledgePackageSetField.ID, id)
                .filterByField(KnowledgePackageSetField.TITLE, title)
                .sortByField(sort, order)
                .search();
    }
}
