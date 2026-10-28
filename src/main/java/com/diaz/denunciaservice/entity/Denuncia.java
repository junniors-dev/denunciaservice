package com.diaz.denunciaservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "denuncias")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "el DNI debe tener 8 dígitos")
    @Column(length = 8, nullable = false)
    private String dni;

    // Si el cliente no la envía, se registra con la hora de Lima
    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotBlank
    @Size(max = 30)
    @Column(length = 30, nullable = false)
    private String tipo;

    @NotBlank
    @Size(max = 200)
    @Column(length = 200, nullable = false)
    private String direccion;

    @NotBlank
    @Size(max = 255)
    @Column(length = 255, nullable = false)
    private String descripcion;

    // 1 = pendiente, 0 = atendida por serenazgo
    @ColumnDefault("1")
    @Column(nullable = false)
    private Integer estado = 1;

    @PrePersist
    void alRegistrar() {
        if (fecha == null) {
            fecha = LocalDateTime.now(ZoneId.of("America/Lima"));
        }
        estado = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
