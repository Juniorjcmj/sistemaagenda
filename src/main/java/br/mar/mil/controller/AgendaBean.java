package br.mar.mil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.mar.mil.dao.ConsultaRepository;
import br.mar.mil.dao.PacienteRepository;
import br.mar.mil.entity.Consulta;
import br.mar.mil.entity.Paciente;

@ManagedBean
@ViewScoped
public class AgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private PacienteRepository pacienteRepository;
	@EJB
	private ConsultaRepository consultaRepository;

	private Consulta consulta = new Consulta();
	private Consulta consultaSelecionada = new Consulta();
	private List<Paciente> pacientes = new ArrayList<Paciente>();
	private List<Consulta> listarConsultas = new ArrayList<Consulta>();
	private List<Consulta> listarConsultasModal = new ArrayList<Consulta>();
	private String destinoSalvar;
	
	
	Long pacienteID;

	public AgendaBean() {

	}

	// SCHEDULE FUNCIONANDO BONITO

	private ScheduleModel agendaScheduleModel;

	public ScheduleModel getAgendaScheduleModel() {
		return agendaScheduleModel;
	}

	public void setAgendaScheduleModel(ScheduleModel agendaScheduleModel) {
		this.agendaScheduleModel = agendaScheduleModel;
	}

	@PostConstruct
	public void listar() {

		consulta = new Consulta();
		agendaScheduleModel = new DefaultScheduleModel();
		List<Consulta> todas = new ArrayList<Consulta>();
		todas = this.consultaRepository.findAll();

		for (Consulta c : todas) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setStartDate(c.getDataConsulta());
			evt.setEndDate(c.getFimConsulta());
			evt.setData(c.getCodigo());
			evt.setTitle(c.getPaciente().getNome());
			// evt.setAllDay(true);
			evt.setEditable(true);	

			agendaScheduleModel.addEvent(evt);			
		}

	}

	//QUANDO CLICA preenche com a data
	public void quandoNovo(SelectEvent selectEvent) {
		consulta = new Consulta();
		consulta.setDataConsulta((Date) selectEvent.getObject());
		
	}

	//// SCHEDULE QUANDO FOR selecionado O OBJETO
	public void onEventSelect(SelectEvent selectEvent) {
	
		ScheduleEvent event = (ScheduleEvent) selectEvent.getObject();		
		Consulta cBanco = this.consultaRepository.findById((Long)event.getData());		
		consulta = cBanco;
		

	}

	//// SCHEDULE QUANDO FOR MOVER O OBJETO
	public void quandoMovido(ScheduleEntryMoveEvent evento) {
		
		Consulta movida = this.consultaRepository.findById((Long)evento.getScheduleEvent().getData());
		consulta = movida;
		
		consulta.setDataConsulta(evento.getScheduleEvent().getStartDate());
		consultaRepository.update(consulta);

	}

	//// SCHEDULE QUANDO FOR redimensionado O OBJETO
	public void quandoRedimensionado(ScheduleEntryResizeEvent event) {
		for (Consulta c : listarConsultas) {
			if (c.getCodigo() == (Long) event.getScheduleEvent().getData()) {
				consulta = c;

				try {
					consultaRepository.update(consulta);
					listar();
				} catch (Exception e) {
					e.printStackTrace();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro em Atualizar!", "Erro" + e.getMessage()));
				}
				break;
			}
		}
	}

	// SCHEDULE FUNCIONANDO BONITO FIM

	// METODO SALVAR

	public String salvar() {

		if (consulta.getCodigo() == null) {
			Paciente p = this.pacienteRepository.findById(this.pacienteID);
			this.consulta.setPaciente(p);
			Date dataSomada = new Date();
			dataSomada = new Date(consulta.getDataConsulta().getTime()+ 30 * 60 * 1000);
			this.consulta.setFimConsulta(dataSomada);
			this.consultaRepository.save(consulta);

			FacesMessage msg = new FacesMessage("Consulta cadastrada com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			this.consulta = null;
			

		} else {
			
			Date dataSomada = new Date();
			dataSomada = new Date(consulta.getDataConsulta().getTime()+ 30 * 60 * 1000);
			this.consulta.setFimConsulta(dataSomada);
			this.consultaRepository.update(consulta);

			FacesMessage msg = new FacesMessage("Consulta atualizada com sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			this.consulta = null;

		}
		listar();
		return null;

	}
	public String alterar(){
		
		return "alterarConsulta";
		
		
	}

	public Consulta getConsulta() {
		return consulta;
	}
	
	public  void excluir(){
		this.consultaRepository.delete(consulta.getCodigo());		
		listar();		
		
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

	public List<Paciente> getPacientes() {
		pacientes = this.pacienteRepository.findAll();
		return pacientes;
	}

	public List<Consulta> getConsultas() {
		listarConsultas = this.consultaRepository.findAll();
		return listarConsultas;
	}

	public Consulta getConsultaSelecionada() {
		return consultaSelecionada;
	}

	public void setConsultaSelecionada(Consulta consultaSelecionada) {
		this.consultaSelecionada = consultaSelecionada;
	}

	public List<Consulta> getListarConsultasModal() {
		
		return listarConsultasModal;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
	
	

}
