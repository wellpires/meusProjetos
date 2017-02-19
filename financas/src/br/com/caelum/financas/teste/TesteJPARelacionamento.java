package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimento;
import br.com.caelum.financas.utils.JPAUtil;

public class TesteJPARelacionamento {
	public static void main(String[] args) {

		Conta conta = new Conta();
		conta.setId(1L);
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Conta da luz");
		movimentacao.setTipoMovimentacao(TipoMovimento.SAIDA);
		movimentacao.setValor(new BigDecimal("123.9"));
		movimentacao.setConta(conta);

		EntityManager entityManager = new JPAUtil().getEntityManager();

		entityManager.getTransaction().begin();

//		entityManager.persist(conta);
		entityManager.persist(movimentacao);

		entityManager.getTransaction().commit();

		entityManager.close();

	}
}
