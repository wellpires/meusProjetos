package br.projetojpa.services.impl;

import br.projetojpa.models.Veiculo;
import br.projetojpa.services.IVeiculoService;
import br.projetojpa.util.JPADao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class VeiculoService implements IVeiculoService, Serializable{
    private static final long serialVersionUID = 1L;

    @Override
    public List<Veiculo> buscarTodosVeiculos(Class veiculo) throws Exception {
        return (List<Veiculo>) JPADao.buscarVariosRegistros(Veiculo.class);
    }

    @Override
    public Veiculo consultarVeiculo(Veiculo veiculo) throws Exception {
        return (Veiculo) JPADao.buscarPeloCodigo(Veiculo.class, veiculo.getCodigo());
    }

    @Override
    public void gravar(Object objeto) throws Exception {
        JPADao.gravar(objeto);
    }

    @Override
    public void alterar(Object objeto) throws Exception {
        JPADao.atualizarInformacoes(objeto);
    }

    @Override
    public void excluir(Object objeto) throws Exception {
        JPADao.removerRegistro(objeto);
    }
    
}
