package com.maureva.domain.adapter;

import com.maureva.domain.dto.CabinClass;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CabinClassAdapter extends XmlAdapter<String, CabinClass> {
    @Override
    public CabinClass unmarshal(String cabinClass) {
        return CabinClass.valueOf(cabinClass);
    }

    @Override
    public String marshal(CabinClass cabinClass) {
        return cabinClass.name();
    }

}
