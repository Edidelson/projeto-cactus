package br.com.cactus.controller;

import br.com.cactus.controller.dto.ClienteDTO;
import br.com.cactus.controller.form.ClienteForm;
import br.com.cactus.modelo.Cliente;
import br.com.cactus.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody @Valid ClienteForm form){
        Cliente cliente = form.converter();
        clienteRepository.save(cliente);
        return ResponseEntity.ok(new ClienteDTO(cliente));
    }
}
