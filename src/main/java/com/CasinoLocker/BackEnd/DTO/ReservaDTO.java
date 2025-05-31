package com.CasinoLocker.BackEnd.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservaDTO {
    private Long id;
    private int numeroReserva;
    private String fechaAltaReserva;
    private int cantidadObjetos;
    private String ubicacion; // Ej: "12 - Casillero" o "8 - Percha"
    private String cliente;   // Ej: "Juan Pérez"
}