package com.CasinoLocker.BackEnd.Entitys;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name ="estado_casillero_percha")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoCasilleroPercha extends BaseEntity{

    @Column(name = "nombre_estado_casillero_percha")
    private String nombreEstadoCasilleroPercha;

    @Column(name = "color_estado_casillero_percha")
    private String colorEstadoCasilleroPercha;

    @Column(name = "fecha_alta_estado_casillero_percha")
    private LocalDate fechaAltaEstadoCasilleroPercha;

    @Column(name = "fecha_modificación_estado_casillero_percha")
    private LocalDate fechaModificacionEstadoCasilleroPercha;

    @Column(name = "fecha_baja_estado_casillero_percha")
    private LocalDate fechaBajaEstadoCasilleroPercha;

    @Column(name = "Reservable")
    private Boolean reservable;


}
