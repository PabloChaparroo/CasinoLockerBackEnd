package com.CasinoLocker.BackEnd.Entitys;
import com.CasinoLocker.BackEnd.Enum.EstadoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name ="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends BaseEntity{


    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;


    @Column(name = "dni_usuario")
    private int dniUsuario;

    @Column(name = "telefono_usuario")
    private int telefonoUsuario;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "descripcion_usuario")
    private String descripcionUsuario;

    @Column(name = "fecha_alta_usuario")
    private LocalDate fechaAltaUsuario;

    @Column(name = "fecha_baja_usuario")
    private LocalDate fechaBajaUsuario;

    @Column(name = "fecha_modificacion_usuario")
    private LocalDate fechaModificacionUsuario;

    @Column(name = "estado_usuario")
    private EstadoUsuario estadoUsuario;





}
