package br.com.mv.doceshub.exceptions;

public class EntidadeEmUsoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException() {
		super("A entidade está em uso");
	}
}
