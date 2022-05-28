package com.residencia.comercio.dtos;

import java.util.List;

public class CategoriaDTO {
	private Integer idCategoria;
	private String nomeCategoria;
	private List<ProdutoDTO> produtoDTOList;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	public List<ProdutoDTO> getProdutoDTOList() {
		return produtoDTOList;
	}

	public void setProdutoDTOList(List<ProdutoDTO> produtoDTOList) {
		this.produtoDTOList = produtoDTOList;
	}

	@Override
	public String toString() {
		return "CategoriaDTO [idCategoria=" + idCategoria + ", nomeCategoria=" + nomeCategoria + ", produtoDTOList="
				+ produtoDTOList + "]";
	}

	


}
