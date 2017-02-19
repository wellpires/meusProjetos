package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.utils.JPAUtil;

public class TesteMovimentacaoConta {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

        Query query = manager.createQuery("select c from Conta c join fetch c.movimentacoes where c.id = :pId");
        query.setParameter("pId", 2L);
        Conta conta = (Conta) query.getSingleResult();

        manager.close();

        System.out.println(conta.getMovimentacoes().size());

	    System.exit(0);
	    
	}

}
