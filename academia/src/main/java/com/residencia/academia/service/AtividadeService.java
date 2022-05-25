package com.residencia.academia.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.repository.AtividadeRepository;


@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	public List<Atividade> findAll() {
		return atividadeRepository.findAll();
	}
	
	public Atividade findById(Integer id) {
		return atividadeRepository.findById(id).isPresent() ? 
				atividadeRepository.findById(id).get() : null;
	}

	public Atividade save(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public Atividade update(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public void delete(Integer id) {
		atividadeRepository.deleteById(id);
	}
}
