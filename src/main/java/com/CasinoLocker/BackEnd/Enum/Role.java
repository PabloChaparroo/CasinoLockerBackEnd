package com.CasinoLocker.BackEnd.Enum;

public enum Role {
    ADMIN(1),
    EMPLEADO_CASILLERO_PERCHA(2),
    EMPLEADO_OBJETO_PERDIDOS(3),
    EMPLEADO_REPORTE(4);


    
    private final int valorNumerico;

    Role(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public int getValorNumerico() {
        return valorNumerico;
    }

    public static Role fromValorNumerico(int valor) {
        for (Role role : values()) {
            if (role.valorNumerico == valor) {
                return role;
            }
        }
        return null;
    }
}