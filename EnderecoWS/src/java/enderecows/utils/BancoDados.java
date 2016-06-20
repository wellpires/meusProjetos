package enderecows.utils;

/*
 * @Author: Wellington Gonçalves Pires
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoDados {

    private static Statement sta;
    private static ResultSet rs;
    private static Connection con = null;
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String caminho;
    private static String usuario;
    private static String senha;

    /**
     * @throws ClassNotFoundException, SQLException, Exception,
     * FileNotFoundException e IOException
     *
     *
     */
    private static void conexao() throws ClassNotFoundException, SQLException, Exception, FileNotFoundException, IOException {

        StringConexao conexao = new StringConexao();

        conexao.lerBancoDados();
        caminho = conexao.getCaminho();
        usuario = conexao.getUsuario();
        senha = conexao.getSenha();

        Class.forName(DRIVER);

        con = DriverManager.getConnection(caminho, usuario, senha);
        sta = con.createStatement();

    }

    public static void executaComando(String sql) throws ClassNotFoundException, SQLException, Exception {

        /* EXECUTA COMANDOS SEM RETORNO DE INFORMAÇÕES
         * EXEMPLOS: DELETE, INSERT, UPDATE E ETC...
         */
        try {
            conexao();

            sta.executeUpdate(sql);
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException(ex.getLocalizedMessage(), ex);
        } catch (SQLException ex) {
            throw new SQLException(ex.getLocalizedMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            fecharConexoes();
        }

    }

    public static Integer inserirRetornaID(String sql) throws ClassNotFoundException, SQLException, Exception {
        /*
         *EFETUA A INSERÇÃO E RETORNA O CÓDIGO QUE FOI INSERIDO
         */
        Integer ID = 0;

        try {

            conexao();

            sta.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            rs = sta.getGeneratedKeys();

            while (rs.next()) {
                ID = Integer.parseInt(rs.getObject(1).toString());
            }

        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException(ex.getLocalizedMessage(), ex);
        } catch (SQLException ex) {
            throw new SQLException(ex.getLocalizedMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            fecharConexoes();

        }

        return ID;
    }

    public static List<List<String>> retorna_N_Registros(String sql) throws ClassNotFoundException, SQLException, Exception {

        // RETORNA VÁRIOS REGISTROS
        List<List<String>> lstRegistros = new ArrayList<>();
        try {
            List<String> lstValores = new ArrayList<>();

            conexao();

            rs = sta.executeQuery(sql);

            int coluna = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= coluna; i++) {

                    lstValores.add(rs.getString(i));
                }

                lstRegistros.add(lstValores);
                lstValores = new ArrayList<>();
            }

        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException(ex.getLocalizedMessage(), ex);
        } catch (SQLException ex) {
            throw new SQLException(ex.getLocalizedMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            fecharConexoes();
        }
        return lstRegistros;

    }

    public static List<String> retornaRegistro(String sql) throws ClassNotFoundException, SQLException, Exception {

        //RETORNA UM REGISTRO
        List<String> lstRegistros = new ArrayList<>();
        try {

            conexao();

            rs = sta.executeQuery(sql);

            int coluna = rs.getMetaData().getColumnCount();

            while (rs.next()) {

                for (int i = 0; i < coluna; i++) {
                    lstRegistros.add(rs.getString(i + 1));

                }
            }

        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException(ex.getLocalizedMessage(), ex);
        } catch (SQLException ex) {
            throw new SQLException(ex.getLocalizedMessage(), ex);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            fecharConexoes();
        }

        return lstRegistros;

    }

    private static void fecharConexoes() throws SQLException, Exception {

        fecharResultSet();
        fecharStatement();
        fecharConexaoBanco();

    }

    private static void fecharResultSet() throws SQLException, Exception {

        if (rs != null && rs.isClosed() == false) {
            rs.close();
        }

    }

    private static void fecharStatement() throws SQLException, Exception {

        if (sta.isClosed() == false) {
            sta.close();
        }

    }

    private static void fecharConexaoBanco() throws SQLException, Exception {

        if (con.isClosed() == false) {
            con.close();
        }

    }

}
