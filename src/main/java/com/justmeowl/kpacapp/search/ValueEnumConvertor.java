package com.justmeowl.kpacapp.search;

import org.springframework.core.convert.converter.Converter;

public class ValueEnumConvertor<T extends Enum<T> & Convertable> implements Converter<String, T> {
    private final Class<T> enumClass;

    public ValueEnumConvertor(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public T convert(String from) {
        for (T enumConst : enumClass.getEnumConstants()) {
            if (from.equalsIgnoreCase(enumConst.getValue()))
                return enumConst;
        }
        throw new IllegalArgumentException("Invalid Argument");
    }
}
