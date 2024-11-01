package com.pigeonskyrace.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ORGANISATEUR,
    ELEVEUR;

    @JsonCreator
    public static Role fromValue(String value) {
        try {
            return Role.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + value);
        }
    }


    @JsonValue
    public String toValue() {
        return this.name();
    }
}
