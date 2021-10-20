package br.com.mv.doceshub.exceptions;

public class FormaPagamentoNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoNaoEncontradaException(Long id) {
		super(String.format("Não foi possivel encontrar Forma de Pagamento com id informado: '%d'", id));
	}

}
