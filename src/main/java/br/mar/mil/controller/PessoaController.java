package br.mar.mil.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.mar.mil.dao.PessoaReposytory;
import br.mar.mil.entity.Pessoa;
import br.mar.mil.services.PessoaService;

@Named
@RequestScoped
public class PessoaController {
	
	@Inject
	private PessoaService pessoaService;
	
	@Inject
	private PessoaReposytory pessoaReposytory;
	
	private Pessoa pessoa = new Pessoa();
	private Pessoa pessoaEdicao = new Pessoa();
	
	private List<Pessoa> findAll = new ArrayList<>();
	
	
	public String salvar(){
		this.pessoaService.salvar(pessoa);
		return " /pesquisa-pessoas.xhtml?faces-redirect=true";
	}


	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public List<Pessoa> getFindAll() {
		this.findAll = this.pessoaReposytory.findAll();
		return findAll;
	}


	public Pessoa getPessoaEdicao() {
		return pessoaEdicao;
	}


	public void setPessoaEdicao(Pessoa pessoaEdicao) {
		this.pessoaEdicao = pessoaEdicao;
	}
	
	
	

}
