package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="conf_Objeto_perdido")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfObjetoPerdido extends BaseEntity {


    @Column(name = "fecha_alta_conf_objeto_perdido")
    private LocalDateTime fechaAltaConfObjetoPerdido;

    @Column(name = "fecha_modificación_conf_objeto_perdido")
    private LocalDateTime fechaModificacionConfObjetoPerdido;

    @Column(name = "fecha_baja_conf_objeto_perdido")
    private LocalDateTime fechaBajaConfObjetoPerdido;

    //Relacion con objeto
    @OneToMany
    @JoinColumn(name = "fk_confObjetoPerdido_id")
    private List<Objeto> objetos = new ArrayList<>();

    //Relacion con Casillero
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_casillero_id")
    private Casillero casillero;



}
