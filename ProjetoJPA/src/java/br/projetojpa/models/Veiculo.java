package br.projetojpa.models;

import br.projetojpa.util.ProjetoUtils;
import br.projetojpa.util.ViewConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Entity
@Table(name = "VEICULO")
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long codigo;
    private String fabricante;
    private String modelo;
    private Integer anoFabricacao;
    private Integer anoModelo;
    private BigDecimal valor;
    private TipoCombustivel tipoCombustivel;
    private Date dataCadastro = new Date();
    private byte[] foto;
    private Proprietario proprietario;
    private Marca marca;
    private Set<Acessorios> acessorios = new HashSet<>();

    private UploadedFile caminhoImagem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Column(length = 60, nullable = false)
    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    @Column(length = 60, nullable = false)
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Column(name = "ano_fabricacao", nullable = false)
    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    @Column(name = "ano_modelo", nullable = false)
    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    @Column(precision = 10, scale = 2, nullable = false)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Column(name = "tipo_combustivel", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_cadastro", nullable = false)
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Lob
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "id_proprietario_FK")
    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_marca_FK")
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    @ManyToMany
    @JoinTable(name = "VEICULOS_ACESSORIOS",joinColumns = @JoinColumn(name = "cod_veiculo"), inverseJoinColumns = @JoinColumn(name = "cod_acessorio"))
    public Set<Acessorios> getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Set<Acessorios> acessorios) {
        this.acessorios = acessorios;
    }

    @Transient
    public StreamedContent getImagem() throws FileNotFoundException, IOException {
        String nomeArquivo = null;
        if (getCaminhoImagem() != null) {
            nomeArquivo = getCaminhoImagem().getFileName();
        } else if (getFoto() != null) {
            File file = new File(ViewConstants.CAMINHO_IMAGENS + ProjetoUtils.gerarNumero());
            ProjetoUtils.escreverArquivo(file, getFoto());
            nomeArquivo = file.getName();
        }
        try {
            if (nomeArquivo != null) {
                String arquivo = ViewConstants.CAMINHO_IMAGENS + nomeArquivo;
                File caminhoArquivo = new File(arquivo);
                FileInputStream inputStream = new FileInputStream(arquivo);
                Path path = Paths.get(caminhoArquivo.getAbsolutePath());
                setFoto(Files.readAllBytes(path));
                return new DefaultStreamedContent(inputStream);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Veiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DefaultStreamedContent();

    }

    @Transient
    public StreamedContent getImagemDetalhe() throws FileNotFoundException, IOException {
        if (getFoto() != null) {
            try {
                return new DefaultStreamedContent(new ByteArrayInputStream(getFoto()));
            } catch (Exception ex) {
                Logger.getLogger(Veiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new DefaultStreamedContent();

    }

    @Transient
    public UploadedFile getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(UploadedFile caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

}
