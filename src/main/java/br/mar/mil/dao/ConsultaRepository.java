package br.mar.mil.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Query;

import br.mar.mil.entity.Consulta;
import br.mar.mil.generics.GenericDAO;

@Stateful
public class ConsultaRepository extends GenericDAO<Consulta> {

	public ConsultaRepository() {
		super(Consulta.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Consulta> findAllConsultas() {
      
     Query query = manager.createQuery("select * from Consulta v ", Consulta.class);
       return query.getResultList();
  }
	
//	public List<Viatura> findAllNome() {
//        String situacao = null;
//        Query query = manager.createQuery("select v.cfn from Viatura v ", Viatura.class);
//         return query.getResultList();
//    }
//     public List<Viatura> ordenadas() {
//         Query query = manager.createQuery("select v from Viatura v order by v.om ASC", Viatura.class);
//         return query.getResultList();
//  
//    }
//
//    public Usuario findOneLogadoOm(String login) {
//      String jpql = "select u from Usuario u where u.login = :login";
//      return this.manager
//              .createQuery(jpql,Usuario.class)
//              .setParameter("login", login)
//              .getSingleResult();
//    }
//
//    public List<Viatura> findAllOm(String om) {
//        String jpql = "select v from Viatura v where v.om = :om";
//      return this.manager
//              .createQuery(jpql,Viatura.class)
//              .setParameter("om", om)
//              .getResultList();
//    }
}
