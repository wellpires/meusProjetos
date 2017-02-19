package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.utils.JPAUtil;

public class TesteConsulta {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		conta.setId(1L);

		Query query = manager.createQuery("SELECT m FROM Movimentacao m WHERE m.conta.id=" + conta.getId());

		List<Movimentacao> movimentacoes = query.getResultList();

		for (Movimentacao m : movimentacoes) {
			System.out.println("\nDescricao ..: " + m.getDescricao());
			System.out.println("Valor ......: R$ " + m.getValor());
		}

		manager.close();

	}

}
