package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Formatters.XmlAdapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PriceFormatter extends XmlAdapter<String, BigDecimal> {

    private final DecimalFormat formatter = new DecimalFormat("0.00");

    @Override
    public BigDecimal unmarshal(String v) throws Exception {
        synchronized (formatter){
            return BigDecimal.valueOf(Double.parseDouble(v));
        }
    }
    @Override
    public String marshal(BigDecimal v) throws Exception {
        synchronized (formatter){
            return formatter.format(v);
        }
    }
}
