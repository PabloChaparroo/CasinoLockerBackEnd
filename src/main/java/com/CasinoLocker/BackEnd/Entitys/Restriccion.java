package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name ="restriccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restriccion extends BaseEntity {

    @Column(name = "nombre_restriccion")
    private String nombreRestriccion;

    @Column(name = "fecha_alta_restriccion")
    private LocalDate fechaAltaRestriccion;

    @Column(name = "fecha_modificación_restriccion")
    private LocalDate fechaModificacionRestriccion;

    @Column(name = "fecha_baja_restriccion")
    private LocalDate fechaBajaRestriccion;

    @Column(name = "verCliente")
    private Boolean verCliente;

    @Column(name = "verCasillero")
    private Boolean verCasillero;

    @Column(name = "verPercha")
    private Boolean verPercha;

    @Column(name = "verReporte")
    private Boolean verReporte;

    @Column(name = "verUsuario")
    private Boolean verUsuario;

    @Column(name = "accesoTotal")
    private Boolean accesoTotal;

    @Column(name = "administrar")
    private Boolean administrar;
}
