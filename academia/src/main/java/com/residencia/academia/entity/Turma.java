package com.residencia.academia.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "turma")
//@JsonIgnoreProperties({ "instrutor" })
@JsonIdentityInfo(
generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTurma")
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_turma")
	private Integer idTurma;

	@Column(name = "horario")
	@JsonFormat(pattern="HH:mm:ss")
	private Date horarioTurma;

	@Column(name = "duracao")
	@JsonFormat(pattern="HH:mm:ss")
	private Integer duracaoTurma;

	@Column(name = "data_inicio")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dataInicioTurma;

	@Column(name = "data_fim")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dataFimTurma;

	@ManyToOne
	//@JsonManagedReference
	//@JsonIgnore
	//@JsonBackReference
	@JoinColumn(name="id_instrutor", columnDefinition = "id_instrutor")
	private	Instrutor instrutor;
	
	@OneToOne
	@JoinColumn(name="id_atividade", columnDefinition = "id_atividade")
	private Atividade atividade;

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public Date getHorarioTurma() {
		return horarioTurma;
	}

	public void setHorarioTurma(Date horarioTurma) {
		this.horarioTurma = horarioTurma;
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

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

}
