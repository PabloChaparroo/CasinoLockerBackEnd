package com.CasinoLocker.BackEnd.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    int id;
    String username;
    String password;

    int dniUsuario;
    String descripcionUsuario;
    String nombreUsuario;
    String apellidoUsuario;
    int telefonoUsuario;
    String emailUsuario;

}