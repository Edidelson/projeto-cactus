package br.com.cactus.exception;

public class CepException extends RuntimeException {

    private String mensagemErro;

    public CepException(Throwable cause, String mensagemErro) {
        super(mensagemErro, cause);
        this.mensagemErro = mensagemErro;
    }

    public CepException(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}
