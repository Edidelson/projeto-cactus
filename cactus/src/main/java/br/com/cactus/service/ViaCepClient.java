package br.com.cactus.service;

import br.com.cactus.controller.response.DadosCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="${via.cep.url}", value = "ViaCepClient")
public interface ViaCepClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
    DadosCepResponse listarCep(@PathVariable("cep") String cep);

}
