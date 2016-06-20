/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enderecows.utils;

import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EnderecoUtils {
    
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
