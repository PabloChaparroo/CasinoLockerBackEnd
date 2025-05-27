package com.CasinoLocker.BackEnd.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    String token;
    String nombreUsuario;
    String username;
    String emailUsuario;
    int dniUsuario;
    int telefonoUsuario;

}