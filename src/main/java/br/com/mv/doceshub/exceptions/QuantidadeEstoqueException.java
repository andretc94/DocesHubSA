package br.com.mv.doceshub.exceptions;

public class QuantidadeEstoqueException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public QuantidadeEstoqueException() {
		super("A quantidade solicitada não possui estoque disponivel");
	}
	
	public QuantidadeEstoqueException(String mensagem) {
		super(mensagem);
	}
}
