package br.buscarenderecos.converters;

import br.buscarenderecos.utils.CRUDEnderecoUtil;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@FacesConverter(value = "cepConverter")
public class CepConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null) {
            return "";
        }

        String cepSemFormatacao = value.replaceAll("\\-", "");
        if(!CRUDEnderecoUtil.isNumeric(cepSemFormatacao)){
            return "";
        }
        
        return new Long(cepSemFormatacao);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return "";
        }
        
        String cep = value.toString();
        if (cep.contains("-")) {
            if (cep.length() < 9) {
                cep = CRUDEnderecoUtil.preencherZerosEsquerda(cep, 9);
            }
        } else {
            try {
                if (cep.length() < 8) {
                    cep = CRUDEnderecoUtil.preencherZerosEsquerda(cep, 8);
                }

                MaskFormatter mf = new MaskFormatter("#####-###");
                mf.setValueContainsLiteralCharacters(false);
                cep = mf.valueToString(cep);
            } catch (ParseException ex) {
                Logger.getLogger(CepConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return cep;
    }

}
