package br.buscarenderecos.helper;

import br.buscarenderecos.model.Endereco;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class BuscarEnderecoViewHelper implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Endereco endereco = null;
    private Endereco enderecoSelecionado = null;
    private List<Endereco> lstEnderecos = null;

    public void inicializar(){
        endereco = new Endereco();
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getLstEnderecos() {
        return lstEnderecos;
    }

    public void setLstEnderecos(List<Endereco> lstEnderecos) {
        this.lstEnderecos = lstEnderecos;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.endereco);
        hash = 71 * hash + Objects.hashCode(this.enderecoSelecionado);
        hash = 71 * hash + Objects.hashCode(this.lstEnderecos);
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
        final BuscarEnderecoViewHelper other = (BuscarEnderecoViewHelper) obj;
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.enderecoSelecionado, other.enderecoSelecionado)) {
            return false;
        }
        if (!Objects.equals(this.lstEnderecos, other.lstEnderecos)) {
            return false;
        }
        return true;
    }
    
    
    
}
