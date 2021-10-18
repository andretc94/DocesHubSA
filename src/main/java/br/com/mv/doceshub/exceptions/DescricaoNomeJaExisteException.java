package br.com.mv.doceshub.exceptions;

public class DescricaoNomeJaExisteException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public DescricaoNomeJaExisteException() {
		super("O nome informado já existe cadastrado, por favor informe outro");
	}
	
	public DescricaoNomeJaExisteException(String msg, Throwable cause) {
		super(msg, cause);
	}
	

}
