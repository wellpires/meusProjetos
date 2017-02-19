package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.utils.JPAUtil;

public class TesteJPA {
	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setTitular("João Ferreira");
		conta.setBanco("HSBC");
		conta.setNumero("123345");
		conta.setAgencia("321");
		
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(conta);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		
	}
}
