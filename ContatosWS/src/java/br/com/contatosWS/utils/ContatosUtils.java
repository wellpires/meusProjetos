package br.com.contatosWS.utils;

import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ContatosUtils {
    
    public static boolean listaDuplaVaziaNula(List<List<String>> lstDados) {

        Boolean listaNulaVazia = false;

        for (List<?> lstValores : lstDados) {

            if (listaVaziaNula(lstValores)) {
                listaNulaVazia = true;
            } else {
                return false;
            }

        }

        return listaNulaVazia;

    }

    public static boolean listaVaziaNula(List<?> lstDados) {

        Integer qtdNulaVazia = 0;

        if (lstDados != null) {
            for (Object valor : lstDados) {
                if (valor == null || "".equals(valor)) {
                    qtdNulaVazia++;
                }
            }
        } else {
            return true;
        }

        return qtdNulaVazia == lstDados.size();
    }
    
}
