package com.justmeowl.kpacapp.knowledgepackage;

import com.justmeowl.kpacapp.search.Order;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/kpacs")
public class KnowledgePackageController {
    private final KnowledgePackageService knowledgePackageService;

    public KnowledgePackageController(KnowledgePackageService knowledgePackageService) {
        this.knowledgePackageService = knowledgePackageService;
    }

    @GetMapping
    List<KnowledgePackage> getKnowledgePackages() {
        return knowledgePackageService.getAllKnowledgePackages();
    }

    @GetMapping("/{id}")
    KnowledgePackage getKnowledgePackageById(@PathVariable Long id) {
        return knowledgePackageService.getKnowledgePackageById(id);
    }

    @PostMapping
    void addKnowledgePackage(@RequestBody KnowledgePackage knowledgePackage) {
        knowledgePackageService.addKnowledgePackage(knowledgePackage);
    }

    @DeleteMapping("/{id}")
    void deleteKnowledgePackageById(@PathVariable Long id) {
        knowledgePackageService.deleteKnowledgePackageById(id);
    }

    @GetMapping("/search")
    public List<KnowledgePackage> getFilteredAndSortedKnowledgePackages(
            @RequestParam(required = false) Set<Long> id,
            @RequestParam(required = false) Set<String> title,
            @RequestParam(required = false) Set<String> description,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Set<LocalDate> creationDate,
            @RequestParam(required = false) KnowledgePackageField sort,
            @RequestParam(required = false, defaultValue = "ASC") Order order
    ) {
        System.out.println(order);
        // TODO: 28.04.2023
        return knowledgePackageService.getFilteredAndSortedKnowledgePackages(
                id,
                title,
                description,
                creationDate,
                sort,
                order
        );
    }
}