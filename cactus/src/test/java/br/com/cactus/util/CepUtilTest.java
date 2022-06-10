package br.com.cactus.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

class CepUtilTest {

    @Test
    public void deveGerarURLParaConexaoTest() throws MalformedURLException {
        URL url = CepUtil.gerarURLParaConsultaViaCep("12369000");
        Assertions.assertEquals("https://viacep.com.br/ws/12369000/json/", url.toString());
    }

    @Test
    public void naoDeveGerarURLParaConexaoTest() throws MalformedURLException {
        URL url = CepUtil.gerarURLParaConsultaViaCep("");
        Assertions.assertNull(url);
    }

    @Test
    public void deveValidarCepPreenchidoTrueTest() {
        Assertions.assertTrue(CepUtil.cepValido("12369000"));
    }

    @Test
    public void deveValidarCepPreenchidoFalseTest() {
        Assertions.assertFalse(CepUtil.cepValido(""));
    }

}