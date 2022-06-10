package br.com.cactus.service;

import br.com.cactus.controller.response.DadosCepResponse;
import br.com.cactus.exception.CepException;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ViaCepService {

    private final CepValidator validator;
    private final ViaCepClient viaCepClient;

    public DadosCepResponse listarCep(String cep) {
        if (!validator.cepValido(cep)) {
            throw new CepException("Informe o cep para realizar a pesquisa.");
        }
        try{
            return viaCepClient.listarCep(cep);
        }catch (FeignException.FeignClientException e){
            throw new CepException(e, "Cep inválido.");
        }
    }
}
