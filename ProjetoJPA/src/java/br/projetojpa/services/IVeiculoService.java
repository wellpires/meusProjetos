package br.projetojpa.services;

import br.projetojpa.models.Veiculo;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface IVeiculoService{

    public List<Veiculo> buscarTodosVeiculos(Class veiculo) throws Exception;

    public Veiculo consultarVeiculo(Veiculo veiculo) throws Exception;

    public void gravar(Object objeto) throws Exception;

    public void alterar(Object objeto) throws Exception;

    public void excluir(Object objeto) throws Exception;

}
