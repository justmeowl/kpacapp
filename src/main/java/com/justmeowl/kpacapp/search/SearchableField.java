package com.justmeowl.kpacapp.search;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;

public interface SearchableField<T> {
    Predicate<T> getFilterPredicate(Set<?> fieldData);

    Comparator<T> getComparator();
}