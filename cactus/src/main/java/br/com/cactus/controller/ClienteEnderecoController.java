package br.com.cactus.controller;

import br.com.cactus.controller.request.ClienteEnderecoRequest;
import br.com.cactus.controller.response.ClienteEnderecoResponse;
import br.com.cactus.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes/enderecos")
public class ClienteEnderecoController {

    private final ClienteService clienteService;

    @PostMapping("/{email}")
    public ResponseEntity<ClienteEnderecoResponse> cadastrarEndereco(@PathVariable String email, @RequestBody @Valid ClienteEnderecoRequest enderecoRequest){
        ClienteEnderecoResponse enderecoClienteResponse = clienteService.cadastrarEndereco(email, enderecoRequest);
        return ResponseEntity.ok(enderecoClienteResponse);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<ClienteEnderecoResponse>> listarEnderecoPorCliente(@PathVariable String email){
        return  ResponseEntity.ok(clienteService.buscarEnderecoPorCliente(email));
    }
}
