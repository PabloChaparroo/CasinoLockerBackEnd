package com.CasinoLocker.BackEnd.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    
    
    String username;

    Long id;
    String nombreUsuario;
    String apellidoUsuario;
    int telefonoUsuario;
    int dniUsuario;
    String emailUsuario;
    String descripcionUsuario;
    LocalDate fechaHoraModificacionUsuario;
}
