package br.projetojpa.converters;

import br.projetojpa.models.Acessorios;
import br.projetojpa.util.JPADao;
import br.projetojpa.util.ProjetoUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@FacesConverter(value = "acessorioConverter", forClass = Acessorios.class)
public class AcessorioConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty() && ProjetoUtils.isNumerico(value)){
            try {
                return JPADao.buscarPeloCodigo(Acessorios.class, value);
            } catch (Exception ex) {
                ProjetoUtils.growlErro(ex.getMessage());
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            return String.valueOf(((Acessorios) value).getCodigo());
        }
        return null;
    }
    
}
