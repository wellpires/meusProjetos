package br.projetojpa.converters;

import br.projetojpa.models.Marca;
import br.projetojpa.util.JPADao;
import br.projetojpa.util.ProjetoUtils;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@FacesConverter(value = "marcaConverter", forClass = Marca.class)
public class MarcaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty() && ProjetoUtils.isNumerico(value)) {
            try {
                return JPADao.buscarPeloCodigo(Marca.class, value);
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Projeto JPA", ex.getMessage()));
            }

        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && (value instanceof Marca)){
            return String.valueOf(((Marca)value).getCodigo());
        }
        return null;
    }

}
