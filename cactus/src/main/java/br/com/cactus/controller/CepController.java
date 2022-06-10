package br.com.cactus.controller;

import br.com.cactus.config.Constants;
import br.com.cactus.controller.dto.DadosCepDTO;
import br.com.cactus.controller.dto.ErroDTO;
import br.com.cactus.exception.CepException;
import br.com.cactus.util.CepUtil;
import br.com.cactus.service.ConnectService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;

@RestController
@RequestMapping("/cep")
public class CepController {

    @GetMapping("/{cep}")
    public ResponseEntity<DadosCepDTO> lista(@PathVariable String cep) throws Exception {
        try {
            if (CepUtil.cepValido(cep)) {
                HttpURLConnection connection = ConnectService.getConnection(CepUtil.gerarURLParaConsultaViaCep(cep));
                String jsonCep = ConnectService.getResponse(connection);
                int responseCode = connection.getResponseCode();

                if (isRetornoComErro(responseCode)) {
                    throw new CepException(responseCode, "Erro ao realizar a consulta do cep");
                }
                DadosCepDTO dadosCepDTO = new Gson().fromJson(jsonCep, DadosCepDTO.class);
                connection.disconnect();
                return ResponseEntity.ok(dadosCepDTO);
            } else {
                throw new CepException("Informe o cep para realizar a pesquisa.");
            }
        } catch (CepException e){
            return new ResponseEntity(new ErroDTO(e.getMensagemErro()), HttpStatus.FOUND);
        }
    }

    private boolean isRetornoComErro(int responseCode) {
        return Constants.HTTP_COD_SUCESSO != responseCode;
    }

}
