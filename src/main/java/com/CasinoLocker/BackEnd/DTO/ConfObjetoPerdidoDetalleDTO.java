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
public class ConfObjetoPerdidoDetalleDTO {
    private List<ReservaDetalleDTO> reservas;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ReservaDetalleDTO {
        private Long idReserva;
        private String clienteNombre;
        private String ubicacion;
        private String fechaHoraReserva;
        private List<ObjetoDTO> objetos;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ObjetoDTO {
        private int numeroObjeto;
        private String descripcionObjeto;
    }
}
