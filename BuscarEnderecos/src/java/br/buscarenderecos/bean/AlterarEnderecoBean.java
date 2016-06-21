package br.buscarenderecos.bean;

import br.buscarenderecos.helper.AlterarEnderecoViewHelper;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ViewScoped
@ManagedBean
public class AlterarEnderecoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private AlterarEnderecoViewHelper viewHelper = null;
    private AlterarEnderecoViewHelper viewHelperComparacao = null;
    private ConsumirWS consumirWS = null;

    @PostConstruct
    public void inicializar() {
        try {
            System.out.println(FacesContext.getCurrentInstance().getCurrentPhaseId());
            this.viewHelper = new AlterarEnderecoViewHelper();
            this.viewHelper.inicializar();
            String cep = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ENDERECO_CODIGO");
            viewHelper.getEndereco().setEndereco_id(Long.parseLong(cep));

            consumirWS = new ConsumirWS();
            String json = consumirWS.sendPost(UrlsConstants.BUSCA_REGISTRO, new Gson().toJson(viewHelper.getEndereco()), "POST");
            viewHelper.setEndereco(new Gson().fromJson(json, Endereco.class));

            viewHelperComparacao = new AlterarEnderecoViewHelper();
            viewHelperComparacao.inicializar();
            CRUDEnderecoUtil.clonarGetters(viewHelper.getEndereco(), viewHelperComparacao.getEndereco());

        } catch (Exception ex) {
            CRUDEnderecoUtil.mostrarMensagemError(ex.getLocalizedMessage());
        }

    }

    public String inicializarAlterarEndereco() {
        return BuscarEnderecoNavigation.ALTERAR_ENDERECO;
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

    public void alterar(ActionEvent ae) {
        try {
            if (viewHelper.getEndereco().equals(viewHelperComparacao.getEndereco())) {
                CRUDEnderecoUtil.mostrarMensagemAviso(CRUDEnderecoUtil.getMessage("mensagem_nao_houve_alteracoes"));
                return;
            }

            consumirWS = new ConsumirWS();
            String json = new Gson().toJson(viewHelper.getEndereco());
            String msg = consumirWS.sendPost(UrlsConstants.ALTERAR_REGISTRO, json, "PUT");
            
            if(msg.length() > 0){
                CRUDEnderecoUtil.mostrarMensagemAviso(msg);
                return;
            }
            
            FacesContext.getCurrentInstance().getExternalContext().redirect(BuscarEnderecoNavigation.LISTAR_ENDERECOS.replace("xhtml", "jsf"));
        } catch (Exception ex) {
            CRUDEnderecoUtil.mostrarMensagemError(ex.getLocalizedMessage());
        }
    }

    public AlterarEnderecoViewHelper getViewHelper() {
        return viewHelper;
    }

    public void setViewHelper(AlterarEnderecoViewHelper viewHelper) {
        this.viewHelper = viewHelper;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.viewHelper);
        hash = 67 * hash + Objects.hashCode(this.viewHelperComparacao);
        hash = 67 * hash + Objects.hashCode(this.consumirWS);
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
        final AlterarEnderecoBean other = (AlterarEnderecoBean) obj;
        if (!Objects.equals(this.viewHelper, other.viewHelper)) {
            return false;
        }
        if (!Objects.equals(this.viewHelperComparacao, other.viewHelperComparacao)) {
            return false;
        }
        if (!Objects.equals(this.consumirWS, other.consumirWS)) {
            return false;
        }
        return true;
    }

    
    
}
