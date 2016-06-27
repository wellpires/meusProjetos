package br.projetojpa.services.impl;

import br.projetojpa.models.Marca;
import br.projetojpa.services.IMarcaService;
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
public class MarcaService implements IMarcaService, Serializable{
    private static final long serialVersionUID = 1L;

    @Override
    public void gravar(Marca marca) throws Exception {
        JPADao.gravar(marca);
    }

    @Override
    public List<Marca> listarMarcas(Class marca) throws Exception {
        return (List<Marca>) JPADao.buscarVariosRegistros(marca);
    }

    @Override
    public void excluir(Marca marca) throws Exception {
       JPADao.removerRegistro(marca);
    }

    @Override
    public void alterar(Marca marca) throws Exception {
        JPADao.atualizarInformacoes(marca);
    }
    
}
