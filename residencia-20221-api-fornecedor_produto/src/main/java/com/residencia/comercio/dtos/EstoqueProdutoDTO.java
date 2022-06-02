package com.residencia.comercio.dtos;

public class EstoqueProdutoDTO {
	private Integer IdEstoque;
	private Integer quantidade;
	private Integer idProduto;
	private String nomeProduto;
	
	public Integer getIdEstoque() {
		return IdEstoque;
	}
	public void setIdEstoque(Integer idEstoque) {
		IdEstoque = idEstoque;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	
	
}
