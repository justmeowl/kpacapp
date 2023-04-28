package com.justmeowl.kpacapp.knowledgepackageset;

import com.justmeowl.kpacapp.search.Convertable;
import com.justmeowl.kpacapp.search.Order;
import com.justmeowl.kpacapp.search.SearchableField;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

public enum KnowledgePackageSetField
        implements Convertable, SearchableField<KnowledgePackageSet> {
    ID("id"), TITLE("title");

    private final String value;

    KnowledgePackageSetField(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Predicate<KnowledgePackageSet> getFilterPredicate(Set<?> fieldData) {
        switch (this) {
            case ID:
                return kpacset -> fieldData.contains(kpacset.getId());
            case TITLE:
                return kpacset -> fieldData.contains(kpacset.getTitle());
            default:
                throw new EnumConstantNotPresentException(this.getClass(), "Enum Constant not Present");
        }
    }

    @Override
    public Comparator<KnowledgePackageSet> getComparator() {
        Comparator<KnowledgePackageSet> comparator;
        switch (this) {
            case ID:
                comparator = Comparator.comparingLong(KnowledgePackageSet::getId);
                break;
            case TITLE:
                comparator = Comparator.comparing(KnowledgePackageSet::getTitle);
                break;
            default:
                throw new EnumConstantNotPresentException(this.getClass(), "Enum Constant not Present");
        }
        return comparator;
    }
}
