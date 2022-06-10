package br.com.cactus.controller.response;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ClienteResponse {

    private String email;
}
