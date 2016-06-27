package br.projetojpa.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
            throw err;
        }
    }

    public static void gravar(Object entidade) throws Exception {
        iniciarGerenciador();

        tx = manager.getTransaction();
        tx.begin();
        try {
            manager.persist(entidade);
        } catch (Exception erro) {
            tx.rollback();
            throw erro;
        }
        tx.commit();

    }

    public static Object buscarPeloCodigo(Class classe, Object codigo) throws Exception {

        iniciarGerenciador();
        return manager.find(classe, Long.parseLong(codigo.toString()));

    }

    public static List<?> buscarVariosRegistros(Class classe) throws Exception {

        iniciarGerenciador();
        return manager.createQuery("FROM " + classe.getName()).getResultList();
    }

    public static void atualizarInformacoes(Object objetoAtualizado) throws Exception {

        iniciarGerenciador();
        tx = manager.getTransaction();
        tx.begin();

        tx.commit();
    }

    public static void removerRegistro(Object objeto) throws Exception {

        iniciarGerenciador();
        tx = manager.getTransaction();
        tx.begin();
        manager.remove(objeto);
        
        tx.commit();

    }

}
