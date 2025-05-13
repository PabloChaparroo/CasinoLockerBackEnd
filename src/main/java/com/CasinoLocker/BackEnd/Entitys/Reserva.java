package com.CasinoLocker.BackEnd.Entitys;
import com.CasinoLocker.BackEnd.Enum.EstadoReserva;
import com.CasinoLocker.BackEnd.Enum.EstadoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="reserva")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reserva extends BaseEntity {

    @Column(name = "numero_reserva")
    private int numeroReserva;

    @Column(name = "fecha_alta_reserva")
    private LocalDateTime fechaAltaReserva;

    @Column(name = "fecha_modificación_reserva")
    private LocalDateTime fechaModificacionReserva;

    @Column(name = "fecha_baja_reserva")
    private LocalDateTime fechaBajaReserva;

    @Column(name = "estado_reserva")
    private EstadoReserva estadoReserva;

    //Relacion con objeto
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //Si se elimina una reserva entonces también se eliminan sus objetos relacionados
    private List<Objeto> objetoList = new ArrayList<Objeto>();

    //Relacion con Casillero
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "reserva_casillero_id")
    private Casillero casillero;

    //Relacion con Usuario
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "reserva_usuario_id")
    private Usuario usuario;

    //Relacion con Cliente
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "reserva_cliente_id")
    private Cliente cliente;

    //Relacion con AbrigoPercha
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AbrigoPercha> abrigoPerchasList = new ArrayList<AbrigoPercha>();
}
