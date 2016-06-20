package br.buscarenderecos.utils;

import java.text.MessageFormat;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class JSFFunctions {

    public static String getMessage(String key, String params) {
        String parametros[] = params.split("\\,");
        for (int i = 0; i < parametros.length; i++) {
            parametros[i] = CRUDEnderecoUtil.getMessage(parametros[i]);
        }
        MessageFormat messageFormat = new MessageFormat(CRUDEnderecoUtil.getMessage(key), FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return messageFormat.format(parametros);
    }

}
