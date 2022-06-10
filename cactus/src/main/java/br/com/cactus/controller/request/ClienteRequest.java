package br.com.cactus.controller.request;

import br.com.cactus.domain.Cliente;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @Builder
public class ClienteRequest {

    @NotNull
    @NotEmpty
    private String email;

    public Cliente converter() {
        return Cliente.builder().email(this.email).build();
    }
}
