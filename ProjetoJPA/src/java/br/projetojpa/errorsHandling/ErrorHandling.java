package br.projetojpa.errorsHandling;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@RequestScoped
public class ErrorHandling {
    
      public String getStatusCode(){
        String val = String.valueOf((Integer)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.status_code"));
        return val;
    }
}
