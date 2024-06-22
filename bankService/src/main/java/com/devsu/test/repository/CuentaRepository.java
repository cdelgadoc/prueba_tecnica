package com.devsu.test.repository;

import com.devsu.test.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query("SELECT c FROM Cuenta c WHERE c.cliente.clienteId = :clienteId")
    List<Cuenta> findByClienteId(@Param("clienteId") String clienteId);

}
