package br.mar.mil.services;

import java.io.Serializable;

import javax.inject.Inject;

import br.mar.mil.dao.PessoaReposytory;
import br.mar.mil.entity.Pessoa;
import br.mar.mil.util.Messagens;

public class PessoaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public PessoaService() {}
	
	@Inject
	private PessoaReposytory pessoaReposytory;
	
	private Messagens messages;
	
	private Pessoa pessoa = new Pessoa();
	
	
	public void salvar(Pessoa pessoa){
		
		if (this.pessoa.getCodigo() == null) {			
			this.pessoaReposytory.save(pessoa);
			this.messages.sucesso();
			
		}else{
			this.pessoaReposytory.update(pessoa);
			this.messages.sucesso();
		}
		
	}


	public Messagens getMessages() {
		return messages;
	}


	public void setMessages(Messagens messages) {
		this.messages = messages;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


		
	

}
