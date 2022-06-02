package com.residencia.comercio.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.residencia.comercio.dtos.CadastroEmpresaReceitaDTO;
import com.residencia.comercio.dtos.CepDTO;
import com.residencia.comercio.dtos.FornecedorDTO;
import com.residencia.comercio.entities.Fornecedor;
import com.residencia.comercio.repositories.FornecedorRepository;

@Service
public class FornecedorService {
	@Autowired
	FornecedorRepository fornecedorRepository;

	public List<Fornecedor> findAllFornecedor() {
		return fornecedorRepository.findAll();
	}

	public Fornecedor findFornecedorById(Integer id) {
		return fornecedorRepository.findById(id).isPresent() ? fornecedorRepository.findById(id).get() : null;
	}

	public FornecedorDTO findFornecedorDTOById(Integer id) {
		Fornecedor fornecedor = fornecedorRepository.findById(id).isPresent() ? 
				fornecedorRepository.findById(id).get() : null;
		FornecedorDTO fornecedorDTO = new FornecedorDTO();
		if(null != fornecedor) {
			fornecedorDTO = converterEntidadeParaDTO(fornecedor);
		}
		return fornecedorDTO;		
	}

	public Fornecedor saveFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}
	
	public FornecedorDTO saveFornecedorByCnpj(String cnpj) {
        return converterEntidadeParaDTO(fornecedorRepository.save(CadastroEmpresaReceitaDTOParaFornecedor(consultarDadosPorCnpj(cnpj))));
    }

	public FornecedorDTO saveFornecedorDTO(FornecedorDTO fornecedorDTO1) {
		Fornecedor fornecedor1= new Fornecedor();
		fornecedor1.setBairro(fornecedorDTO1.getBairro());
		fornecedor1.setCep(fornecedorDTO1.getCep());
		fornecedor1.setCnpj(fornecedorDTO1.getCnpj());
		fornecedor1.setComplemento(fornecedorDTO1.getComplemento());
		fornecedor1.setDataAbertura(fornecedorDTO1.getDataAbertura());
		fornecedor1.setEmail(fornecedorDTO1.getEmail());
		fornecedor1.setIdFornecedor(fornecedorDTO1.getIdFornecedor());
		fornecedor1.setLogradouro(fornecedorDTO1.getLogradouro());
		fornecedor1.setMunicipio(fornecedorDTO1.getMunicipio());
		fornecedor1.setNomeFantasia(fornecedorDTO1.getNomeFantasia());
		
		fornecedor1.setRazaoSocial(fornecedorDTO1.getRazaoSocial());
		fornecedor1.setStatusSituacao(fornecedorDTO1.getStatusSituacao());
		fornecedor1.setTelefone(fornecedorDTO1.getTelefone());
		fornecedor1.setTipo(fornecedorDTO1.getTipo());
		fornecedor1.setUf(fornecedorDTO1.getUf());
		
		Fornecedor novoFornecedor = fornecedorRepository.save(fornecedor1);

		return converterEntidadeParaDTO(novoFornecedor);
	}

	public Fornecedor updateFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public void deleteFornecedor(Integer id) {
		fornecedorRepository.delete(fornecedorRepository.findById(id).get());
	}

	public void deleteFornecedor(Fornecedor fornecedor) {
		fornecedorRepository.delete(fornecedor);
	}

	private Fornecedor converterDTOParaEntidade(FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setBairro(fornecedorDTO.getBairro());
		fornecedor.setCep(fornecedorDTO.getCep());
		fornecedor.setCnpj(fornecedorDTO.getCnpj());
		fornecedor.setComplemento(fornecedorDTO.getComplemento());
		fornecedor.setDataAbertura(fornecedorDTO.getDataAbertura());
		fornecedor.setEmail(fornecedorDTO.getEmail());
		fornecedor.setIdFornecedor(fornecedorDTO.getIdFornecedor());
		fornecedor.setLogradouro(fornecedorDTO.getLogradouro());
		fornecedor.setMunicipio(fornecedorDTO.getMunicipio());
		fornecedor.setNomeFantasia(fornecedorDTO.getNomeFantasia());
		fornecedor.setRazaoSocial(fornecedorDTO.getRazaoSocial());
		fornecedor.setStatusSituacao(fornecedorDTO.getStatusSituacao());
		fornecedor.setTelefone(fornecedorDTO.getTelefone());
		fornecedor.setTipo(fornecedorDTO.getTipo());
		fornecedor.setUf(fornecedorDTO.getUf());

		return fornecedor;
	}

	private FornecedorDTO converterEntidadeParaDTO(Fornecedor fornecedor) {
		FornecedorDTO fornecedorDTO = new FornecedorDTO();

		fornecedorDTO.setBairro(fornecedor.getBairro());
		fornecedorDTO.setCep(fornecedor.getCep());
		fornecedorDTO.setCnpj(fornecedor.getCnpj());
		fornecedorDTO.setComplemento(fornecedor.getComplemento());
		fornecedorDTO.setDataAbertura(fornecedor.getDataAbertura());
		fornecedorDTO.setEmail(fornecedor.getEmail());
		fornecedorDTO.setIdFornecedor(fornecedor.getIdFornecedor());
		fornecedorDTO.setLogradouro(fornecedor.getLogradouro());
		fornecedorDTO.setMunicipio(fornecedor.getMunicipio());
		fornecedorDTO.setNomeFantasia(fornecedor.getNomeFantasia());
		fornecedorDTO.setRazaoSocial(fornecedor.getRazaoSocial());
		fornecedorDTO.setStatusSituacao(fornecedor.getStatusSituacao());
		fornecedorDTO.setTelefone(fornecedor.getTelefone());
		fornecedorDTO.setTipo(fornecedor.getTipo());
		fornecedorDTO.setUf(fornecedor.getUf());
		 

		return fornecedorDTO;
	}
	
	public Fornecedor CadastroEmpresaReceitaDTOParaFornecedor(CadastroEmpresaReceitaDTO cadastroEmpresaReceitaDTO) {
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setCnpj(cadastroEmpresaReceitaDTO.getCnpj());
		fornecedor.setEmail(cadastroEmpresaReceitaDTO.getEmail());
		fornecedor.setNomeFantasia(cadastroEmpresaReceitaDTO.getFantasia());
		fornecedor.setRazaoSocial(cadastroEmpresaReceitaDTO.getNome());
		fornecedor.setStatusSituacao(cadastroEmpresaReceitaDTO.getSituacao());
		fornecedor.setTelefone(cadastroEmpresaReceitaDTO.getTelefone());
		fornecedor.setTipo(cadastroEmpresaReceitaDTO.getTipo());
		
		fornecedor.setCep(cadastroEmpresaReceitaDTO.getCep());
		
		fornecedor.setLogradouro(cadastroEmpresaReceitaDTO.getLogradouro());
		fornecedor.setBairro(cadastroEmpresaReceitaDTO.getBairro());
		fornecedor.setComplemento(cadastroEmpresaReceitaDTO.getComplemento());
		fornecedor.setMunicipio(cadastroEmpresaReceitaDTO.getMunicipio());
		fornecedor.setUf(cadastroEmpresaReceitaDTO.getUf());

		
		try {
			fornecedor.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse(cadastroEmpresaReceitaDTO.getAbertura()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 fornecedor.setNumero(Integer.parseInt(cadastroEmpresaReceitaDTO.getNumero()));
		 
		 
		return fornecedor;
	}

	
	public CadastroEmpresaReceitaDTO consultarDadosPorCnpj(String cnpj) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://www.receitaws.com.br/v1/cnpj/{cnpj}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);
		CadastroEmpresaReceitaDTO cadastroEmpresaReceitaDTO = restTemplate.getForObject(uri, CadastroEmpresaReceitaDTO.class,params);
			return cadastroEmpresaReceitaDTO;
	}
	
	
	public CepDTO consultarCepDTO(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		//String criterion = "{\"prop\":\"cnpj\",\"value\":\"{cnpj}\"}";
		String uri = "https://viacep.com.br/ws/{cep}/json";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		 CepDTO cepDTO = restTemplate.getForObject(uri, CepDTO.class,params);
		return cepDTO;
		
	}
	

	// 00776574000156
	//{"cep":"25651078"}
	//{"cnpj":"33000167076949"}
}
