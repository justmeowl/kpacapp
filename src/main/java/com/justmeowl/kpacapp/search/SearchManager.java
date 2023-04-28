package com.justmeowl.kpacapp.search;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchManager<T> {
    private Stream<T> dataStream;

    public SearchManager(List<T> data) {
        this.dataStream = data.stream();
    }

    public SearchManager<T> filterByField(SearchableField<T> field, Set<?> fieldData) {
        if (fieldData != null && field != null) {
            dataStream = dataStream.filter(field.getFilterPredicate(fieldData));
        }
        return this;
    }

    public SearchManager<T> sortByField(SearchableField<T> sortByField, Order order) {
        if (sortByField != null && order != null) {
            Comparator<T> comparator = sortByField.getComparator();
            if (order == Order.DESC) {
                comparator = comparator.reversed();
            }
            dataStream = dataStream.sorted(comparator);
        }
        return this;
    }

    public List<T> search() {
        return dataStream.collect(Collectors.toList());
    }
}
