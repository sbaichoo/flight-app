package com.maureva.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AirportCode {

    CDG("Charles de Gaulle Airport"),
    JFK("John F. Kennedy International Airport"),
    LHR("Heathrow Airport"),
    MUR("Marudi Airport"),
    MRU("Sir Seewoosagur Ramgoolam International Airport"),
    ORY("Paris Orly Airport"),
    RUN("Roland Garros Airport"),
    RRG("Sir Gaëtan Duval Airport"),
    SEZ("Seychelles International Airport"),
    SIN("Singapore Changi Airport"),
    SYD("Sydney Kingsford Smith Airport"),
    TNR("Antananarivo-Ivato Airport"),
    YYZ("Toronto Pearson International Airport"),
    ZSE("Pierrefonds Airport");

    private final String airportName;

}
