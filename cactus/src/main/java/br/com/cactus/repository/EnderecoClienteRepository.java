package br.com.cactus.repository;

import br.com.cactus.modelo.Cliente;
import br.com.cactus.modelo.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Integer> {
    List<EnderecoCliente> findByCliente(Cliente cliente);
}
