package com.CasinoLocker.BackEnd.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrearConfObjetoPerdidoDTO {
    private Long idReserva;
    private Long idCasillero;
}
