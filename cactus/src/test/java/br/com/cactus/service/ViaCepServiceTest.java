package br.com.cactus.service;

import br.com.cactus.controller.response.DadosCepResponse;
import br.com.cactus.exception.CepException;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViaCepServiceTest {

    @InjectMocks
    private ViaCepService viaCepService;
    @Mock
    private ViaCepClient viaCepClient;
    @Mock
    private CepValidator validator;

    @Test
    public void deveriaListarCepTest(){
        String cep = "86390000";
        when(validator.cepValido(cep)).thenReturn(true);
        when(viaCepClient.listarCep(cep)).thenReturn(createResposeForTest());
        DadosCepResponse dadosCepResponse = viaCepService.listarCep(cep);
        Assertions.assertNotNull(dadosCepResponse);
        Assertions.assertEquals("56236000", dadosCepResponse.getCep());
        Assertions.assertEquals("Avenida Brasil", dadosCepResponse.getLogradouro());
        Assertions.assertEquals("123123", dadosCepResponse.getGia());
        Assertions.assertEquals("43", dadosCepResponse.getDdd());
        Assertions.assertEquals("Jardim Avorada", dadosCepResponse.getBairro());
        Assertions.assertEquals("Porto Alegre", dadosCepResponse.getLocalidade());
        Assertions.assertEquals("RS", dadosCepResponse.getUf());
        Assertions.assertEquals("Apartamento", dadosCepResponse.getComplemento());
        verify(viaCepClient, times(1)).listarCep(cep);
        verify(validator, times(1)).cepValido(cep);
        verifyNoMoreInteractions(viaCepClient);
        verifyNoMoreInteractions(validator);
    }

    @Test
    public void deveriaRetornarExcecaoFeignClientTest(){
        String cep = "56236000";
        when(validator.cepValido(cep)).thenReturn(true);
        when(viaCepClient.listarCep(cep)).thenThrow(FeignException.FeignClientException.class);
        CepException cepException = Assertions.assertThrows(CepException.class, () -> viaCepService.listarCep(cep));
        Assertions.assertEquals("Cep inválido.", cepException.getMensagemErro());
        verify(viaCepClient, times(1)).listarCep(cep);
        verify(validator, times(1)).cepValido(cep);
        verifyNoMoreInteractions(viaCepClient);
        verifyNoMoreInteractions(validator);
    }

    @Test
    public void deveriaRetornarExcecaoParaCepInvalidoTest(){
        String cep = "56236000";
        when(validator.cepValido(cep)).thenReturn(false);
        CepException cepException = Assertions.assertThrows(CepException.class, () -> viaCepService.listarCep(cep));
        Assertions.assertEquals("Informe o cep para realizar a pesquisa.", cepException.getMensagemErro());
        verify(validator, times(1)).cepValido(cep);
        verifyNoMoreInteractions(validator);
        verifyZeroInteractions(viaCepClient);
    }

    public DadosCepResponse createResposeForTest(){
        return DadosCepResponse.builder()
                .cep("56236000")
                .logradouro("Avenida Brasil")
                .complemento("Apartamento")
                .gia("123123")
                .localidade("Porto Alegre")
                .uf("RS")
                .siafi("676776")
                .bairro("Jardim Avorada")
                .ddd("43")
                .build();
    }
}