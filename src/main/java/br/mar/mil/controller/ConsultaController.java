package br.mar.mil.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.mar.mil.dao.ConsultaRepository;
import br.mar.mil.dao.PacienteRepository;
import br.mar.mil.entity.Consulta;
import br.mar.mil.entity.Paciente;
import br.mar.mil.services.ConsultaService;

@Named
public class ConsultaController {
	
	@Inject
	private ConsultaService consultaService;
	@Inject
	private PacienteRepository pacienteRepository;
	@Inject
	private ConsultaRepository consultaRepository;
	
	
	private Consulta consulta = new Consulta();
	private Paciente paciente = new Paciente();
	private List<Paciente>pacientes = new ArrayList<Paciente>();
	private List<Consulta>consultas = new ArrayList<Consulta>();
	private ScheduleModel agendaModel;	
	Long pacienteID;
	   
    @PostConstruct
    public void inicializar(){
    	
    	try{  
    	consultaRepository = new ConsultaRepository();
    	consulta = new Consulta();
    	agendaModel = new DefaultScheduleModel();
    	
    	  	
    	consultas = this.consultaRepository.findAll();
    	}catch(Exception ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage
				.SEVERITY_ERROR, "Erro", "Erro no sql."));
		}
    	for(Consulta cs : consultas){
    		DefaultScheduleEvent evt = new DefaultScheduleEvent();
    		evt.setStartDate(cs.getDataConsulta());
    		evt.setData(cs.getCodigo());    		
    		evt.setAllDay(true);
    		evt.setEditable(true);
    		
    		agendaModel.addEvent(evt);
    	}
    	
    	
    }
	
	
	public String salvar(){
	    Paciente p = this.pacienteRepository.findById(this.pacienteID);
		this.consulta.setPaciente(p);
		this.consultaService.salvar(consulta);
		//this.consultaRepository.save(consulta);
		
		FacesMessage msg = new FacesMessage("Consulta cadastrada com sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		this.consulta = null;
		
		return "marcacao.xhtml?faces-redirect=true";
		
		
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



	public ScheduleModel getAgendaModel() {
		return agendaModel;
	}



	public void setAgendaModel(ScheduleModel agendaModel) {
		this.agendaModel = agendaModel;
	}
	
	
	
	
	
	

}
