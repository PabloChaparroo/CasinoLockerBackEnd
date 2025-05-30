package com.CasinoLocker.BackEnd.Entitys;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name ="objeto")
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Objeto extends BaseEntity{

    @Column(name = "numero_objeto")
    private int numeroObjeto;

    @Column(name = "descripcion_objeto")
    private String descripcionObjeto;

    @Column(name = "fecha_alta_objeto")
    private LocalDate fechaAltaObjeto;

    @Column(name = "fecha_modificación_objeto")
    private LocalDate fechaModificacionObjeto;

    @Column(name = "fecha_baja_objeto")
    private LocalDate fechaBajaObjeto;

    public void setReserva(Reserva reservaGuardada) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setReserva'");
    }
}
