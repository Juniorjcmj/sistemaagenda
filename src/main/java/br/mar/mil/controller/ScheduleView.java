package br.mar.mil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.mar.mil.dao.ConsultaRepository;
import br.mar.mil.dao.PacienteRepository;
import br.mar.mil.entity.Consulta;
import br.mar.mil.entity.Paciente;
import br.mar.mil.services.ConsultaService;

@Named
@ViewScoped
public class ScheduleView implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ConsultaService consultaService;
	@Inject
	private PacienteRepository pacienteRepository;
	@Inject
	private ConsultaRepository consultaRepository;

	private Consulta consulta = new Consulta();
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	private List<Consulta> consultas = new ArrayList<Consulta>();
	Long pacienteID;


	public String salvar() {
		Paciente p = this.pacienteRepository.findById(this.pacienteID);
		this.consulta.setPaciente(p);
		this.consultaService.salvar(consulta);
		// this.consultaRepository.save(consulta);

		FacesMessage msg = new FacesMessage("Consulta cadastrada com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		this.consulta = null;

		return "marcacao.xhtml?faces-redirect=true";

	}
	public void novo(SelectEvent evento){
		
		
		consulta = new Consulta();
		consulta.setDataConsulta((Date) evento.getObject());
		
		ConsultaRepository repo = new ConsultaRepository();
		consultas = repo.findAll();
	}

	
	public List<Consulta> getConsultas() {
		this.consultas = this.consultaRepository.findAll();
		return consultas;
	}

	public List<Paciente> getPacientes() {
		this.pacientes = this.pacienteRepository.findAll();
		return pacientes;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Long getPacienteID() {
		return pacienteID;
	}

	public void setPacienteID(Long pacienteID) {
		this.pacienteID = pacienteID;
	}

//	public ScheduleModel getEventModel() {
//		return eventModel;
//	}
	
}
