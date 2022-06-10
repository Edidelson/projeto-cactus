package br.com.cactus.repository;

import br.com.cactus.domain.Cliente;
import br.com.cactus.domain.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteEnderecoRepository extends JpaRepository<EnderecoCliente, Integer> {
    List<EnderecoCliente> findByCliente(Cliente cliente);
    List<EnderecoCliente> findByClienteEmail(String email);
}
