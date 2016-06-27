package br.com.contatosWS.utils;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class JPAUtil implements Serializable {
    public static final long serialVersionUID = 1L;
    
    private static EntityManagerFactory factory;
    
    static{
        System.out.println("======================== INICIALIZANDO ContatosWSPU ========================");
        factory = Persistence.createEntityManagerFactory("ContatosWSPU");
        System.out.println("============================================================================");
    }
    
    public static EntityManager getEntityManaged(){
        return factory.createEntityManager();
    }
    
    public static void close(){
        factory.close();
    }
    
    
    
}
