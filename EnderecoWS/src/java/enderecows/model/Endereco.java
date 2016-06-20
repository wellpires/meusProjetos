package enderecows.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@Entity
@Table(name = "TBL_ENDERECO")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long endereco_id = 0L;
    private Long cep = 0L;
    private String rua = null;
    private Integer numero = 0;
    private String cidade = null;
    private String estado = null;
    private String bairro = null;
    private String complemento = null;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(Long endereco_id) {
        this.endereco_id = endereco_id;
    }

    @Column(name = "cep", nullable = false)
    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    @Column(name = "rua", length = 100, nullable = false)
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Column(name = "numero", nullable = false)
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer número) {
        this.numero = número;
    }

    @Column(name = "cidade", length = 60, nullable = false)
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Column(name = "estado", length = 20, nullable = false)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "bairro", length = 60)
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(name = "complemento", length = 20)
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.endereco_id);
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.endereco_id, other.endereco_id)) {
            return false;
        }
        return true;
    }

    
    
}
