package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

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
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "abrigo_percha_id")
    private Percha abrigoPercha;



}
