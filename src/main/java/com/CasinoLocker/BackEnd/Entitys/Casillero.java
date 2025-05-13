package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name ="casillero")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Casillero extends BaseEntity{

    @Column(name = "numero_casillero")
    private int numeroCasillero;

    @Column(name = "fecha_alta_casillero")
    private LocalDate fechaAltaCasillero;

    @Column(name = "fecha_modificación_casillero")
    private LocalDate fechaModificacionCasillero;

    @Column(name = "fecha_baja_casillero")
    private LocalDate fechaBajaCasillero;

    //Relacion con TipoCasillero
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "casillero_tipoCasillero_id")
    private TipoCasillero tipoCasillero;

    //Relacion con EstadoCasilleroPercha
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "casillero_estadoCasillero_id")
    private EstadoCasilleroPercha estadoCasilleroPercha;
}
