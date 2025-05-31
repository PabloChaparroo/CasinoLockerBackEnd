package com.CasinoLocker.BackEnd.Entitys;
import com.CasinoLocker.BackEnd.Enum.EstadoReserva;
import com.CasinoLocker.BackEnd.Enum.EstadoUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Column(name = "fecha_Finalizacion_reserva")
    private LocalDateTime fechaFinalizacionReserva;

    @Column(name = "fecha_baja_reserva")
    private LocalDateTime fechaBajaReserva;

    @Enumerated(EnumType.STRING)  // Esto es importante para usar los nombres textuales
    @Column(name = "estado_reserva")
    private EstadoReserva estadoReserva;

    //Relacion con objeto
    @OneToMany
    @JoinColumn(name = "fk_reserva_id")
   @JsonProperty("objetos") // Esto mapea la propiedad "objetos" del JSON a objetoList
    private List<Objeto> objetoList = new ArrayList<>();

    //Relacion con Casillero
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_casillero_id")
    private Casillero casillero;

    //Relacion con Usuario
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_usuario_id")
    private Usuario usuario;

    //Relacion con Cliente
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_percha_id")
    private  Percha percha;
    //Relacion con AbrigoPercha
    //@OneToMany
    //@JoinColumn(name = "fk_reserva_id")
    //private List<AbrigoPercha> abrigoPerchasList = new ArrayList<AbrigoPercha>();
}
