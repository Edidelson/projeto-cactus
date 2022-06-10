package br.com.cactus.controller.response;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class EnderecoClienteResponse {

    private String email;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
