package br.com.cactus.repository;

import br.com.cactus.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Cliente findByEmail(String email);
}
