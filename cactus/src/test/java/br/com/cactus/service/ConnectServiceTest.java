package br.com.cactus.service;

import br.com.cactus.util.CepUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

class ConnectServiceTest {

    @Test
    public void deveValidarSeGerouConexaoTest() throws IOException {
        URL url = CepUtil.gerarURLParaConsultaViaCep("12369000");
        HttpURLConnection connection = ConnectService.getConnection(url);
        Assertions.assertNotNull(connection);
    }

    @Test
    public void deveValidarSeNaoGerouConexaoTest() throws IOException {
        URL url = CepUtil.gerarURLParaConsultaViaCep(null);
        HttpURLConnection connection = ConnectService.getConnection(url);
        Assertions.assertNull(connection);
    }

}