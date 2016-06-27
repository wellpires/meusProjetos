package br.projetojpa.converters;

import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class TimeStampConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if(value == null || "".equals(value)){
            return "";
        }
        
        return new SimpleDateFormat("dd/MM/yyyy hh:MM:ss").format(value);
        
    }
    
}
