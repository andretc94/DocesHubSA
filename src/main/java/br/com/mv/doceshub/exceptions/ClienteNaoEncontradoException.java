package br.com.mv.doceshub.exceptions;

public class ClienteNaoEncontradoException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ClienteNaoEncontradoException(Long id) {
		super(String.format("Não foi possivel encontrar Cliente com id informado: '%d'", id));
	}

}
