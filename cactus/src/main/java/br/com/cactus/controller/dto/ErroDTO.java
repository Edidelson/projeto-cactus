package br.com.cactus.controller.dto;

import lombok.Data;

@Data
public class ErroDTO {

    private String mensagem;

    public ErroDTO() {
    }

    public ErroDTO(String mensagem) {
        this.mensagem = mensagem;
    }

}
