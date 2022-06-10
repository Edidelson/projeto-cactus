package br.com.cactus.service;

import org.springframework.stereotype.Component;

@Component
public class CepValidator {

    /**
     * Método responsável por validar se o cep foi preenchido
     * @param cep
     * @return
     */
    public boolean cepValido(String cep){
        return cep!=null && !"".equals(cep.trim());
    }

}
