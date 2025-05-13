package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name ="percha")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Percha extends BaseEntity {

    @Column(name = "numero_percha")
    private int numeroPercha;

    @Column(name = "fecha_alta_percha")
    private LocalDate fechaAltaPercha;

    @Column(name = "fecha_modificación_percha")
    private LocalDate fechaModificacionPercha;

    @Column(name = "fecha_baja_percha")
    private LocalDate fechaBajaPercha;

    //Relaciones
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "percha_id")
    private EstadoCasilleroPercha estadoCasilleroPercha;

}
