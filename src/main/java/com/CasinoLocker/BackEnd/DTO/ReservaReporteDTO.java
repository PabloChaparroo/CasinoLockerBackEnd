package com.CasinoLocker.BackEnd.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservaReporteDTO {
    private int numeroReserva;
    private String cliente;
    private String estado;
    private String ubicacion;
    private String fechaHoraAlta;
    private String fechaHoraFinalizacion;
}
