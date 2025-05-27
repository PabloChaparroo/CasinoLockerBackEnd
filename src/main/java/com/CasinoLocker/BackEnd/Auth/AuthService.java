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
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    Perfil user = perfilRepository.findByUsername(request.getUsername()).orElseThrow();
    String token = jwtService.getToken(user);

    return AuthResponse.builder()
            .token(token)
            .nombreUsuario(user.getUsuario().getNombreUsuario())
            .username(user.getUsername())
            .dniUsuario(user.getUsuario().getDniUsuario())
            .emailUsuario(user.getUsuario().getEmailUsuario())
            .telefonoUsuario(user.getUsuario().getTelefonoUsuario())
            .build();
}

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
                .role(Role.EMPLEADO)
                .build();

        user.setUsuario(usuario);
        perfilRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .nombreUsuario(usuario.getNombreUsuario())
                .username(user.getUsername())
                .dniUsuario(user.getUsuario().getDniUsuario())
                .emailUsuario(user.getUsuario().getEmailUsuario())
                .telefonoUsuario(user.getUsuario().getTelefonoUsuario())
                .build();

    }

    public AuthResponse registerEmployee(RegisterEmployeeRequest request) {


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
                .password(passwordEncoder.encode(request.provisionalPassword))
                .fechaAltaPerfil(LocalDate.now())
                .role(Role.EMPLEADO) //ver numeracion de roles
                .build();

        user.setUsuario(usuario);
        perfilRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .nombreUsuario(usuario.getNombreUsuario())
                .username(user.getUsername())
                .dniUsuario(user.getUsuario().getDniUsuario())
                .emailUsuario(user.getUsuario().getEmailUsuario())
                .telefonoUsuario(user.getUsuario().getTelefonoUsuario())
                .build();

    }

}