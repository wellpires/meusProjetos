package enderecows.services;

import enderecows.model.Endereco;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface IEnderecoService {
    
    public void inserir(Endereco endereco) throws SQLException, Exception;
    public void excluir(Endereco endereco) throws SQLException, Exception;
    public void alterar(Endereco endereco) throws SQLException, Exception;
    public List<Endereco> listarEnderecos() throws SQLException, Exception;
    public Endereco buscarEndereco(Endereco endereco) throws SQLException, Exception;
    public Endereco buscarEnderecoPorCep(Endereco endereco) throws SQLException, Exception;
}
