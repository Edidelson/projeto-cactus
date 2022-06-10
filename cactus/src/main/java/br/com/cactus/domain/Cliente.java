package br.com.cactus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Entity(name = "cliente")
public class Cliente implements Serializable {

    @Id
    private String email;
    @OneToMany(mappedBy = "cliente")
    private List<EnderecoCliente> respostas = new ArrayList<>();

    public Cliente() {
    }

     public void setEmail(String email) {
         this.email = email;
     }

     public void setRespostas(List<EnderecoCliente> respostas) {
         this.respostas = respostas;
     }

     public String getEmail() {
         return email;
     }

     public List<EnderecoCliente> getRespostas() {
         return respostas;
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
