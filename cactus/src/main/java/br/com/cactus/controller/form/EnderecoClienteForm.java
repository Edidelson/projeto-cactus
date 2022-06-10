package br.com.cactus.controller.form;

import br.com.cactus.modelo.Cliente;
import br.com.cactus.modelo.EnderecoCliente;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EnderecoClienteForm {

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
        return new EnderecoCliente()
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
