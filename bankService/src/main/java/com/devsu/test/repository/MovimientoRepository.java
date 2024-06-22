package com.devsu.test.repository;

import com.devsu.test.dto.ReporteEstadoCuentaDTO;
import com.devsu.test.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT new com.devsu.test.dto.ReporteEstadoCuentaDTO(m.fecha, cl.nombre, c.numeroCuenta, c.tipoCuenta, c.saldoInicial, c.estado, m.tipoMovimiento, m.valor, m.saldoDisponible) " +
            "FROM Movimiento m JOIN m.cuenta c JOIN c.cliente cl " +
            "WHERE cl.clienteId = :clienteId AND m.fecha BETWEEN :fechaInicial AND :fechaFinal AND c.estado = true AND cl.estado = true")
    List<ReporteEstadoCuentaDTO> generarReporteEstadoCuenta(@Param("fechaInicial") Date fechaInicial,
                                                            @Param("fechaFinal") Date fechaFinal,
                                                            @Param("clienteId") String clienteId);

}
