package br.com.cactus.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data @Builder @AllArgsConstructor
@Entity(name = "cliente")
public class Cliente implements Serializable {

    @Id
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<EnderecoCliente> respostas = new ArrayList<>();

    public Cliente() {
    }


}
