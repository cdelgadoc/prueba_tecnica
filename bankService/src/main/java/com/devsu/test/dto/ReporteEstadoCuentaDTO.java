package com.devsu.test.dto;

import com.devsu.test.model.TipoCuenta;
import com.devsu.test.model.TipoMovimiento;

import java.math.BigDecimal;
import java.util.Date;

public class ReporteEstadoCuentaDTO {

    private Date fecha;
    private String nombreCliente;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private TipoMovimiento tipoMovimiento;
    private BigDecimal valorMovimiento;
    private BigDecimal saldoDisponible;

    public ReporteEstadoCuentaDTO(Date fecha,
                                  String nombreCliente,
                                  String numeroCuenta,
                                  TipoCuenta tipoCuenta,
                                  BigDecimal saldoInicial,
                                  Boolean estado,
                                  TipoMovimiento tipoMovimiento,
                                  BigDecimal valorMovimiento,
                                  BigDecimal saldoDisponible) {
        this.fecha = fecha;
        this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.tipoMovimiento = tipoMovimiento;
        this.valorMovimiento = valorMovimiento;
        this.saldoDisponible = saldoDisponible;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getValorMovimiento() {
        return valorMovimiento;
    }

    public void setValorMovimiento(BigDecimal valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

}
