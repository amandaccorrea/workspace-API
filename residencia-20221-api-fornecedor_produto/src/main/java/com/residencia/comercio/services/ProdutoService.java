package com.residencia.comercio.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.comercio.dtos.CategoriaDTO;
import com.residencia.comercio.dtos.FornecedorDTO;
import com.residencia.comercio.dtos.ProdutoDTO;
import com.residencia.comercio.entities.Categoria;
import com.residencia.comercio.entities.Fornecedor;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CategoriaService categoriaService;
	@Autowired
	FornecedorService fornecedorService;
	
	//@Value("${files.folder.path}")
	//private Path path;
	
	public List<Produto> findAllProduto(){
		return produtoRepository.findAll();
	}
	
	public Produto findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ?
				produtoRepository.findById(id).get() : null;
	}

	public ProdutoDTO findProdutoDTOById(Integer id) {
		Produto produto = produtoRepository.findById(id).isPresent() ?
				produtoRepository.findById(id).get() : null;
		
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if(null != produto) {
			produtoDTO = converterEntidadeParaDto(produto);
		}
		return produtoDTO;
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto = convertDTOToEntidade(produtoDTO);
		Produto novoProduto = produtoRepository.save(produto);
		
		return converterEntidadeParaDto(novoProduto);
	}
	
	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto updateProdutoId(Produto produto, Integer id) {
		Produto prod = produtoRepository.findById(id).isPresent() ?
				produtoRepository.findById(id).get() : null;
		Produto produtoAtualizado = null;
		if(null != prod) {
			prod.setCategoria(produto.getCategoria());
			prod.setFornecedor(produto.getFornecedor());
			prod.setIdProduto(produto.getIdProduto());
			prod.setNomeProduto(produto.getNomeProduto());
			prod.setSku(produto.getSku());
			produtoAtualizado = produtoRepository.save(prod);
		}
		return produtoAtualizado;
	}
	
	public void deleteProdutoId(Integer id) {
		Produto prod = produtoRepository.findById(id).get();
		produtoRepository.delete(prod);
	}
	
	public void deleteProduto(Produto produto) {
		produtoRepository.delete(produto);
	}
	
	private Produto convertDTOToEntidade(ProdutoDTO produtoDTO){
		Produto produto = new Produto();
		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setSku(produtoDTO.getSku());
		
		Categoria categoria = categoriaService.findCategoriaById(produtoDTO.getCategoriaDTO().getIdCategoria());
		produto.setCategoria(categoria);
		
		Fornecedor fornecedor = fornecedorService.findFornecedorById(produtoDTO.getFornecedorDTO().getIdFornecedor());
		produto.setFornecedor(fornecedor);
		
		return produto;
	}
		
	private ProdutoDTO converterEntidadeParaDto(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setSku(produto.getSku());
		
		CategoriaDTO categoriaDTO = categoriaService.findCategoriaDTOById(produto.getCategoria().getIdCategoria());
		produtoDTO.setCategoriaDTO(categoriaDTO);
		
		FornecedorDTO fornecedorDTO = fornecedorService.findFornecedorDTOById(produto.getFornecedor().getIdFornecedor());
		produtoDTO.setFornecedorDTO(fornecedorDTO);
		
		return produtoDTO;
		
	}
	/*(public Produto saveProdutocomImagem(String produto, MultipartFile file) {
		Produto newProduto = new Produto();
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			newProduto = objMapper.readValue(produto, Produto.class);
		} catch (IOException e) {
			System.out.println("Erro de conversão");
			e.printStackTrace();
		}
		
		Produto produtoFoto = produtoRepository.save(newProduto);
		
		String filename = "produto."+produtoFoto.getIdProduto()+".image.png";
		
		try {
			Files.copy(file.getInputStream(), path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			produtoFoto.setImagemProduto(path.resolve(filename).toRealPath().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return produtoRepository.save(produtoFoto);
	}*/
}
