package br.mar.mil.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.mar.mil.dao.PacienteRepository;
import br.mar.mil.entity.Paciente;

public class PacienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	public PacienteService() {}
	
	@Inject
	private PacienteRepository pacienterepository;
	
	private Paciente paciente = new Paciente();	
	
	public void salvar(Paciente paciente){
		
		if (paciente.getCodigo() == null) {
			
			this.pacienterepository.save(paciente);
		}else {
			this.pacienterepository.update(paciente);
		}
				
	}

	
	

}
