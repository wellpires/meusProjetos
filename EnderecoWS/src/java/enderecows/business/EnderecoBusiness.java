package enderecows.business;

import enderecows.model.Endereco;
import enderecows.utils.BancoDados;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EnderecoBusiness {
    
    public void inserir(Endereco endereco) throws SQLException, Exception{
        
        String strQuery = "INSERT INTO ENDERECO "
                + "VALUES"
                + "( '" + endereco.getCep() + "',"
                + "'" + endereco.getRua() + "',"
                + "'" + endereco.getNumero() + "',"
                + "'" + endereco.getCidade() + "',"
                + "'" + endereco.getEstado() + "',"
                + "'" + endereco.getBairro() + "',"
                + "'" + endereco.getComplemento() + "')";
        
        BancoDados.executaComando(strQuery);
        
    }
    
    public void excluir(Endereco endereco) throws SQLException, Exception{
        String strQuery = "DELETE FROM ENDERECO WHERE endereco_id = " + endereco.getEndereco_id().toString();
        BancoDados.executaComando(strQuery);
    }
    
    public void alterar(Endereco endereco) throws SQLException, Exception{
        String strQuery = "UPDATE ENDERECO SET "
                + "cep = '" + endereco.getCep().toString() + "',"
                + "rua = '" + endereco.getRua() + "',"
                + "numero = '" + endereco.getNumero() + "',"
                + "cidade = '" + endereco.getCidade() + "',"
                + "estado = '" + endereco.getEstado() + "',"
                + "bairro = '" + endereco.getBairro() + "',"
                + "complemento = '" + endereco.getComplemento() + "'"
                + " WHERE endereco_id = " + endereco.getEndereco_id().toString();
        
        BancoDados.executaComando(strQuery);
    }
    
    public List<List<String>> listarEnderecos() throws SQLException, Exception{
        String strQuery = "SELECT endereco_id, cep, rua, numero, cidade, estado, bairro, complemento FROM ENDERECO";
        return BancoDados.retorna_N_Registros(strQuery);
    }
    
    public List<String> buscarEndereco(Endereco endereco) throws SQLException, Exception{
        String complemento = "";
        if(endereco.getCep() == null || endereco.getCep() == 0){
            complemento = "endereco_id = " + endereco.getEndereco_id().toString();
        }
        else if(endereco.getCep() != null || endereco.getCep() > 0){
            complemento = "cep = " + endereco.getCep().toString();
        }
        String strQuery = "SELECT endereco_id, cep, rua, numero, cidade, estado, bairro, complemento FROM ENDERECO WHERE " + complemento;
        return BancoDados.retornaRegistro(strQuery);
    }
    
}
