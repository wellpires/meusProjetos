package br.com.caelum.financas.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.utils.JPAUtil;

public class TesteConsultaFuncaoMax {

	public static void main(String[] args) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
	    Conta conta = new Conta();
	    conta.setId(2L);
	    
	    TypedQuery<BigDecimal> query = manager.createQuery("select max(m.valor) from Movimentacao m where m.conta = :pConta", BigDecimal.class);
	    
	    query.setParameter("pConta", conta);
	    BigDecimal quantidade = query.getSingleResult();
	    System.out.println("Total: " + quantidade);
	    
	    manager.close();
		
	}

}