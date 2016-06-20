package br.buscarenderecos.helper;

import br.buscarenderecos.model.Endereco;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class DetalharEnderecoViewHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Endereco endereco = null;

    public void inicializar(){
        this.endereco = new Endereco();
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.endereco);
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
        final DetalharEnderecoViewHelper other = (DetalharEnderecoViewHelper) obj;
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        return true;
    }
    
    
}
