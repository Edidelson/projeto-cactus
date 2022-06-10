package br.com.cactus.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "endereco_cliente") @Data @Builder @AllArgsConstructor @NoArgsConstructor
public class EnderecoCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Cliente cliente;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "numero")
    private String numero;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String  cidade;
    @Column(name = "estado")
    private String estado;
    @Column(name = "cep")
    private String cep;
}
