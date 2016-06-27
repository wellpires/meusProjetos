package br.com.contatosWS.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@Table(name = "TBL_OPERADORA")
@Entity
public class Operadora implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer operadora_id = null;
    private String operadora_desc = null;
    private Integer codigo = null;
    private Categoria categoria = null;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operadora_id")
    public Integer getOperadora_id() {
        return operadora_id;
    }

    public void setOperadora_id(Integer operadora_id) {
        this.operadora_id = operadora_id;
    }

    @Column(name = "operadora_desc", length = 60, nullable = false)
    public String getOperadora_desc() {
        return operadora_desc;
    }

    public void setOperadora_desc(String operadora_desc) {
        this.operadora_desc = operadora_desc;
    }

    @Column(name = "codigo", nullable = false)
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "categoria_id_FK",nullable = false)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.operadora_id);
        hash = 59 * hash + Objects.hashCode(this.operadora_desc);
        hash = 59 * hash + Objects.hashCode(this.codigo);
        hash = 59 * hash + Objects.hashCode(this.categoria);
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
        final Operadora other = (Operadora) obj;
        if (!Objects.equals(this.operadora_id, other.operadora_id)) {
            return false;
        }
        if (!Objects.equals(this.operadora_desc, other.operadora_desc)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }
    
}
