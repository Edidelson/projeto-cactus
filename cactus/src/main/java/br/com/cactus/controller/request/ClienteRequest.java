package br.com.cactus.controller.request;

import br.com.cactus.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotNull
    @NotEmpty
    private String email;

    public Cliente converter() {
        return Cliente.builder().email(this.email).build();
    }
}
