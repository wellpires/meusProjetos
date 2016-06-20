package enderecows.services.impl;

import enderecows.model.Endereco;
import enderecows.services.IEnderecoService;
import enderecows.utils.JPADao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class EnderecoService implements IEnderecoService{

    @Override
    public void inserir(Endereco endereco) throws SQLException, Exception {
        JPADao.gravar(endereco);
    }

    @Override
    public void excluir(Endereco endereco) throws SQLException, Exception {
        JPADao.removerRegistro(endereco);
    }

    @Override
    public void alterar(Endereco endereco) throws SQLException, Exception {
        JPADao.atualizarInformacoes(endereco);
    }

    @Override
    public List<Endereco> listarEnderecos() throws SQLException, Exception {
        return (List<Endereco>) JPADao.buscarVariosRegistros(Endereco.class);
    }

    @Override
    public Endereco buscarEndereco(Endereco endereco) throws SQLException, Exception {
        return (Endereco) JPADao.buscarPeloCodigo(Endereco.class, endereco.getEndereco_id());
    }

    @Override
    public Endereco buscarEnderecoPorCep(Endereco endereco) throws SQLException, Exception {
        return (Endereco) JPADao.buscarPeloCEP(Endereco.class, endereco.getCep());
    }

}