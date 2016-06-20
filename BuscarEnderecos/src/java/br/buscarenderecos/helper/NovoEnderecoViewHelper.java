package br.buscarenderecos.helper;

import br.buscarenderecos.model.Endereco;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class NovoEnderecoViewHelper {
    
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
    
}
