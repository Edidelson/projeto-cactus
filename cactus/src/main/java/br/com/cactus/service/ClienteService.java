package br.com.cactus.service;

import br.com.cactus.controller.request.ClienteEnderecoRequest;
import br.com.cactus.controller.request.ClienteRequest;
import br.com.cactus.controller.response.ClienteResponse;
import br.com.cactus.controller.response.EnderecoClienteResponse;
import br.com.cactus.domain.Cliente;
import br.com.cactus.domain.EnderecoCliente;
import br.com.cactus.repository.ClienteEnderecoRepository;
import br.com.cactus.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClienteService {

    private ClienteEnderecoRepository enderecoClienteRepository;
    private ClienteRepository clienteRepository;

    public EnderecoClienteResponse cadastrarEndereco(String email, ClienteEnderecoRequest clienteEnderecoRequest) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente==null){
            throw new IllegalArgumentException("Cliente inexistente");
        }
        EnderecoCliente enderecoCliente = clienteEnderecoRequest.converter(cliente);
        enderecoClienteRepository.save(enderecoCliente);
        return getEnderecoClienteResponse(enderecoCliente);
    }

    private EnderecoClienteResponse getEnderecoClienteResponse(EnderecoCliente enderecoCliente) {
        return EnderecoClienteResponse
            .builder()
            .email(enderecoCliente.getCliente().getEmail())
            .logradouro(enderecoCliente.getLogradouro())
            .cidade(enderecoCliente.getCidade())
            .cep(enderecoCliente.getCep())
            .estado(enderecoCliente.getEstado())
            .numero(enderecoCliente.getNumero())
            .bairro(enderecoCliente.getBairro()).build();
    }

    public List<EnderecoClienteResponse> buscarEnderecoPorCliente(String email){
        return enderecoClienteRepository
                .findByClienteEmail(email)
                .stream()
                .map(this::getEnderecoClienteResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponse cadastrarCliente(ClienteRequest clienteRequest){
        Cliente cliente = clienteRequest.converter();
        clienteRepository.save(cliente);
        return ClienteResponse
                .builder()
                .email(cliente.getEmail())
                .build();
    }
}
