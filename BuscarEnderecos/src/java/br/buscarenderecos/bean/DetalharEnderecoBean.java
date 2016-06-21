package br.buscarenderecos.bean;

import br.buscarenderecos.helper.DetalharEnderecoViewHelper;
import br.buscarenderecos.model.Endereco;
import br.buscarenderecos.navigation.BuscarEnderecoNavigation;
import br.buscarenderecos.services.ConsumirWS;
import br.buscarenderecos.utils.CRUDEnderecoUtil;
import br.buscarenderecos.utils.UrlsConstants;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@RequestScoped
@ManagedBean
public class DetalharEnderecoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private DetalharEnderecoViewHelper viewHelper = null;
    private ConsumirWS consumirWS = null;

    public void inicializar() {
        try {
            this.viewHelper = new DetalharEnderecoViewHelper();
            this.viewHelper.inicializar();
            String cep = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ENDERECO_CODIGO");
            viewHelper.getEndereco().setEndereco_id(Long.parseLong(cep));

            consumirWS = new ConsumirWS();
            String json = consumirWS.sendPost(UrlsConstants.BUSCA_REGISTRO, new Gson().toJson(viewHelper.getEndereco()), "POST");

            if (CRUDEnderecoUtil.isJson(viewHelper.getEndereco(), json)) {
                viewHelper.setEndereco(new Gson().fromJson(json, Endereco.class));
            } else {
                CRUDEnderecoUtil.mostrarMensagemAviso(json);
            }

        } catch (Exception ex) {
            CRUDEnderecoUtil.mostrarMensagemError(ex.getLocalizedMessage());
        }
    }

    public String inicializarDetalharEndereco() {
        return BuscarEnderecoNavigation.DETALHAR_ENDERECO;
    }

    public DetalharEnderecoViewHelper getViewHelper() {
        return viewHelper;
    }

    public void setViewHelper(DetalharEnderecoViewHelper viewHelper) {
        this.viewHelper = viewHelper;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.viewHelper);
        hash = 73 * hash + Objects.hashCode(this.consumirWS);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalharEnderecoBean other = (DetalharEnderecoBean) obj;
        if (!Objects.equals(this.viewHelper, other.viewHelper)) {
            return false;
        }
        if (!Objects.equals(this.consumirWS, other.consumirWS)) {
            return false;
        }
        return true;
    }

}
