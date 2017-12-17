package br.mar.mil.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.mar.mil.entity.Paciente;
import br.mar.mil.generics.GenericDAO;

@Stateless
public class PacienteRepository extends GenericDAO<Paciente> {

	public PacienteRepository() {
		super(Paciente.class);
	}
	
	public List<Paciente>findAllOrderByASC(){
		Query query = manager.createQuery("select p from Paciente p order by p.nome ASC", Paciente.class);
		return query.getResultList();
	}
	
	public List<Paciente> findAllNome() {
        String situacao = null;
        Query query = manager.createQuery("select p from Paciente p ", Paciente.class);
         return query.getResultList();
    }
     public List<Paciente> findAllNomeOrderByDESC() {
         Query query = manager.createQuery("select p from Paciente p order by p.nome DESC", Paciente.class);
         return query.getResultList();
 
    }

    public List<Paciente> findOnePacientePorNome(String nome) {
      String jpql = "select p from Paciente p where p.nome = :nome";
      return this.manager
              .createQuery(jpql,Paciente.class)
              .setParameter("nome", nome)
              .getResultList();
    }
//
//    public List<Viatura> findAllOm(String om) {
//        String jpql = "select v from Viatura v where v.om = :om";
//      return this.manager
//              .createQuery(jpql,Viatura.class)
//              .setParameter("om", om)
//              .getResultList();
//    }
//    
	
}
