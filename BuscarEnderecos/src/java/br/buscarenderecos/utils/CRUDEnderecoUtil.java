package br.buscarenderecos.utils;

import br.buscarenderecos.model.Endereco;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class CRUDEnderecoUtil {

    public static void passarDadosSessao(String nomeSessao, Object dadosSessao) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute(nomeSessao, dadosSessao);

    }

    public static Object pegarDadosSessao(String nomeSessao) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        return session.getAttribute(nomeSessao);

    }

    public static boolean isNumeric(Object valor) {
        try {
            Double d = Double.parseDouble(valor.toString());
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String preencherZerosEsquerda(String valor, int qtdeTotal) {

        int qtdeZeros = 0;
        if (qtdeTotal > valor.length()) {
            qtdeZeros = qtdeTotal - valor.length();
        }

        String zeros = "";
        for (int i = 0; i < qtdeZeros; i++) {
            zeros += "0";
        }

        return zeros + valor;
    }

    public static String getMessage(String key) {
        FacesContext fc = FacesContext.getCurrentInstance();
        String mb = fc.getApplication().getMessageBundle();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(mb, fc.getViewRoot().getLocale());
        return resourceBundle.getString(key);
    }

    public static void mostrarMensagemError(String mensagem) {
        aparecerGrowl(FacesMessage.SEVERITY_ERROR, getMessage("label_titulo_projeto"), mensagem);
    }

    public static void mostrarMensagemFatal(String mensagem) {
        aparecerGrowl(FacesMessage.SEVERITY_FATAL, getMessage("label_titulo_projeto"), mensagem);
    }

    public static void mostrarMensagemInfo(String mensagem) {
        aparecerGrowl(FacesMessage.SEVERITY_INFO, getMessage("label_titulo_projeto"), mensagem);
    }

    public static void mostrarMensagemAviso(String mensagem) {
        aparecerGrowl(FacesMessage.SEVERITY_WARN, getMessage("label_titulo_projeto"), mensagem);
    }

    private static void aparecerGrowl(FacesMessage.Severity severity, String titulo, String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, mensagem));
    }

    public static void clonarGetters(Object objOrigem, Object objDestino) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        for (Method method1 : objOrigem.getClass().getMethods()) {
            for (Method method2 : objDestino.getClass().getMethods()) {
                if (method1.getName().startsWith("get") && method2.getName().startsWith("set")) {
                    if (method1.getName().substring(1).equalsIgnoreCase(method2.getName().substring(1))) {
                        method2.invoke(objDestino, method1.invoke(objOrigem));
                    }
                }
            }
        }
    }

    public static boolean isJson(Object destino, String json){
        try{
            new Gson().fromJson(json,destino.getClass());
            return true;
        }catch(JsonSyntaxException jse){
            return false;
        }
    }
    
}
