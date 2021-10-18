package br.com.mv.doceshub.exceptions;

public class TipoDoceNaoEncontradoException extends NegocioException{
	
	private static final long serialVersionUID = 1L;
	
	public TipoDoceNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public TipoDoceNaoEncontradoException(Long id) {
		this(String.format("O Tipo de Doce com id: '%d' não foi encontrado", id));
	}

}
