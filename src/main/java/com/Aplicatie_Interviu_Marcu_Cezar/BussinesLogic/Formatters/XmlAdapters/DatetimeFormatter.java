package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Formatters.XmlAdapters;

import org.springframework.stereotype.Component;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatetimeFormatter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss");

    @Override
    public Date unmarshal(String v) throws Exception {
        synchronized (dateFormat){
            return dateFormat.parse(v);
        }
    }
    @Override
    public String marshal(Date v) {
        synchronized (dateFormat){
            return dateFormat.format(v);
        }
    }
}
