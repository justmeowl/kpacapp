package com.justmeowl.kpacapp.knowledgepackageset;

import com.justmeowl.kpacapp.knowledgepackage.KnowledgePackage;

import java.util.Set;

public class KnowledgePackageSet {
    private Long id;
    private String title;
    private Set<KnowledgePackage> knowledgePackages;

    public KnowledgePackageSet() {
    }

    public KnowledgePackageSet(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<KnowledgePackage> getKnowledgePackages() {
        return knowledgePackages;
    }

    public void setKnowledgePackages(Set<KnowledgePackage> knowledgePackages) {
        this.knowledgePackages = knowledgePackages;
    }
}
