package br.projetojpa.services;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface IProprietarioService {

    public void gravar(Object objeto) throws Exception;

    public void alterar(Object objeto) throws Exception;

    public void excluir(Object objeto) throws Exception;

}
