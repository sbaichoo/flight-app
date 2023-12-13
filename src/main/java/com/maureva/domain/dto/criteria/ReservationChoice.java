package com.maureva.domain.dto.criteria;

import com.maureva.domain.dto.CabinClass;

import java.io.Serializable;

public record ReservationChoice(Integer key, CabinClass cabinClass) implements Serializable {
}
