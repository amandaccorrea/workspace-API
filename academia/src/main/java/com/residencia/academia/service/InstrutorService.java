package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {
	@Autowired
	private InstrutorRepository instrutorRepository;

	public List<Instrutor> findAll() {
		return instrutorRepository.findAll();
	}

	public InstrutorDTO findDTOById(Integer id) {
		Instrutor instrutor = instrutorRepository.findById(id).isPresent() ? instrutorRepository.findById(id).get()
				: null;
		InstrutorDTO instrutorDTO = new InstrutorDTO();
		if (null != instrutor) {
			instrutorDTO = converterEntidadeParaDTO(instrutor);
		}
		return instrutorDTO;
	}

	private InstrutorDTO converterEntidadeParaDTO(Instrutor instrutor) {
		InstrutorDTO instrutorDTO = new InstrutorDTO();
		instrutorDTO.setDataNascimento(instrutor.getDataNascimento());
		instrutorDTO.setNomeInstrutor(instrutor.getNomeInstrutor());
		instrutorDTO.setRg(instrutor.getRg());
		instrutorDTO.setIdInstrutor(instrutor.getIdInstrutor());
		instrutorDTO.setTitulacaoInstrutor(instrutor.getTitulacaoInstrutor());

		List<TurmaDTO> listTurmaDTO = new ArrayList<>();
		if (null != instrutor.getTurmaList()) {
			for (Turma turma : instrutor.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();
				turmaDTO.setDataFimTurma(turma.getDataFimTurma());
				turmaDTO.setDataInicioTurma(turma.getDataInicioTurma());
				turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
				turmaDTO.setIdTurma(turma.getIdTurma());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());

				listTurmaDTO.add(turmaDTO);
			}
			instrutorDTO.setTurmaDTOList(listTurmaDTO);
		}
		return instrutorDTO;
	}

	private Instrutor converterDTOParaEntidade(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = new Instrutor();
		instrutor.setDataNascimento(instrutorDTO.getDataNascimento());
		instrutor.setIdInstrutor(instrutorDTO.getIdInstrutor());
		instrutor.setNomeInstrutor(instrutorDTO.getNomeInstrutor());
		instrutor.setRg(instrutorDTO.getRg());
		instrutor.setTitulacaoInstrutor(instrutorDTO.getTitulacaoInstrutor());
		
		List<Turma>Turmalist = new ArrayList<>();
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
			instrutor.setTurmaList(Turmalist);
		}
		return instrutor;

	}

	public Instrutor findById(Integer id) {
		return instrutorRepository.findById(id).isPresent() ? instrutorRepository.findById(id).get() : null;
	}

	public Instrutor save(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public InstrutorDTO saveDTO(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = new Instrutor();
		instrutor = converterDTOParaEntidade(instrutorDTO);
		Instrutor novoInstrutor = instrutorRepository.save(instrutor);

		return converterEntidadeParaDTO(novoInstrutor);
	}

	public Instrutor update(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public void delete(Integer id) {
		Instrutor inst = instrutorRepository.findById(id).get();
		instrutorRepository.delete(inst);
	}

	public void delete(Instrutor instrutor) {
		instrutorRepository.delete(instrutor);
	}

}
