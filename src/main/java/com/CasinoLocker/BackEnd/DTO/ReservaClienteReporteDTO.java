package com.CasinoLocker.BackEnd.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservaClienteReporteDTO {
    private String nombreCliente;
    private String ubicacion;
    private String fechaHoraInicio;
    private String fechaHoraFinalizacion;
    private List<ObjetoDTO> objetos;
    private String estadoReserva;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ObjetoDTO {
        private int numeroObjeto;
        private String descripcionObjeto;
    }
}
