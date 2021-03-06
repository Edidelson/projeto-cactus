package br.com.cactus.controller.request;

import br.com.cactus.domain.Cliente;
import br.com.cactus.domain.EnderecoCliente;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @Builder
public class ClienteEnderecoRequest {

    @NotNull @NotEmpty
    private String logradouro;
    @NotNull @NotEmpty
    private String numero;
    @NotNull @NotEmpty
    private String bairro;
    @NotNull @NotEmpty
    private String cidade;
    @NotNull @NotEmpty
    private String estado;
    @NotNull @NotEmpty
    private String cep;

    public EnderecoCliente converter(Cliente cliente) {
        return EnderecoCliente
                .builder()
                .cliente(cliente)
                .logradouro(this.logradouro)
                .numero(this.numero)
                .bairro(this.bairro)
                .cidade(this.cidade)
                .estado(this.estado)
                .cep(this.cep)
                .build();
    }
}
