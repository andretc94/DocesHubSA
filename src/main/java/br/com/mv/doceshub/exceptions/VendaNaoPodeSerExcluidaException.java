package br.com.mv.doceshub.exceptions;

public class VendaNaoPodeSerExcluidaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public VendaNaoPodeSerExcluidaException(String msg) {
		super(msg);
	}
	
	public VendaNaoPodeSerExcluidaException(Long id) {
		this(String.format("A venda de cod %d n�o pode ser excluida pois n�o est� quitada", id));
	}

}
