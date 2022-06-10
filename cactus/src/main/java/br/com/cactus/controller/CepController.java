package br.com.cactus.controller;

import br.com.cactus.controller.response.DadosCepResponse;
import br.com.cactus.controller.response.ErroResponse;
import br.com.cactus.exception.CepException;
import br.com.cactus.service.ViaCepService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/cep")
public class CepController {

    private final ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public ResponseEntity<DadosCepResponse> lista(@PathVariable String cep) {
        try {
            return ResponseEntity.ok(viaCepService.listarCep(cep));
        } catch (CepException e){
            return new ResponseEntity(new ErroResponse(e.getMensagemErro()), HttpStatus.BAD_REQUEST);
        }
    }
}
