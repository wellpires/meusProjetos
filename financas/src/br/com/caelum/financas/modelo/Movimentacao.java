package br.com.caelum.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQuery(name="totalDeMovimentacoes", query="select count(m) from Movimentacao m where m.conta = :pConta")
@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id = null;
	
	private BigDecimal valor = null;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimento tipoMovimentacao = null;
	
	@Temporal(TemporalType.DATE)
	private Calendar data = null;

	private String descricao = null;
	
	@ManyToOne
	private Conta conta = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimento getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimento tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
