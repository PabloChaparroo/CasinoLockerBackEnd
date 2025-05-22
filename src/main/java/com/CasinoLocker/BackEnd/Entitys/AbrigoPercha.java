package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="abrigo_percha")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbrigoPercha extends BaseEntity {

    @Column(name = "descripcion_abrigo_percha")
    private String descripcionAbrigoPercha;

    @Column(name = "hora_ingreso_abrigo_percha")
    private LocalDateTime horaIngresoAbrigoPercha;

    //Relaciones
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_reserva_id")
    private Reserva reserva;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_percha_id")
    private Percha percha;



}
