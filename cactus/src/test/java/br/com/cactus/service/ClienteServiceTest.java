package br.com.cactus.service;

import br.com.cactus.controller.request.ClienteEnderecoRequest;
import br.com.cactus.controller.request.ClienteRequest;
import br.com.cactus.controller.response.ClienteEnderecoResponse;
import br.com.cactus.controller.response.ClienteResponse;
import br.com.cactus.domain.Cliente;
import br.com.cactus.domain.EnderecoCliente;
import br.com.cactus.repository.ClienteEnderecoRepository;
import br.com.cactus.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;
    @Mock
    private ClienteEnderecoRepository enderecoClienteRepository;
    @Mock
    private ClienteRepository clienteRepository;

    @Test()
    public void deveriaCadastrarEnderecoExcecaoTest() {
        String email = "teste@gmail.com";
        ClienteEnderecoRequest clienteEnderecoRequest = createResposeForTest();
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> clienteService.cadastrarEndereco(email, clienteEnderecoRequest));
        Assertions.assertEquals("Cliente inexistente", exception.getMessage());
    }

    @Test
    public void deveriaCadastrarEnderecoTest() {
        String email = "teste@gmail.com";
        Cliente cliente = Cliente.builder().email(email).build();
        ClienteEnderecoRequest clienteEnderecoRequest = createResposeForTest();
        when(enderecoClienteRepository.save(clienteEnderecoRequest.converter(cliente))).thenReturn(createEnderecoClienteForTest(cliente));
        when(clienteRepository.findByEmail(email)).thenReturn(cliente);
        ClienteEnderecoResponse clienteEnderecoResponse = clienteService.cadastrarEndereco(email, clienteEnderecoRequest);
        Assertions.assertNotNull(clienteEnderecoResponse);
        Assertions.assertEquals("56236000", clienteEnderecoResponse.getCep());
        Assertions.assertEquals("Avenida Brasil", clienteEnderecoResponse.getLogradouro());
        Assertions.assertEquals("789", clienteEnderecoResponse.getNumero());
        Assertions.assertEquals("Jardim Avorada", clienteEnderecoResponse.getBairro());
        Assertions.assertEquals("Porto Alegre", clienteEnderecoResponse.getCidade());
        Assertions.assertEquals("RS", clienteEnderecoResponse.getEstado());
        Assertions.assertEquals("teste@gmail.com", clienteEnderecoResponse.getEmail());

        verify(enderecoClienteRepository, times(1)).save(clienteEnderecoRequest.converter(cliente));
        verify(clienteRepository, times(1)).findByEmail(email);
        verifyNoMoreInteractions(clienteRepository);
        verifyNoMoreInteractions(enderecoClienteRepository);
    }

    @Test
    public void deveriaBuscarEnderecoPorClienteTest() {
        String email = "teste@gmail.com";
        Cliente cliente = Cliente.builder().email(email).build();
        ClienteEnderecoRequest clienteEnderecoRequest = createResposeForTest();
        when(enderecoClienteRepository.findByClienteEmail(email)).thenReturn(createListaEnderecoClienteForTest(cliente));
        List<ClienteEnderecoResponse> enderecos = clienteService.buscarEnderecoPorCliente(email);
        Assertions.assertNotNull(enderecos);
        Assertions.assertEquals(2, enderecos.size());
        verify(enderecoClienteRepository, times(1)).findByClienteEmail(email);
        verifyNoMoreInteractions(clienteRepository);
        verifyNoMoreInteractions(enderecoClienteRepository);
    }


    @Test
    public void deveriaCadastrarClienteTest() {
        String email = "teste@gmail.com";
        Cliente cliente = Cliente.builder().email(email).build();
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        ClienteResponse clienteResponse = clienteService.cadastrarCliente(createClienteResposeForTest(cliente));
        Assertions.assertNotNull(clienteResponse);
        Assertions.assertEquals("teste@gmail.com", clienteResponse.getEmail());
        verify(clienteRepository, times(1)).save(cliente);
        verifyNoMoreInteractions(clienteRepository);
    }

    private List<EnderecoCliente> createListaEnderecoClienteForTest(Cliente cliente) {
        List<EnderecoCliente> enderecoClientes = new ArrayList<>();
        enderecoClientes.add(createEnderecoClienteForTest(cliente));
        enderecoClientes.add(createSecundEnderecoClienteForTest(cliente));
        return enderecoClientes;
    }

    private ClienteEnderecoRequest createResposeForTest(){
        return ClienteEnderecoRequest.builder()
                .cep("56236000")
                .numero("789")
                .logradouro("Avenida Brasil")
                .bairro("Jardim Avorada")
                .cidade("Porto Alegre")
                .estado("RS")
                .build();
    }

    private ClienteRequest createClienteResposeForTest(Cliente cliente){
        return ClienteRequest
                .builder()
                .email(cliente.getEmail())
                .build();
    }

    private EnderecoCliente createEnderecoClienteForTest(Cliente cliente) {
        return EnderecoCliente
                .builder()
                .id(1)
                .cliente(cliente)
                .logradouro("Avenida Brasil")
                .cidade("Porta Alegre")
                .cep("56236000")
                .estado("RS")
                .numero("786")
                .estado("RS")
                .build();
    }

    private EnderecoCliente createSecundEnderecoClienteForTest(Cliente cliente) {
        return EnderecoCliente
                .builder()
                .id(1)
                .cliente(cliente)
                .logradouro("Rua Parana Brasil")
                .cidade("Rua Alegre")
                .cep("56236000")
                .estado("RS")
                .numero("852")
                .estado("RS")
                .build();
    }
}