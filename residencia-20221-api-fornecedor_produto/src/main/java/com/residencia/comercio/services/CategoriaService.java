package com.residencia.comercio.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.comercio.dtos.CategoriaDTO;
import com.residencia.comercio.entities.Categoria;
import com.residencia.comercio.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ArquivoService arquivoService;
	
	@Autowired
	EmailService emailService;
	
	public List<Categoria> findAllCategoria(){
		return categoriaRepository.findAll();
	}
	
	public Categoria findCategoriaById(Integer id) {
		return categoriaRepository.findById(id).isPresent() ?
				categoriaRepository.findById(id).get() : null;
	}

	public CategoriaDTO findCategoriaDTOById(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).isPresent() ?
				categoriaRepository.findById(id).get() : null;
		
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		if(null != categoria) {
			categoriaDTO = converterEntidadeParaDto(categoria);
		}
		return categoriaDTO;
	}
	
	public Categoria saveCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public CategoriaDTO saveCategoriaDTO(CategoriaDTO categoriaDTO) {
			
		Categoria categoria = new Categoria();
		
		categoria.setIdCategoria(categoriaDTO.getIdCategoria());
		Categoria novoCategoria = categoriaRepository.save(categoria);
		
		return converterEntidadeParaDto(novoCategoria);
	}
	
	public Categoria updateCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void deleteCategoria(Integer id) {
		Categoria inst = categoriaRepository.findById(id).get();
		categoriaRepository.delete(inst);
	}
	
	public void deleteCategoria(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}
	
	private Categoria convertDTOToEntidade(CategoriaDTO categoriaDTO){
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(categoriaDTO.getIdCategoria());
		categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());
		
		/*List<Produto>produtoList = new ArrayList<>();
		if(null != categoriaDTO.getProdutoDTOList()) {
			for(ProdutoDTO produtoDTO : produtoDTO.getProdutoDTOList()) {
				
				
			}
			
		}*/
		
		return categoria;
	}
		
	private CategoriaDTO converterEntidadeParaDto(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
		return categoriaDTO;
	}
	
	/*List<Turma>Turmalist = new ArrayList<>();
	if (null != instrutorDTO.getTurmaDTOList()) {
		for (TurmaDTO turmaDTO : instrutorDTO.getTurmaDTOList()) {
			Turma turma = new Turma();
			turma.setDataFimTurma(turmaDTO.getDataFimTurma());
			turma.setDataInicioTurma(turmaDTO.getDataInicioTurma());
			turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
			turma.setHorarioTurma(turmaDTO.getHorarioTurma());
			turma.setIdTurma(turmaDTO.getIdTurma());
			
			Turmalist.add(turma);
		}
		instrutor.setTurmaList(Turmalist);*/
	
	/*public String conferirNome(MultipartFile file) {
		Boolean existe = categoriaRepository.existsByNomeImagem(file.getOriginalFilename());
		String nomeImagem = null;
		if(existe) {
			System.out.println("Esse nome ja existe");
		}else {
			nomeImagem = file.getOriginalFilename();
		}
		return nomeImagem;
	}
	public Categoria saveCategoriaFoto(String categoria, MultipartFile file) {
		
		Categoria categoriaConvertida = new Categoria();
		
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			categoriaConvertida = objectMapper.readValue(categoria, Categoria.class);
			
			
		} catch (IOException e) {
			System.out.println("Ocorreu um erro na conversão");
		}
		String nome = conferirNome(file);
		categoriaConvertida.setNomeImagem(nome);
		Categoria categoriaBD = categoriaRepository.save(categoriaConvertida);
		return null;
}*/
	public Categoria saveCategoriaFoto(String categoria, MultipartFile file) throws Exception {
		
		Categoria categoriaConvertida = new Categoria();
		
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			categoriaConvertida = objectMapper.readValue(categoria, Categoria.class);
			
			
		} catch (IOException e) {
			System.out.println("Ocorreu um erro na conversão");
		}
		
		
		
		/*
		  List<String> nomesImagensList =  categoriaRepository.findAllByNomeImagem(file.getOriginalFilename());
		  if(nomesImagensList.isEmpty()){
		  }else{
		  for(String nome: nomesImagensList){
		  }
		  
		 */
		Categoria categoriaBD = categoriaRepository.save(categoriaConvertida);
		categoriaBD.setNomeImagem(categoriaBD.getIdCategoria()+ "_"+ file.getOriginalFilename());
		Categoria categoriaAtualizada = categoriaRepository.save(categoriaBD);
		
		try {
			arquivoService.criarArquivo(categoriaBD.getIdCategoria() +"_"+file.getOriginalFilename(),file);
		} catch (Exception e) {
			throw new Exception("Ocorreu um erro ao tentar copiar o arquivo - " + e.getStackTrace());
		}
		//String corpoEmail= "Foi cadastrada uma nova categoria"+categoriaAtualizada.toString();
		//emailService.enviarEmailTexto("teste@teste.com","Cadastro de Categoria", corpoEmail);
		
		String corpoEmail= "<h1>Hello!!</h1>"+categoriaAtualizada.toString();
		emailService.enviarEmailHtml("amandacorrea625@gmail.com","Cadastro de Categoria", corpoEmail);
		return categoriaAtualizada;
}
	
	
}
