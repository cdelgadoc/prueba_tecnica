package com.devsu.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name="persona_id")
public class Cliente extends Persona {

    @Valid
    @Column(name = "contrasena", nullable = false, length = 100)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, una letra minúscula, una letra mayúscula y un dígito.")
    private String contrasena;

    @Column(nullable = false)
    private Boolean estado;

    public Cliente() {}

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getClienteId() {
        return this.getIdentificacion();
    }

}
