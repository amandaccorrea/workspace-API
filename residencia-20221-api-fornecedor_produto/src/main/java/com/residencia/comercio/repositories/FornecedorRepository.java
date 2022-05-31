package com.residencia.comercio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.comercio.dtos.CadastroEmpresaReceitaDTO;
import com.residencia.comercio.dtos.FornecedorDTO;
import com.residencia.comercio.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor,Integer> {

	FornecedorDTO save(String cnpj);

	FornecedorDTO save(FornecedorDTO fornecedor);

}