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
@Table(name ="conf_tipo_casillero")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TipoCasillero extends BaseEntity {


    @Column(name = "nombre_tipo_casillero")
    private String nombreTipoCasillero;

    @Column(name = "fecha_alta_tipo_casillero")
    private LocalDate fechaAltaTipoCasillero;

    @Column(name = "fecha_modificación_tipo_casillero")
    private LocalDate fechaModificacionTipoCasillero;

    @Column(name = "fecha_baja_tipo_casillero")
    private LocalDate fechaBajaTipoCasillero;
}
