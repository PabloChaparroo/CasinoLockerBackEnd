package com.CasinoLocker.BackEnd.Entitys;

<<<<<<< HEAD
public class Perfil {
=======
import com.CasinoLocker.BackEnd.Enum.EstadoPerfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name ="perfil")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Perfil extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fecha_alta_perfil")
    private LocalDate fechaAltaPerfil;

    @Column(name = "fecha_baja_perfil")
    private LocalDate fechaBajaPerfil;

    @Column(name = "fecha_modificacion_perfil")
    private LocalDate fechaModificacionPerfil;

    private EstadoPerfil estadoPerfil;

    //Relacion usuario
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    //relacion con TipoPerfil
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_tipoPerfil_id")
    private TipoPerfil tipoPerfil;
>>>>>>> master
}
