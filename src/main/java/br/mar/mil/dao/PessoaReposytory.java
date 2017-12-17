package br.mar.mil.dao;

import javax.ejb.Stateless;

import br.mar.mil.entity.Pessoa;
import br.mar.mil.generics.GenericDAO;

@Stateless
public class PessoaReposytory extends GenericDAO<Pessoa> {
	
	public PessoaReposytory() {
		super(Pessoa.class);
	}

}
