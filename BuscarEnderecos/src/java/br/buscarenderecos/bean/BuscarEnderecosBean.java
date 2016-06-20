package br.buscarenderecos.bean;

import br.buscarenderecos.helper.BuscarEnderecoViewHelper;
import br.buscarenderecos.model.Endereco;
import br.buscarenderecos.navigation.BuscarEnderecoNavigation;
import br.buscarenderecos.services.ConsumirWS;
import br.buscarenderecos.utils.CRUDEnderecoUtil;
import br.buscarenderecos.utils.UrlsConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ViewScoped
@ManagedBean
public class BuscarEnderecosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private BuscarEnderecoViewHelper viewHelper = null;
    private ConsumirWS consumirWS = null;

    @PostConstruct
    public void inicializar() {
        inicializarListar();
    }

    public String inicializarListar() {
        try {
            viewHelper = new BuscarEnderecoViewHelper();
            consumirWS = new ConsumirWS();
            viewHelper.inicializar();

            String json = consumirWS.sendGet(UrlsConstants.LISTAR_TODOS_ENDERECOS);

            viewHelper.setLstEnderecos(new Gson().fromJson(json, new TypeToken<List<Endereco>>() {
            }.getType()));

        } catch (Exception ex) {
            CRUDEnderecoUtil.mostrarMensagemError(ex.getLocalizedMessage());
        }

        return BuscarEnderecoNavigation.LISTAR_ENDERECOS;
    }

    public void excluirEndereco(ActionEvent ae) {
        try {
            consumirWS = new ConsumirWS();
            Endereco e = new Endereco();
            e.setEndereco_id(viewHelper.getEnderecoSelecionado().getEndereco_id());
            String json = new Gson().toJson(e);
            consumirWS.sendPost(UrlsConstants.EXCLUIR_REGISTRO, json, "POST");
        } catch (Exception ex) {
            CRUDEnderecoUtil.mostrarMensagemError(ex.getLocalizedMessage());
        }
    }

    public Boolean getBotoesAtivos() {
        return viewHelper.getEnderecoSelecionado() != null;
    }

    public BuscarEnderecoViewHelper getViewHelper() {
        return viewHelper;
    }

    public void setViewHelper(BuscarEnderecoViewHelper viewHelper) {
        this.viewHelper = viewHelper;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.viewHelper);
        hash = 97 * hash + Objects.hashCode(this.consumirWS);
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
        final BuscarEnderecosBean other = (BuscarEnderecosBean) obj;
        if (!Objects.equals(this.viewHelper, other.viewHelper)) {
            return false;
        }
        if (!Objects.equals(this.consumirWS, other.consumirWS)) {
            return false;
        }
        return true;
    }

}
