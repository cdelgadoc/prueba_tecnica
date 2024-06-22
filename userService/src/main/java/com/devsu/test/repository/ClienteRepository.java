package com.devsu.test.repository;

import com.devsu.test.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findOneByIdentificacion(String identificacion);

}
