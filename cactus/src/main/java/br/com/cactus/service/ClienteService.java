package br.com.cactus.service;

import br.com.cactus.controller.request.ClienteEnderecoRequest;
import br.com.cactus.controller.request.ClienteRequest;
import br.com.cactus.controller.response.ClienteResponse;
import br.com.cactus.controller.response.ClienteEnderecoResponse;
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

    public ClienteEnderecoResponse cadastrarEndereco(String email, ClienteEnderecoRequest clienteEnderecoRequest) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente==null){
            throw new IllegalArgumentException("Cliente inexistente");
        }
        EnderecoCliente enderecoCliente = clienteEnderecoRequest.converter(cliente);
        ClienteEnderecoResponse clienteEnderecoResponse = getEnderecoClienteResponse(enderecoCliente);
        enderecoClienteRepository.save(enderecoCliente);
        return clienteEnderecoResponse;
    }

    private ClienteEnderecoResponse getEnderecoClienteResponse(EnderecoCliente enderecoCliente) {
        return ClienteEnderecoResponse
            .builder()
            .email(enderecoCliente.getCliente().getEmail())
            .logradouro(enderecoCliente.getLogradouro())
            .cidade(enderecoCliente.getCidade())
            .cep(enderecoCliente.getCep())
            .estado(enderecoCliente.getEstado())
            .numero(enderecoCliente.getNumero())
            .bairro(enderecoCliente.getBairro()).build();
    }

    public List<ClienteEnderecoResponse> buscarEnderecoPorCliente(String email){
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
