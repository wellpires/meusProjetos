package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.utils.JPAUtil;

public class TestesEstadoJPA {

	public static void main(String[] args) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		Conta conta = manager.find(Conta.class, 1L);
		System.out.println(conta.getTitular());
		
		conta.setTitular("Luiz Ferreira");
		
		System.out.println(conta.getTitular());
		
		manager.getTransaction().commit();
		manager.close();
		
	}
	
}
