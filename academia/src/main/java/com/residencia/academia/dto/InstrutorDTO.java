package com.residencia.academia.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.residencia.academia.entity.Instrutor;


public class InstrutorDTO {
	@NonNull
	private Integer idInstrutor;

	private Integer rg;

	private String nomeInstrutor;

	@DateTimeFormat(pattern= "yyyyMMdd")
	private Date dataNascimento;

	private Integer titulacaoInstrutor;

	private List<TurmaDTO> turmaDTOList;

	public Integer getIdInstrutor() {
		return idInstrutor;
	}

	public void setIdInstrutor(Integer idInstrutor) {
		this.idInstrutor = idInstrutor;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public String getNomeInstrutor() {
		return nomeInstrutor;
	}

	public void setNomeInstrutor(String nomeInstrutor) {
		this.nomeInstrutor = nomeInstrutor;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getTitulacaoInstrutor() {
		return titulacaoInstrutor;
	}

	public void setTitulacaoInstrutor(Integer titulacaoInstrutor) {
		this.titulacaoInstrutor = titulacaoInstrutor;
	}

	
	public List<TurmaDTO> getTurmaDTOList() {
		return turmaDTOList;
	}

	public void setTurmaDTOList(List<TurmaDTO> turmaDTOList) {
		this.turmaDTOList = turmaDTOList;
	}

	@Override
	public String toString() {
		return "InstrutorDTO [idInstrutor=" + idInstrutor + ", rg=" + rg + ", nomeInstrutor=" + nomeInstrutor
				+ ", dataNascimento=" + dataNascimento + ", titulacaoInstrutor=" + titulacaoInstrutor
				+ ", turmaDTOList=" + turmaDTOList + "]";
	}

	

	
	
}
