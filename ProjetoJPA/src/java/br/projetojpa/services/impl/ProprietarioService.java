package br.projetojpa.services.impl;

import br.projetojpa.services.IProprietarioService;
import br.projetojpa.util.JPADao;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class ProprietarioService implements IProprietarioService,Serializable{
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
