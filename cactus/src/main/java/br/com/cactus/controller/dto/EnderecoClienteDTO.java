package br.com.cactus.controller.dto;

import br.com.cactus.modelo.EnderecoCliente;
import lombok.Data;

@Data
public class EnderecoClienteDTO {

    private String email;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public EnderecoClienteDTO() {
    }

    public EnderecoClienteDTO(EnderecoCliente enderecoCliente) {
        this.email = enderecoCliente.getCliente().getEmail();
        this.bairro = enderecoCliente.getBairro();
        this.logradouro = enderecoCliente.getLogradouro();
        this.numero = enderecoCliente.getNumero();
        this.cidade = enderecoCliente.getCidade();
        this.estado = enderecoCliente.getEstado();
        this.cep = enderecoCliente.getCep();
    }
}
