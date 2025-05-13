package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name ="tipo_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoUsuario extends BaseEntity {

    @Column(name = "nombre_tipo_usuario")
    private String nombreTipoUsuario;

    @Column(name = "fecha_alta_tipo_usuario")
    private LocalDate fechaAltaTipoUsuario;

    @Column(name = "fecha_modificación_tipo_usuario")
    private LocalDate fechaModificacionTipoUsuario;

    @Column(name = "fecha_baja_tipo_usuario")
    private LocalDate fechaBajaTipoUsuario;

    //Relacion con Restriccion
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "tipo_usuario_id")
    private Restriccion restriccion;
}
