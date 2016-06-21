package enderecows.utils;

import enderecows.model.Endereco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class JPADao {

    private static EntityManager manager;
    private static EntityTransaction tx;

    private static void iniciarGerenciador() throws Exception {
        try {
            if (manager == null || !manager.isOpen()) {
                manager = JPAUtil.getEntityManaged();
            }

        } catch (Exception err) {
            manager.close();
            JPAUtil.close();
            throw new Exception(err);
        }
    }

    public static void gravar(Object entidade) throws Exception {
        try {
            iniciarGerenciador();
            
            tx = manager.getTransaction();
            tx.begin();
            manager.persist(entidade);
            tx.commit();
        } catch (Exception erro) {
            tx.rollback();
            throw new Exception(erro);
        } finally {
            manager.close();
        }

    }

    public static Object buscarPeloCodigo(Class classe, Object codigo) throws Exception {
        try {
            iniciarGerenciador();
            return manager.find(classe, Long.parseLong(codigo.toString()));
        } catch(Exception e){
            throw new Exception(e);
        } finally {
            manager.close();
        }

    }

    public static Object buscarPeloCEP(Class classe, Object codigo) throws Exception {
        try {
            iniciarGerenciador();
            Query query = manager.createQuery("SELECT e FROM " + classe.getSimpleName() + " e WHERE e.cep = :cep");
            query.setParameter("cep", codigo);
            return query.getSingleResult();
        } catch (NoResultException nse) {
            throw new Exception("CEP Inválido");
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            manager.close();
        }

    }

    public static List<?> buscarVariosRegistros(Class classe) throws Exception {
        try {
            iniciarGerenciador();
            return manager.createQuery("SELECT e FROM " + classe.getSimpleName() + " e").getResultList();
        }catch(Exception e){
            throw new Exception(e);
        } finally {
            manager.close();
        }
    }

    public static void atualizarInformacoes(Object objetoAtualizado) throws Exception {

        try {

            iniciarGerenciador();
            tx = manager.getTransaction();
            tx.begin();
            manager.merge(objetoAtualizado);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }
    }

    public static void removerRegistro(Object objeto) throws Exception {

        try {
            iniciarGerenciador();
            tx = manager.getTransaction();
            tx.begin();
            Object endereco = manager.find(objeto.getClass(), Long.parseLong(((Endereco) objeto).getEndereco_id().toString()));
            manager.remove(endereco);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new Exception(e);
        } finally {
            manager.close();
        }
    }

}
