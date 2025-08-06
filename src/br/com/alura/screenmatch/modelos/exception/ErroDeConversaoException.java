package br.com.alura.screenmatch.modelos.exception;

public class ErroDeConversaoException extends RuntimeException {

    private String mensagem;

    public ErroDeConversaoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }
}
