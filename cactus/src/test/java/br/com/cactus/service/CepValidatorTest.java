package br.com.cactus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CepValidatorTest {

    @Test
    public void deveriaCepValidoTest(){
        String cep = "86390000";
        Assertions.assertTrue(new CepValidator().cepValido(cep));
    }

    @Test
    public void deveriaCepInvalidoTest(){
        String cep = "";
        Assertions.assertFalse(new CepValidator().cepValido(cep));
    }
}