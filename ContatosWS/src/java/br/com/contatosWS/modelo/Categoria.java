package br.com.contatosWS.modelo;

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
@Table(name = "TBL_CATEGORIA")
@Entity
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer categoria_id = null;
    private String categoria_desc = null;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    public Integer getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    @Column(name = "categoria_desc", length = 60, nullable = false)
    public String getCategoria_desc() {
        return categoria_desc;
    }

    public void setCategoria_desc(String categoria_desc) {
        this.categoria_desc = categoria_desc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.categoria_id);
        hash = 71 * hash + Objects.hashCode(this.categoria_desc);
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
        final Categoria other = (Categoria) obj;
        if (!Objects.equals(this.categoria_id, other.categoria_id)) {
            return false;
        }
        if (!Objects.equals(this.categoria_desc, other.categoria_desc)) {
            return false;
        }
        return true;
    }

}
