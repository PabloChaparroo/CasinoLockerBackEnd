package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Entity
@Table(name ="cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente extends BaseEntity{

    //Atributos
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "dni_cliente")
    private int dniCliente;
    @Column(name = "telefono_cliente")
    private int telefonoCliente;
    @Column(name = "mail_cliente")
    private String mailCliente;

    @Column(name = "fecha_hora_alta_cliente")
    private LocalDate fechaHoraAltaCliente;
    @Column(name = "fecha_hora_modificacion_cliente")
    private LocalDate fechaHoraModificacionCliente;
    @Column(name = "fecha_hora_baja_cliente")
    private LocalDate fechaHoraBajaCliente;

}
