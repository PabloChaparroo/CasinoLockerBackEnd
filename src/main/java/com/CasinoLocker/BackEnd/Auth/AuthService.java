package com.CasinoLocker.BackEnd.Auth;

import com.CasinoLocker.BackEnd.Auth.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CasinoLocker.BackEnd.Entitys.Perfil;
import com.CasinoLocker.BackEnd.Entitys.Usuario;
import com.CasinoLocker.BackEnd.Enum.EstadoUsuario;
import com.CasinoLocker.BackEnd.Enum.Role;
import com.CasinoLocker.BackEnd.Jwt.JwtService;
import com.CasinoLocker.BackEnd.Repositories.PerfilRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PerfilRepository perfilRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    public AuthResponse login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );
    Perfil user = perfilRepository.findByUsername(request.getUsername()).orElseThrow();

    // Validar estado del usuario
    if (user.getUsuario().getEstadoUsuario() != EstadoUsuario.USUARIO_HABILITADO) {
        throw new RuntimeException("El usuario no está habilitado para iniciar sesión.");
    }

    String token = jwtService.getToken(user);

    return AuthResponse.builder()
            .token(token)
            .id(user.getUsuario().getId())
            .nombreUsuario(user.getUsuario().getNombreUsuario())
            .username(user.getUsername())
            .dniUsuario(user.getUsuario().getDniUsuario())
            .emailUsuario(user.getUsuario().getEmailUsuario())
            .telefonoUsuario(user.getUsuario().getTelefonoUsuario())
            .build();
}

//Usamos este registro en el caso de tener usuarios, NO ES NUESTRO CASO ya que solo tenemos empleados
//Hay que cambiar el Role a cliente o usuarios, en este caso está como ADMIN y no es correcto pero nosotros no lo usamos
    public AuthResponse register(RegisterRequest request) {

        Usuario usuario = Usuario.builder()
                
                .nombreUsuario(request.getNombreUsuario())
                .emailUsuario(request.getEmailUsuario())
                .dniUsuario(request.getDniUsuario())
                .descripcionUsuario(request.getDescripcionUsuario())
                .telefonoUsuario(request.getTelefonoUsuario())
                .fechaAltaUsuario(LocalDate.now())
                .estadoUsuario(EstadoUsuario.USUARIO_HABILITADO)
                .build();

        Perfil user = Perfil.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fechaAltaPerfil(LocalDate.now())
                .role(Role.ADMIN) 
                .build();

        user.setUsuario(usuario);
        perfilRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .id(user.getUsuario().getId())
                .nombreUsuario(usuario.getNombreUsuario())
                .username(user.getUsername())
                .dniUsuario(user.getUsuario().getDniUsuario())
                .emailUsuario(user.getUsuario().getEmailUsuario())
                .telefonoUsuario(user.getUsuario().getTelefonoUsuario())
                .build();

    }

    public AuthResponse registerEmployee(RegisterEmployeeRequest request) {
        // Validar que el rol exista
        Role role = Role.fromValorNumerico(request.getIdRole());
        if (role == null) {
            throw new IllegalArgumentException("Rol inválido: " + request.getIdRole());
        }


        Usuario usuario = Usuario.builder()
                .nombreUsuario(request.getNombreEmpleado())                
                .emailUsuario(request.getMailEmpleado())
                .dniUsuario(request.getDniEmpleado())
                .descripcionUsuario(request.getDescripcionEmpleado())
                .telefonoUsuario(request.getTelefonoEmpleado())
                .fechaAltaUsuario(LocalDate.now())
                .estadoUsuario(EstadoUsuario.USUARIO_HABILITADO)
                .build();

        Perfil user = Perfil.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.password))
                .fechaAltaPerfil(LocalDate.now())
                .role(role) //ver numeracion de roles
                .build();

        user.setUsuario(usuario);
        perfilRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .id(usuario.getId())
                .nombreUsuario(usuario.getNombreUsuario())
                .username(user.getUsername())
                .dniUsuario(user.getUsuario().getDniUsuario())
                .emailUsuario(user.getUsuario().getEmailUsuario())
                .telefonoUsuario(user.getUsuario().getTelefonoUsuario())
                .role(user.getRole())
                .build();

    }

}