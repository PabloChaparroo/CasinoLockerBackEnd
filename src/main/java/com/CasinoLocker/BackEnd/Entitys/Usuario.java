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


    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fecha_alta_usuario")
    private LocalDate fechaAltaUsuario;

    @Column(name = "fecha_baja_usuario")
    private LocalDate fechaBajaUsuario;

    @Column(name = "fecha_modificacion_usuario")
    private LocalDate fechaModificacionUsuario;

    @Column(name = "estado_usuario")
    private EstadoUsuario estadoUsuario;

    //relacion con TipoUsuario
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "usuario_id")
    private TipoUsuario tipoUsuario;

}
