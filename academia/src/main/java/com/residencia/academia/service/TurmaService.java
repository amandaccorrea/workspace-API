package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private InstrutorService instrutorService;
	@Autowired
	private AtividadeService atividadeService;

	public List<Turma> findAll() {
		return turmaRepository.findAll();
	}

	public TurmaDTO findDTOById(Integer id) {
		Turma turma = turmaRepository.findById(id).isPresent() ? 
				turmaRepository.findById(id).get() : null;
		TurmaDTO turmaDTO = new TurmaDTO();
		if(null != turma) {
			turmaDTO = converterEntidadeParaDTO(turma);
		}
		return turmaDTO;
	}
	
	private TurmaDTO converterEntidadeParaDTO(Turma turma) {
		TurmaDTO turmaDTO = new TurmaDTO();
			turmaDTO.setDataFimTurma(turma.getDataFimTurma());
			turmaDTO.setDataInicioTurma(turma.getDataInicioTurma());
			turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
			turmaDTO.setIdTurma(turma.getIdTurma());
			turmaDTO.setHorarioTurma(turma.getHorarioTurma());
			
			InstrutorDTO instrutorDTO = instrutorService.findDTOById(turma.getInstrutor().getIdInstrutor());
			turmaDTO.setInstrutorDTO(instrutorDTO);
			
			AtividadeDTO atividadeDTO = atividadeService.findDTOById(turma.getAtividade().getIdAtividade());
			turmaDTO.setAtividadeDTO(atividadeDTO);
		
		return turmaDTO;
	}
	
	private Turma converterDTOParaEntidade(TurmaDTO turmaDTO) {
		Turma turma = new Turma();
		turma.setDataFimTurma(turmaDTO.getDataFimTurma());
		turma.setDataInicioTurma(turmaDTO.getDataInicioTurma());
		turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
		turma.setHorarioTurma(turmaDTO.getHorarioTurma());
		turma.setIdTurma(turmaDTO.getIdTurma());
		//turma.setInstrutor(instrutorService.converterDTOParaEntidade(turmaDTO.getInstrutorDTO()));
		Instrutor instrutor = instrutorService.findById(turmaDTO.getInstrutorDTO().getIdInstrutor());
		turma.setInstrutor(instrutor);
		
		Atividade atividade = atividadeService.findById(turmaDTO.getAtividadeDTO().getIdAtividade());
		turma.setAtividade(atividade);
		
		return turma;
	}
	
	public Turma findById(Integer id) {
		return turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
	}
	
	public Turma save(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	public TurmaDTO saveDTO(TurmaDTO turmaDTO) {
		Turma turma = new Turma();
		turma = converterDTOParaEntidade(turmaDTO);
		Turma novaTurma = turmaRepository.save(turma);

		return converterEntidadeParaDTO(novaTurma);
	}

	public Turma update(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	public void delete(Integer id) {
		turmaRepository.deleteById(id);
	}
	
	/*public Boolean delete(Integer id) {
		if(turmaRepository.findById(id).isPresent()) {
			turmaRepository.deleteById(id);
			return true;
		}else {
			return false;
		}*/
	}