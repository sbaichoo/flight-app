package com.maureva.domain.dto.criteria;

import java.io.Serializable;

public record FlightCriteria(String origin, String destination) implements Serializable {
}
