package com.CasinoLocker.BackEnd.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmployeeRequest {

    int id;
    String username;
    String password;
    int idRole;

    int dniEmpleado;
    String descripcionEmpleado;
    String nombreEmpleado;
    String apellidoEmpleado;
    int telefonoEmpleado;
    String mailEmpleado;

}
