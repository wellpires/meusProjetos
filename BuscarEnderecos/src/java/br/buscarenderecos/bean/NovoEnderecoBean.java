package br.buscarenderecos.bean;

import br.buscarenderecos.helper.NovoEnderecoViewHelper;
import br.buscarenderecos.model.Endereco;
import br.buscarenderecos.navigation.BuscarEnderecoNavigation;
import br.buscarenderecos.services.ConsumirWS;
import br.buscarenderecos.utils.CRUDEnderecoUtil;
import br.buscarenderecos.utils.UrlsConstants;
import com.google.gson.Gson;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@RequestScoped
@ManagedBean
public class NovoEnderecoBean {

    private NovoEnderecoViewHelper viewHelper = null;
    private ConsumirWS consumirWS = null;

    @PostConstruct
    public void inicializar() {
        viewHelper = new NovoEnderecoViewHelper();
        viewHelper.inicializar();
    }

    public String inicializarNovoEndereco() {
        return BuscarEnderecoNavigation.NOVO_ENDERECO;
    }

    public void buscarCep(ActionEvent ae) {
        try {
            consumirWS = new ConsumirWS();
            String json = consumirWS.sendPost(UrlsConstants.BUSCA_REGISTRO, new Gson().toJson(viewHelper.getEndereco()), "POST");
            Endereco e = new Endereco();

            if (CRUDEnderecoUtil.isJson(e, json)) {
                e = new Gson().fromJson(json, Endereco.class);
                CRUDEnderecoUtil.clonarGetters(e, viewHelper.getEndereco());
            } else {
                CRUDEnderecoUtil.mostrarMensagemAviso(json);
            }
        } catch (Exception ex) {
            CRUDEnderecoUtil.mostrarMensagemError(ex.getLocalizedMessage());
        }
    }

    public void gravar(ActionEvent ae) {

        try {
            consumirWS = new ConsumirWS();
            String msg = consumirWS.sendPost(UrlsConstants.INCLUIR_NOVO_REGISTRO, new Gson().toJson(viewHelper.getEndereco()), "POST");
            
            if(msg.length() > 0){
                CRUDEnderecoUtil.mostrarMensagemAviso(msg);
                return;
            }
            
            FacesContext.getCurrentInstance().getExternalContext().redirect(BuscarEnderecoNavigation.LISTAR_ENDERECOS.replace("xhtml", "jsf"));
        } catch (Exception ex) {
            CRUDEnderecoUtil.mostrarMensagemError(ex.getLocalizedMessage());
        }

    }

    public NovoEnderecoViewHelper getViewHelper() {
        return viewHelper;
    }

    public void setViewHelper(NovoEnderecoViewHelper viewHelper) {
        this.viewHelper = viewHelper;
    }

}
