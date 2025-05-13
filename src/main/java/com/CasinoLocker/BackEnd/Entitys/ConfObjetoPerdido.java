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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //Si se elimina una cofg entonces también se eliminan sus objetos relacionados
    private List<Objeto> objeto = new ArrayList<Objeto>();

    //Relacion con Casillero
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "conf_objeto_perdido_id")
    private Casillero casillero;



}
