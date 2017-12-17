package br.mar.mil.util;

public enum EstatusPersistencia {

	
	ERRO("Erro"), SUCESSO("Sucesso"),OBJETO_REFERENCIADO(
			"Esse objeto não pode ser excluido por possuir referências ao mesmo.");
	
	private String name;
	
	private EstatusPersistencia(String s) {
		this.name =s;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return this.name; 
	}
}
