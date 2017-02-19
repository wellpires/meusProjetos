package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.utils.JPAUtil;

public class TesteContaMovimentacoes {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		
	}

}
