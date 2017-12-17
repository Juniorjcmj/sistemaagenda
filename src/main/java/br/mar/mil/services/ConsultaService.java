package br.mar.mil.services;

import java.io.Serializable;

import javax.ejb.EJB;

import br.mar.mil.dao.ConsultaRepository;
import br.mar.mil.entity.Consulta;

public class ConsultaService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ConsultaRepository consultaRepository;
	
	private Consulta consulta = new Consulta();
	
	public void salvar(Consulta consulta){
		if (consulta.getCodigo() == null) {
			
			this.consultaRepository.save(consulta);			
		}else {
			this.consultaRepository.update(consulta);
		}
	}	

}
