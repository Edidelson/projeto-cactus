package br.com.cactus.exception;

public class CepException extends Exception {

    private Integer codigoErro;
    private String mensagemErro;

    public CepException(String mensagemErro) {
        super();
        this.mensagemErro = mensagemErro;
    }

    public CepException(Integer codigoErro, String mensagemErro) {
        super();
        this.codigoErro = codigoErro;
        this.mensagemErro = mensagemErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public Integer getCodigoErro() {
        return codigoErro;
    }
}
