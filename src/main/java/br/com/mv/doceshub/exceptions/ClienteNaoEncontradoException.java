package br.com.mv.doceshub.exceptions;

public class ClienteNaoEncontradoException extends NegocioException {
	

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ClienteNaoEncontradoException(Long id) {
		this(String.format("O cliente com id: '%d' não foi encontrado", id));
	}

}
