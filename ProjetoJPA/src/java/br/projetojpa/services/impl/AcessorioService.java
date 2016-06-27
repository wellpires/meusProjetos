package br.projetojpa.services.impl;

import br.projetojpa.models.Acessorios;
import br.projetojpa.services.IAcessorio;
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
public class AcessorioService implements IAcessorio , Serializable{
    private static final long serialVersionUID = 1L;
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

    @Override
    public List<Acessorios> listarAcessorios(Class acessorio) throws Exception {
        return (List<Acessorios>) JPADao.buscarVariosRegistros(acessorio);
    }
    
}
