package br.projetojpa.util;

import java.io.File;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ViewConstants {
    
    public static String LABEL_EDITAR = "editar";
    public static String LABEL_SALVAR = "salvar";
    public static String CAMINHO_IMAGENS = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "midia" + File.separator;
    
}
