package com.justmeowl.kpacapp.knowledgepackageset;

import com.justmeowl.kpacapp.search.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/sets")
public class KnowledgePackageSetController {
    private final KnowledgePackageSetService knowledgePackageSetService;

    public KnowledgePackageSetController(KnowledgePackageSetService knowledgePackageSetService) {
        this.knowledgePackageSetService = knowledgePackageSetService;
    }

    @GetMapping
    List<KnowledgePackageSet> getKnowledgePackages() {
        return knowledgePackageSetService.getAllKnowledgePackageSets();
    }

    @GetMapping("/{id}")
    KnowledgePackageSet getKnowledgePackageById(@PathVariable Long id) {
        return knowledgePackageSetService.getKnowledgePackageSetById(id);
    }

    @PostMapping
    void addKnowledgePackage(@RequestBody KnowledgePackageSet knowledgePackageSet) {
        knowledgePackageSetService.addKnowledgePackageSet(knowledgePackageSet);
    }

    @DeleteMapping("/{id}")
    void deleteKnowledgePackageById(@PathVariable Long id) {
        knowledgePackageSetService.deleteKnowledgePackageSetById(id);
    }

    @GetMapping("/search")
    public List<KnowledgePackageSet> getFilteredAndSortedKnowledgePackages(
            @RequestParam(required = false) Set<Long> id,
            @RequestParam(required = false) Set<String> title,
            @RequestParam(required = false) KnowledgePackageSetField sort,
            @RequestParam(required = false) Order order) {
        return knowledgePackageSetService.getFilteredAndSortedKnowledgePackageSets(
                id,
                title,
                sort,
                order);
    }
}
