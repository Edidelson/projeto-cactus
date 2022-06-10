package br.com.cactus.controller.dto;

import br.com.cactus.modelo.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {
    private String email;

    public ClienteDTO(Cliente cliente) {
        this.email = cliente.getEmail();
    }
}
