package br.projetojpa.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ProjetoUtils {

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

    public static void copiarArquivo(InputStream origem, File destino) throws IOException {

        FileOutputStream fos = new FileOutputStream(destino);

        int count = 0;
        byte[] bytes = new byte[1024];

        while ((count = origem.read(bytes)) >= 0) {
            fos.write(bytes, 0, count);
        }

        origem.close();
        fos.flush();
        fos.close();

    }

    public static void escreverArquivo(File arquivoDestino, byte[] arquivo) throws FileNotFoundException, IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(arquivoDestino)); 
        bos.write(arquivo); 
        bos.close();
    }
    
    public static Long gerarNumero(){
        return new Random().nextLong();
    }

    public static void growlInformacao(String mensagem){
        mostrarGrowl(FacesMessage.SEVERITY_INFO, mensagem);
    }
    
    public static void growlErro(String mensagem){
        mostrarGrowl(FacesMessage.SEVERITY_FATAL, mensagem);
    }
    
    public static void growlAviso(String mensagem){
        mostrarGrowl(FacesMessage.SEVERITY_WARN, mensagem);
    }
    
    private static void mostrarGrowl(FacesMessage.Severity severity, String mensagem){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, "Projeto JPA", mensagem));
    }
    
    public static boolean isNumerico(String valor){
        try{
            double d = Double.parseDouble(valor);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
    
}
