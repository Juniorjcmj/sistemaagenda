package br.mar.mil.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import br.mar.mil.dao.PacienteRepository;
import br.mar.mil.entity.Paciente;

@Named
@RequestScoped
public class PacienteController {

	@Inject
	private PacienteRepository pacienteRepository;
	private List<Paciente> pacientes = new ArrayList<Paciente>();	
	private List<Paciente> findAllOrderByASC = new ArrayList<Paciente>();
	private List<Paciente> findAllporNome = new ArrayList<Paciente>();
	private List<String> planos = new ArrayList<String>();
	private Paciente paciente = new Paciente();
	private String destinoSalvar;
	private String nome;

	//SALVA UM PACIENTE CASO O ID SEJA == NULL OU FAZ UMA ATUALIZAÇÃO SE O ID FOR != NULL
	public String salvar() {
		System.out.println(paciente.getCodigo());
		if (paciente.getCodigo() == null) {

			this.pacienteRepository.save(paciente);
			
		} else {
			this.pacienteRepository.update(paciente);
		}
		return "/cadastro-pacientes.xhtml?faces-redirect=true";
	}
	
	//REDIRECIONA PARA A PÁGINA DE ALTERAÇÃO DE DADOS
	public String update() {
		return "/cadastro-pacientes";
	}
	//EXCLUI PACIENTES POR ID
	public void excluir() {
		try {
			this.pacienteRepository.delete(paciente.getCodigo());
		} catch (PersistenceException e) {
			e.getStackTrace();
		}
		getFindAllOrderByASC();

	}
	//BUSCA TODOS OS PACIENTES 
	public List<Paciente> getPacientes() {
		this.pacientes = this.pacienteRepository.findAll();
		return pacientes;
	}
	//BUSCA TODOS OS PACIENTES EM ORDEM ALFABÉTICAS 
		public List<Paciente> getFindAllOrderByASC() {
			findAllOrderByASC = this.pacienteRepository.findAllOrderByASC();
			return findAllOrderByASC;
		}
		
		//LISTA PLANOS PRÉ CADASTRADOS
		public List<String> getPlanos() {
			this.planos.add("Amil");
			this.planos.add("Assim");
			this.planos.add("Sempre Odonto");
			this.planos.add("Unimed");
			this.planos.add("OdontoPrev");
			this.planos.add("Bradesco");
			return planos;
		}
		
		public List<Paciente> consultaPorNome() {
			
			List<Paciente> vemAllporNome = this.pacienteRepository.findOnePacientePorNome(nome);
			this.findAllporNome = vemAllporNome;
			return findAllporNome;
		}
		
		public List<Paciente> getFindAllporNome() {			
			//consultaPorNome();
			return findAllporNome;
		}
		
		

	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}	

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
