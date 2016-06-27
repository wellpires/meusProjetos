package br.projetojpa.bean;

import br.projetojpa.models.Acessorios;
import br.projetojpa.models.Marca;
import br.projetojpa.models.Proprietario;
import br.projetojpa.models.TipoCombustivel;
import br.projetojpa.models.Veiculo;
import br.projetojpa.services.IAcessorio;
import br.projetojpa.services.IMarcaService;
import br.projetojpa.services.IProprietarioService;
import br.projetojpa.services.IVeiculoService;
import br.projetojpa.util.Navegacao;
import br.projetojpa.util.ProjetoUtils;
import br.projetojpa.util.ViewConstants;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class PersistenciaHibernateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Veiculo veiculo = null;
    private Veiculo veiculoSelecionado = null;
    private Marca marca = null;
    private Marca marcaSelecionada = null;
    private Acessorios acessorio = null;
    private Acessorios acessorioSelecionado = null;
    private List<Veiculo> lstVeiculos = null;
    private List<Veiculo> lstVeiculosFiltrados = null;
    private List<TipoCombustivel> lstTipoCombustivel = null;
    private List<Marca> lstMarcas = null;
    private List<Marca> lstMarcasFiltradas = null;
    private List<Acessorios> lstAcessorios = null;
    private List<Acessorios> lstAcessoriosFiltrados = null;
    private List<Acessorios> lstAcessoriosSelecionados = null;
    private String tipoCombustivel = null;
    private Boolean isAlteracao = null;
    private DualListModel<Acessorios> acessorios = null;

    @ManagedProperty("#{veiculoService}")
    private IVeiculoService veiculoService;

    @ManagedProperty("#{proprietarioService}")
    private IProprietarioService proprietarioService;

    @ManagedProperty("#{marcaService}")
    private IMarcaService marcaService;

    @ManagedProperty("#{acessorioService}")
    private IAcessorio acessorioService;

    private Boolean camposAtivos = null;

    @PostConstruct
    public void init() {
        try {
            veiculoSelecionado = new Veiculo();
            lstVeiculos = veiculoService.buscarTodosVeiculos(Veiculo.class);
            lstVeiculosFiltrados = lstVeiculos;
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
    }

    public String inicializarNovoRegistro() {
        try {
            veiculo = new Veiculo();
            veiculo.setProprietario(new Proprietario());
            veiculo.setMarca(new Marca());
            lstAcessoriosSelecionados = new ArrayList<>();
            carregarTipoCombustivel();
            carregarMarcas();
            carregarAcessorios();
            acessorios = new DualListModel<>(lstAcessorios, lstAcessoriosSelecionados);

        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
        return Navegacao.irNovoRegistro();
    }

    public String inicializarCadastro() {
        init();
        return Navegacao.irCadastroRegistro();
    }

    public String inicializarDetalhe() {
        try {
            carregarTipoCombustivel();
            carregarMarcas();
            carregarAcessorios();
            veiculoSelecionado = veiculoService.consultarVeiculo(veiculoSelecionado);
            lstAcessoriosSelecionados = new ArrayList<>(veiculoSelecionado.getAcessorios());
            acessorios = new DualListModel<>(lstAcessorios, lstAcessoriosSelecionados);
            camposAtivos = true;
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
        return Navegacao.irDetalharRegistro();
    }

    public String inicializarNovaMarca() {
        try {
            marca = new Marca();
            marcaSelecionada = new Marca();
            isAlteracao = false;
            carregarMarcas();
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
        return Navegacao.irNovaMarca();
    }

    public String inicializarNovoAcessorio() {
        try {
            acessorio = new Acessorios();
            acessorioSelecionado = new Acessorios();
            lstAcessoriosFiltrados = new ArrayList<>();
            isAlteracao = false;
            carregarAcessorios();
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
        return Navegacao.irNovoAcessorio();
    }

    public String voltarNovoRegistro() {
        try {
            setLstMarcas(marcaService.listarMarcas(Marca.class));
            getVeiculo().setMarca(new Marca());
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
        return Navegacao.irNovoRegistro();
    }

    public String gravar() {
        try {
            if (veiculo.getFoto() == null) {
                ProjetoUtils.growlErro("O campo Imagem é obrigatório");
                return "";
            }

            veiculo.getAcessorios().addAll(acessorios.getTarget());
            proprietarioService.gravar(veiculo.getProprietario());
            veiculoService.gravar(veiculo);
            veiculo = new Veiculo();
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
        return inicializarCadastro();
    }

    public String excluir() {
        try {
            veiculoService.excluir(getVeiculoSelecionado());
            veiculo = new Veiculo();
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
        return inicializarCadastro();
    }

    public String atualizar() {
        try {
            if (camposAtivos == true) {
                camposAtivos = false;
                return "";
            }

            getVeiculoSelecionado().getAcessorios().addAll(acessorios.getTarget());
            Proprietario p = getVeiculoSelecionado().getProprietario();
            proprietarioService.alterar(p);
            veiculoService.alterar(getVeiculoSelecionado());
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
        return inicializarDetalhe();
    }

    public void gravarMarca(ActionEvent ae) {
        try {
            if (isAlteracao) {
                marcaService.alterar(getMarca());
                ProjetoUtils.growlInformacao("Marca alterada com sucesso!");
                isAlteracao = false;
                marcaSelecionada = null;
            } else {
                marcaService.gravar(getMarca());
                ProjetoUtils.growlInformacao("Marca cadastrada com sucesso!");
            }

            carregarMarcas();
            marca = new Marca();
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
    }

    public void excluirMarca(ActionEvent ae) {
        try {
            marcaService.excluir(getMarcaSelecionada());
            carregarMarcas();
            marcaSelecionada = new Marca();
            marca = new Marca();
            isAlteracao = false;
            ProjetoUtils.growlInformacao("Marca excluída com sucesso!");
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
    }

    public void gravarAcessorio(ActionEvent ae) {
        try {
            if (isAlteracao) {
                acessorioService.alterar(getAcessorio());
                isAlteracao = false;
                ProjetoUtils.growlInformacao("Acessório alterado com sucesso!");
                acessorioSelecionado = null;
            } else {
                acessorioService.gravar(getAcessorio());
                ProjetoUtils.growlInformacao("Acessório cadastrado com sucesso!");
            }
            carregarAcessorios();
            acessorio = new Acessorios();
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
    }

    public void excluirAcessorio(ActionEvent ae) {
        try {
            acessorioService.excluir(getAcessorioSelecionado());
            carregarAcessorios();
            acessorioSelecionado = new Acessorios();
            acessorio = new Acessorios();
            isAlteracao = false;
            ProjetoUtils.growlInformacao("Acessório excluído com sucesso!");
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
    }

    public Boolean getDetalharAtivo() {
        return veiculoSelecionado != null && veiculoSelecionado.getCodigo() != null && veiculoSelecionado.getCodigo() > 0;
    }

    public Boolean getExcluirMarca() {
        return marcaSelecionada != null && marcaSelecionada.getCodigo() != null && marcaSelecionada.getCodigo() > 0;
    }

    public Boolean getExcluirAcessorio() {
        return acessorioSelecionado != null && acessorioSelecionado.getCodigo() != null && acessorioSelecionado.getCodigo() > 0;
    }

    public String getNomeBotao() {
        return camposAtivos ? ViewConstants.LABEL_EDITAR : ViewConstants.LABEL_SALVAR;
    }

    public void carregar(FileUploadEvent event) {
        try {
            String arquivo = ViewConstants.CAMINHO_IMAGENS + event.getFile().getFileName();
            ProjetoUtils.copiarArquivo(event.getFile().getInputstream(), new File(arquivo));
            getVeiculo().setCaminhoImagem(event.getFile());
        } catch (IOException ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }

    }

    public void carregarDetalhe(FileUploadEvent event) {
        try {
            String arquivo = ViewConstants.CAMINHO_IMAGENS + event.getFile().getFileName();
            ProjetoUtils.copiarArquivo(event.getFile().getInputstream(), new File(arquivo));
            getVeiculoSelecionado().setCaminhoImagem(event.getFile());
        } catch (IOException ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }

    }

    public void alterarFlagMarca(SelectEvent se) {
        try {
            isAlteracao = true;
            marca = marcaSelecionada;
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
    }

    public void alterarFlagAcessorio(SelectEvent se) {
        try {
            isAlteracao = true;
            acessorio = acessorioSelecionado;
        } catch (Exception ex) {
            ProjetoUtils.growlErro(ex.getMessage());
        }
    }

    private void carregarTipoCombustivel() {
        lstTipoCombustivel = new ArrayList<>();
        lstTipoCombustivel.addAll(Arrays.asList(TipoCombustivel.values()));
    }

    private void carregarMarcas() throws Exception {
        lstMarcas = marcaService.listarMarcas(Marca.class);
    }

    private void carregarAcessorios() throws Exception {
        lstAcessorios = acessorioService.listarAcessorios(Acessorios.class);
    }

    public void setDescreverNomesAcessorios(String nome) {

    }

    public String getDescreverNomesAcessorios() {
        String nomesAcessorios = "";
        for (int i = 0; i < acessorios.getTarget().size(); i++) {
            if (acessorios.getTarget().size() >= 2) {
                if (i == acessorios.getTarget().size() - 2) {
                    nomesAcessorios += acessorios.getTarget().get(i).getDescricao();
                } else if (i == acessorios.getTarget().size() - 1) {
                    nomesAcessorios = nomesAcessorios + " e " + acessorios.getTarget().get(i).getDescricao();
                } else {
                    nomesAcessorios += acessorios.getTarget().get(i).getDescricao() + ", ";
                }
            } else {
                nomesAcessorios = acessorios.getTarget().get(i).getDescricao();
            }
        }

        return nomesAcessorios;
    }

    //apagar
    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Acessorios) item).getDescricao()).append("<br />");
        }

    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<Veiculo> getLstVeiculos() {
        return lstVeiculos;
    }

    public void setLstVeiculos(List<Veiculo> lstVeiculos) {
        this.lstVeiculos = lstVeiculos;
    }

    public List<Veiculo> getLstVeiculosFiltrados() {
        return lstVeiculosFiltrados;
    }

    public void setLstVeiculosFiltrados(List<Veiculo> lstVeiculosFiltrados) {
        this.lstVeiculosFiltrados = lstVeiculosFiltrados;
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }

    public Boolean getCamposAtivos() {
        return camposAtivos;
    }

    public void setCamposAtivos(Boolean camposAtivos) {
        this.camposAtivos = camposAtivos;
    }

    public List<TipoCombustivel> getLstTipoCombustivel() {
        return lstTipoCombustivel;
    }

    public void setLstTipoCombustivel(List<TipoCombustivel> lstTipoCombustivel) {
        this.lstTipoCombustivel = lstTipoCombustivel;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public IVeiculoService getVeiculoService() {
        return veiculoService;
    }

    public void setVeiculoService(IVeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    public IProprietarioService getProprietarioService() {
        return proprietarioService;
    }

    public void setProprietarioService(IProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    public List<Marca> getLstMarcas() {
        return lstMarcas;
    }

    public void setLstMarcas(List<Marca> lstMarcas) {
        this.lstMarcas = lstMarcas;
    }

    public List<Marca> getLstMarcasFiltradas() {
        return lstMarcasFiltradas;
    }

    public void setLstMarcasFiltradas(List<Marca> lstMarcasFiltradas) {
        this.lstMarcasFiltradas = lstMarcasFiltradas;
    }

    public IMarcaService getMarcaService() {
        return marcaService;
    }

    public void setMarcaService(IMarcaService marcaService) {
        this.marcaService = marcaService;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Marca getMarcaSelecionada() {
        return marcaSelecionada;
    }

    public void setMarcaSelecionada(Marca marcaSelecionada) {
        this.marcaSelecionada = marcaSelecionada;
    }

    public IAcessorio getAcessorioService() {
        return acessorioService;
    }

    public void setAcessorioService(IAcessorio acessorioService) {
        this.acessorioService = acessorioService;
    }

    public Acessorios getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorios acessorio) {
        this.acessorio = acessorio;
    }

    public Acessorios getAcessorioSelecionado() {
        return acessorioSelecionado;
    }

    public void setAcessorioSelecionado(Acessorios acessorioSelecionado) {
        this.acessorioSelecionado = acessorioSelecionado;
    }

    public Boolean getIsAlteracao() {
        return isAlteracao;
    }

    public void setIsAlteracao(Boolean isAlteracao) {
        this.isAlteracao = isAlteracao;
    }

    public List<Acessorios> getLstAcessorios() {
        return lstAcessorios;
    }

    public void setLstAcessorios(List<Acessorios> lstAcessorios) {
        this.lstAcessorios = lstAcessorios;
    }

    public List<Acessorios> getLstAcessoriosFiltrados() {
        return lstAcessoriosFiltrados;
    }

    public void setLstAcessoriosFiltrados(List<Acessorios> lstAcessoriosFiltrados) {
        this.lstAcessoriosFiltrados = lstAcessoriosFiltrados;
    }

    public DualListModel<Acessorios> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(DualListModel<Acessorios> acessorios) {
        this.acessorios = acessorios;
    }

    public List<Acessorios> getLstAcessoriosSelecionados() {
        return lstAcessoriosSelecionados;
    }

    public void setLstAcessoriosSelecionados(List<Acessorios> lstAcessoriosSelecionados) {
        this.lstAcessoriosSelecionados = lstAcessoriosSelecionados;
    }

    
}
