package br.com.mv.doceshub.exceptions;

public class VendaNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public VendaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public VendaNaoEncontradaException(Long id) {
		this(String.format("Não foi possivel encontrar venda com codigo %d", id));
	}

}
