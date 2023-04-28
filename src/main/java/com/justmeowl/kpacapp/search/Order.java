package com.justmeowl.kpacapp.search;

public enum Order implements Convertable {
    ASC("asc"), DESC("desc");

    private final String value;

    Order(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
