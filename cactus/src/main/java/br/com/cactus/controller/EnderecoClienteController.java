package br.com.cactus.controller;

import br.com.cactus.controller.dto.EnderecoClienteDTO;
import br.com.cactus.controller.dto.ErroDTO;
import br.com.cactus.controller.form.EnderecoClienteForm;
import br.com.cactus.exception.CepException;
import br.com.cactus.modelo.Cliente;
import br.com.cactus.modelo.EnderecoCliente;
import br.com.cactus.repository.ClienteRepository;
import br.com.cactus.repository.EnderecoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/endereco-cliente")
public class EnderecoClienteController {

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/{email}")
    public ResponseEntity<EnderecoClienteDTO> cadastrarEnderecoCliente(@PathVariable String email, @RequestBody @Valid EnderecoClienteForm enderecoClienteForm){
        Cliente cliente = clienteRepository.findByEmail(email);
        EnderecoCliente enderecoCliente = enderecoClienteForm.converter(cliente);
        enderecoClienteRepository.save(enderecoCliente);
        return ResponseEntity.ok(new EnderecoClienteDTO(enderecoCliente));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<EnderecoClienteDTO>> listarEnderecoPorCliente(@PathVariable String email){
        try {
            Cliente cliente = clienteRepository.findByEmail(email);
            if (Objects.isNull(cliente)) {
                throw new Exception("Cliente não encontrado.");
            }
            List<EnderecoCliente> listaEnderecoCliente = enderecoClienteRepository.findByCliente(cliente);
            List<EnderecoClienteDTO> listaEnderecoClienteDTO = listaEnderecoCliente.stream().map(EnderecoClienteDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(listaEnderecoClienteDTO);
        }catch (Exception e){
            return new ResponseEntity(new ErroDTO(e.getMessage()), HttpStatus.FOUND);
        }
    }
}
