package br.com.mv.doceshub.exceptions;

public class EmpresaNaoEncontradaException extends NegocioException{
	
	private static final long serialVersionUID = 1L;
	
	public EmpresaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public EmpresaNaoEncontradaException(Long id) {
		this(String.format("A empresa com id: '%d' não foi encontrada", id));
	}

}
