package com.residencia.comercio.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.comercio.dtos.EstoqueProdutoDTO;
import com.residencia.comercio.entities.Estoque;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.EstoqueRepository;

@Service
public class EstoqueService {
	@Autowired
	private EstoqueRepository estoqueRepository;
	@Autowired
	private ProdutoService produtoService;
	
	public List<Estoque> findAllEstoque() {
		return estoqueRepository.findAll();
	}

	public Estoque findEstoqueById(Integer id) {
		return estoqueRepository.findById(id).isPresent() ? estoqueRepository.findById(id).get() : null;
	}
	
	public List<EstoqueProdutoDTO> findEstoqueProduto() {
		List<Estoque> listEstoque = estoqueRepository.findAll();
		List<EstoqueProdutoDTO>listEstProdDTO = new ArrayList<>();
		if(!listEstoque.isEmpty()) {
			for(Estoque estoque : listEstoque) {
				EstoqueProdutoDTO estProdDTO = new EstoqueProdutoDTO();
				estProdDTO.setIdEstoque(estoque.getIdEstoque());
				estProdDTO.setQuantidade(estoque.getQuantidade());
				estProdDTO.setIdProduto(estoque.getIdProduto());
				if(null == estoque.getProduto()) {
					estProdDTO.setNomeProduto(estoque.getProduto().getNomeProduto());
				}else if(null == estoque.getProduto()&& null != estoque.getIdProduto()) {
					Produto produto = produtoService.findProdutoById(estoque.getIdProduto());
					estProdDTO.setNomeProduto(produto.getNomeProduto());
				}else {
					estProdDTO.setNomeProduto(null);
				}
				
				
				listEstProdDTO.add(estProdDTO);
			}
		}
		return listEstProdDTO;
		}
}
