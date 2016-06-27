package br.projetojpa.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@FacesValidator()
public class AcessorioValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if(value == null || "".equals(value)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Projeto JPA", "Escolha ao menos um acessório"));
        }
    }
    
}
