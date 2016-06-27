package br.projetojpa.services;

import br.projetojpa.models.Acessorios;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface IAcessorio {

    public void gravar(Object objeto) throws Exception;

    public void alterar(Object objeto) throws Exception;

    public void excluir(Object objeto) throws Exception;
    
    public List<Acessorios> listarAcessorios(Class acessorio) throws Exception;

}
