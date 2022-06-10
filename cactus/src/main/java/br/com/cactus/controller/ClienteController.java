package br.com.cactus.controller;

import br.com.cactus.controller.request.ClienteRequest;
import br.com.cactus.controller.response.ClienteResponse;
import br.com.cactus.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody @Valid ClienteRequest clienteRequest){
        return ResponseEntity.ok(clienteService.cadastrarCliente(clienteRequest));
    }
}
