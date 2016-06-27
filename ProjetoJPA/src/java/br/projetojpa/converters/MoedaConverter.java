package br.projetojpa.converters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class MoedaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || "".equals(value)){
            return "";
        }
        
        return new BigDecimal(value.replaceAll("\\.","").replaceAll("\\,","."));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value == null || "".equals(value)){
            return "";
        }
        
        return new DecimalFormat("#,##0.00").format(new BigDecimal(value.toString()));
    }

}
