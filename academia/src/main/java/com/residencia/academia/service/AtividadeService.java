package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Turma;
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
	
	public AtividadeDTO findDTOById(Integer id) {
		Atividade atividade = atividadeRepository.findById(id).isPresent() ? 
				atividadeRepository.findById(id).get() : null;
		AtividadeDTO atividadeDTO = new AtividadeDTO();
		if(null != atividade) {
			atividadeDTO = converterEntidadeParaDTO(atividade);
		}
		return atividadeDTO;
	}

	public Atividade save(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}
	
	public AtividadeDTO saveDTO(AtividadeDTO atividadeDTO) {
		Atividade atividade = new Atividade();
		atividade = converterDTOParaEntidade(atividadeDTO);
		Atividade novaAtividade = atividadeRepository.save(atividade);

		return converterEntidadeParaDTO(novaAtividade);
	}

	public Atividade update(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public void delete(Integer id) {
		atividadeRepository.deleteById(id);
	}
	
	private Atividade converterDTOParaEntidade(AtividadeDTO atividadeDTO) {
		Atividade atividade = new Atividade();
		atividade.setIdAtividade(atividadeDTO.getIdAtividade());
		atividade.setNomeAtividade(atividadeDTO.getNomeAtividade());
		
		List<Turma>Turmalist = new ArrayList<>();
		if (null != atividadeDTO.getTurmaDTOList()) {
			for (TurmaDTO turmaDTO : atividadeDTO.getTurmaDTOList()) {
				Turma turma = new Turma();
				turma.setDataFimTurma(turmaDTO.getDataFimTurma());
				turma.setDataInicioTurma(turmaDTO.getDataInicioTurma());
				turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
				turma.setHorarioTurma(turmaDTO.getHorarioTurma());
				turma.setIdTurma(turmaDTO.getIdTurma());
				
				Turmalist.add(turma);
			}
			atividade.setTurmaList(Turmalist);
		}
		return atividade;
	}
	
	private AtividadeDTO converterEntidadeParaDTO(Atividade atividade) {
		AtividadeDTO atividadeDTO = new AtividadeDTO();
		atividadeDTO.setIdAtividade(atividade.getIdAtividade());
		atividadeDTO.setNomeAtividade(atividade.getNomeAtividade());
		
		List<TurmaDTO> listTurmaDTO = new ArrayList<>();
		if (null != atividade.getTurmaList()) {
			for (Turma turma : atividade.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();
				turmaDTO.setDataFimTurma(turma.getDataFimTurma());
				turmaDTO.setDataInicioTurma(turma.getDataInicioTurma());
				turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
				turmaDTO.setIdTurma(turma.getIdTurma());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());

				listTurmaDTO.add(turmaDTO);
			}
			atividadeDTO.setTurmaDTOList(listTurmaDTO);
		}
		return atividadeDTO;
	}
}
