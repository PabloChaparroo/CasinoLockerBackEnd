package com.CasinoLocker.BackEnd.Auth;

import com.CasinoLocker.BackEnd.Enum.Role;

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
    Long id;
    String nombreUsuario;
    String username;
    String emailUsuario;
    int dniUsuario;
    int telefonoUsuario;
    Role role;

}