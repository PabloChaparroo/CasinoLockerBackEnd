package com.CasinoLocker.BackEnd.Entitys;


import com.CasinoLocker.BackEnd.Enum.EstadoPerfil;
import com.CasinoLocker.BackEnd.Enum.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name ="perfil" , uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Perfil extends BaseEntity implements UserDetails {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fecha_alta_perfil")
    private LocalDate fechaAltaPerfil;

    @Column(name = "fecha_baja_perfil")
    private LocalDate fechaBajaPerfil;

    @Column(name = "fecha_modificacion_perfil")
    private LocalDate fechaModificacionPerfil;

    private EstadoPerfil estadoPerfil;

     @Column(name = "role")
    private Role role;

    //Relacion usuario
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    //relacion con TipoPerfil
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_tipoPerfil_id")
    private TipoPerfil tipoPerfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    

}
