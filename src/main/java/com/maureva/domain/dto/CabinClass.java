package com.maureva.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CabinClass {
    FIRST("First"),
    PREMIUM_ECONOMY("PremiumEconomy"),
    ECONOMY("Economy");

    private String value;

    @Override
    public String toString() {
        return value;
    }

}
