package com.justmeowl.kpacapp.knowledgepackage;

import com.justmeowl.kpacapp.search.Convertable;
import com.justmeowl.kpacapp.search.SearchableField;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

public enum KnowledgePackageField
        implements Convertable, SearchableField<KnowledgePackage> {
    ID("id"), TITLE("title"), DESCRIPTION("description"),
    CREATION_DATE("creationDate");

    private final String value;

    KnowledgePackageField(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Predicate<KnowledgePackage> getFilterPredicate(Set<?> fieldData) {
        switch (this) {
            case ID:
                return kpac -> fieldData.contains(kpac.getId());
            case TITLE:
                return kpac -> fieldData.contains(kpac.getTitle());
            case DESCRIPTION:
                return kpac -> fieldData.contains(kpac.getDescription());
            case CREATION_DATE:
                return kpac -> fieldData.contains(kpac.getCreationDate());
            default:
                throw new EnumConstantNotPresentException(this.getClass(), "Enum Constant not Present");
        }
    }

    @Override
    public Comparator<KnowledgePackage> getComparator() {
        Comparator<KnowledgePackage> comparator;
        switch (this) {
            case ID:
                comparator = Comparator.comparingLong(KnowledgePackage::getId);
                break;
            case TITLE:
                comparator = Comparator.comparing(KnowledgePackage::getTitle);
                break;
            case DESCRIPTION:
                comparator = Comparator.comparing(KnowledgePackage::getDescription);
                break;
            case CREATION_DATE:
                comparator = Comparator.comparing(KnowledgePackage::getCreationDate);
                break;
            default:
                throw new EnumConstantNotPresentException(this.getClass(), "Enum Constant not Present");
        }
        return comparator;
    }
}
