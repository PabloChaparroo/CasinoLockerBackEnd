package com.CasinoLocker.BackEnd.DTO;

import java.time.LocalDate;

import com.CasinoLocker.BackEnd.Enum.EstadoUsuario;
import com.CasinoLocker.BackEnd.Enum.Role;

public class UsuarioTablaDTO {
    private Long id;
    private String nombreUsuario;
    private int dniUsuario;
    private int telefonoUsuario;
    private String emailUsuario;
    private String descripcionUsuario;
    private LocalDate fechaAltaUsuario;
    private LocalDate fechaBajaUsuario;
    private LocalDate fechaModificacionUsuario;
    private EstadoUsuario estadoUsuario;
    private Role role; 

    public UsuarioTablaDTO(
    Long id,
    String nombreUsuario,
    int dniUsuario,
    int telefonoUsuario,
    String emailUsuario,
    String descripcionUsuario,
    LocalDate fechaAltaUsuario,
    LocalDate fechaBajaUsuario,
    LocalDate fechaModificacionUsuario,
    EstadoUsuario estadoUsuario,
    Role role
) {
    this.id = id;
    this.nombreUsuario = nombreUsuario;
    this.dniUsuario = dniUsuario;
    this.telefonoUsuario = telefonoUsuario;
    this.emailUsuario = emailUsuario;
    this.descripcionUsuario = descripcionUsuario;
    this.fechaAltaUsuario = fechaAltaUsuario;
    this.fechaBajaUsuario = fechaBajaUsuario;
    this.fechaModificacionUsuario = fechaModificacionUsuario;
    this.estadoUsuario = estadoUsuario;
    this.role = role;
}

    public Long getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getDniUsuario() {
        return dniUsuario;
    }

    public int getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getDescripcionUsuario() {
        return descripcionUsuario;
    }

    public LocalDate getFechaAltaUsuario() {
        return fechaAltaUsuario;
    }

    public LocalDate getFechaBajaUsuario() {
        return fechaBajaUsuario;
    }

    public LocalDate getFechaModificacionUsuario() {
        return fechaModificacionUsuario;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public Role getRole() {
        return role;
    }
}