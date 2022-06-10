package br.com.cactus.service;

import br.com.cactus.config.Constants;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@Service
public class ConnectService {

    private ConnectService(){}

    /**
     * Método responsável por obter o retorno de consultar de um determinado enpoint
     * @param connection
     * @return
     * @throws IOException
     */
    public static String getResponse(final HttpURLConnection connection) throws IOException {
        String line;
        StringBuilder responseList = new StringBuilder();
        InputStream in;
        if (connection.getResponseCode() != Constants.HTTP_COD_SUCESSO) {
            in = connection.getErrorStream();
        } else {
            in = connection.getInputStream();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(in, Constants.UTF_8));
        while ((line = br.readLine()) != null) {
            responseList.append(line);
        }
        return responseList.toString();
    }

    /**
     * Método responspavel por criar a conexão com o serviço disponibilizado na URL
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getConnection(final URL url) throws IOException {
        if(Objects.nonNull(url)) {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            return connection;
        } else {
            return null;
        }
    }

}
