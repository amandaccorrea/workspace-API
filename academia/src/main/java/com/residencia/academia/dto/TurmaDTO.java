package com.residencia.academia.dto;

import java.util.Date;


public class TurmaDTO {
	private Integer idTurma;
	
	private Date horarioTurma;

	private Integer duracaoTurma;

	private Date dataInicioTurma;

	private Date dataFimTurma;


	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public Integer getDuracaoTurma() {
		return duracaoTurma;
	}

	public void setDuracaoTurma(Integer duracaoTurma) {
		this.duracaoTurma = duracaoTurma;
	}

	public Date getDataInicioTurma() {
		return dataInicioTurma;
	}

	public void setDataInicioTurma(Date dataInicioTurma) {
		this.dataInicioTurma = dataInicioTurma;
	}

	public Date getDataFimTurma() {
		return dataFimTurma;
	}

	public void setDataFimTurma(Date dataFimTurma) {
		this.dataFimTurma = dataFimTurma;
	}

	@Override
	public String toString() {
		return "TurmaDTO [idTurma=" + idTurma + ", duracaoTurma=" + duracaoTurma + ", dataInicioTurma="
				+ dataInicioTurma + ", dataFimTurma=" + dataFimTurma + "]";
	}

	public Date getHorarioTurma() {
		return horarioTurma;
	}

	public void setHorarioTurma(Date horarioTurma) {
		this.horarioTurma = horarioTurma;
	}

	
}
