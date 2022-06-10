package br.com.cactus.controller.form;

import br.com.cactus.modelo.Cliente;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClienteForm {

    @NotNull
    @NotEmpty
    private String email;

    public Cliente converter() {
        return new Cliente().builder().email(this.email).build();
    }
}
