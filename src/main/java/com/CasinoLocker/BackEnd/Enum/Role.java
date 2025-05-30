package com.CasinoLocker.BackEnd.Enum;

public enum Role {
    ADMIN(1),
    EMPLEADO(2);

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