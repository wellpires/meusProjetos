package br.projetojpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class JPAUtil {
    
    private static EntityManagerFactory factory;
    
    static{
        factory = Persistence.createEntityManagerFactory("ProjetoJPA_PU");
    }
    
    public static EntityManager getEntityManaged(){
        return factory.createEntityManager();
    }
    
    public static void close(){
        factory.close();
    }
    
}
