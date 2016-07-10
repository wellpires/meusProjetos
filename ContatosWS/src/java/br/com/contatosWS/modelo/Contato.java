package br.com.contatosWS.modelo;

import br.com.contatosWS.utils.ContatosUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@Table(name = "TBL_CONTATO")
@Entity
public class Contato implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer contatos_id = null;
    private String nome = null;
    private Long telefone = null;
    private String cor = null;
    private Date dataContato = null;
    private String dataContatoJson = null;
    private Operadora operadora = null;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contatos_id")
    public Integer getContatos_id() {
        return contatos_id;
    }

    public void setContatos_id(Integer contatos_id) {
        this.contatos_id = contatos_id;
    }

    @Column(name = "nome", length = 100, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "telefone", nullable = false)
    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    @Column(name = "cor", length = 10, nullable = false)
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Column(name = "dataContato", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDataContato() {
        return dataContato;
    }

    public void setDataContato(Date data_contato) {
        this.dataContato = data_contato;
    }

    public void setDataContatoJson(String dataContatoJson) {
        this.dataContatoJson = dataContatoJson;
    }
    
    @Transient
    public String getDataContatoJson(){
        return dataContatoJson;
    }
    
    @ManyToOne
    @JoinColumn(name = "operadora_id_FK", nullable = false)
    public Operadora getOperadora() {
        return operadora;
    }

    public void setOperadora(Operadora operadora) {
        this.operadora = operadora;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.contatos_id);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.telefone);
        hash = 79 * hash + Objects.hashCode(this.cor);
        hash = 79 * hash + Objects.hashCode(this.dataContato);
        hash = 79 * hash + Objects.hashCode(this.operadora);
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
        final Contato other = (Contato) obj;
        if (!Objects.equals(this.contatos_id, other.contatos_id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        if (!Objects.equals(this.dataContato, other.dataContato)) {
            return false;
        }
        if (!Objects.equals(this.operadora, other.operadora)) {
            return false;
        }
        return true;
    }

}
