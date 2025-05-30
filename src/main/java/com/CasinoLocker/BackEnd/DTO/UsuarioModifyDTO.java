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
public class UsuarioModifyDTO {
    Long idUsuario;
    int idRole;

    String nombreUsuario;
    String apellidoUsuario;
    int telefonoUsuario;
    int dniUsuario;
    String descripcionUsuario;
    String emailUsuario;
    LocalDate fechaHoraModificacionUsuario;
}
