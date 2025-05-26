package com.CasinoLocker.BackEnd.Entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name ="tipo_perfil")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoPerfil extends BaseEntity{

    @Column(name = "nombre_tipo_perfil")
    private LocalDate nombreTipoPerfil;

    @Column(name = "fecha_alta_tipo_perfil")
    private LocalDate fechaAltaTipoPerfil;

    @Column(name = "fecha_baja_tipo_perfil")
    private LocalDate fechaBajaTipoPerfil;

    @Column(name = "fecha_modificacion_tipo_perfil")
    private LocalDate fechaModificacionTipoPerfil;
}
