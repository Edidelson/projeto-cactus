package br.com.cactus.util;

import java.net.MalformedURLException;
import java.net.URL;

public class CepUtil {

    /**
     * Método responsável por gerar a URL para consulta
     * @param cep
     * @return
     */
    public static URL gerarURLParaConsultaViaCep(String cep) throws MalformedURLException {
        return cepValido(cep)? new URL(new StringBuilder().append("https://viacep.com.br/ws/").append(cep).append("/json/").toString()):null;
    }

    /**
     * Método responsável por validar se o cep foi preenchido
     * @param cep
     * @return
     */
    public static boolean cepValido(String cep){
        return cep!=null && !"".equals(cep.trim());
    }

}
