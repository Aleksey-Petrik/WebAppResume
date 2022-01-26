package com.webapp.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String s) {
        return LocalDate.parse(s);
    }

    @Override
    public String marshal(LocalDate localDate) {
        return localDate.toString();
    }
}
