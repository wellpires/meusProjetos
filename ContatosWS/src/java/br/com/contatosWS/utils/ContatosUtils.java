package br.com.contatosWS.utils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static Date jsonDateToJavaDate(String dataContato) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        df.setDateFormatSymbols(DateFormatSymbols.getInstance(new Locale("pt-BR")));

        if (dataContato.endsWith("Z")) {
            dataContato = dataContato.substring(0, dataContato.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;

            String s0 = dataContato.substring(0, dataContato.length() - inset);
            String s1 = dataContato.substring(dataContato.length() - inset, dataContato.length());

            dataContato = s0 + "GMT" + s1;
        }
        return df.parse(dataContato);
    }
    
    public static String javaDateToJsonDate(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        df.setTimeZone(tz);

        String output = df.format(date);

        int inset0 = 9;
        int inset1 = 6;

        String s0 = output.substring(0, output.length() - inset0);
        String s1 = output.substring(output.length() - inset1, output.length());

        String result = s0 + s1;

        result = result.replaceAll("UTC", "+00:00");

        return result;

    }
    
}
