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
        verify(viaCepClient, times(1)).listarCep(cep);
        verify(validator, times(1)).cepValido(cep);
        verifyNoMoreInteractions(viaCepClient);
        verifyNoMoreInteractions(validator);
    }

    @Test
    public void deveriaRetornarExcecaoFeignClientTest(){
        String cep = "86390000";
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
        String cep = "86390000";
        when(validator.cepValido(cep)).thenReturn(false);
        CepException cepException = Assertions.assertThrows(CepException.class, () -> viaCepService.listarCep(cep));
        Assertions.assertEquals("Informe o cep para realizar a pesquisa.", cepException.getMensagemErro());
        verify(validator, times(1)).cepValido(cep);
        verifyNoMoreInteractions(validator);
        verifyZeroInteractions(viaCepClient);
    }

    public DadosCepResponse createResposeForTest(){
        return new DadosCepResponse();
    }
}