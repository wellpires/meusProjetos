package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.utils.JPAUtil;

public class TesteConsultaFuncaoCount {

	public static void main(String[] args) {
		EntityManager manager = new JPAUtil().getEntityManager();
	    Conta conta = new Conta();
	    conta.setId(2L);
	    
	    TypedQuery<Long> query = manager.createQuery("select count(m) from Movimentacao m where m.conta = :pConta", Long.class);
	    
	    query.setParameter("pConta", conta);
	    Long quantidade =  query.getSingleResult();
	    System.out.println("Total de movimentações: " + quantidade);
	    
	    manager.close();
	}

}
