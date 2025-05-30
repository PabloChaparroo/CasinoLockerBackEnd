package com.CasinoLocker.BackEnd;

import com.CasinoLocker.BackEnd.Entitys.Perfil;
import com.CasinoLocker.BackEnd.Entitys.Usuario;
import com.CasinoLocker.BackEnd.Enum.EstadoUsuario;
import com.CasinoLocker.BackEnd.Enum.Role;
import com.CasinoLocker.BackEnd.Repositories.PerfilRepository;
import com.CasinoLocker.BackEnd.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
        System.out.println("--------------------ESTÁ FUNCIONANDO CORRECTAMENTE------------------------");
    }

    @Bean
public CommandLineRunner initAdminUser(PerfilRepository perfilRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
    return args -> {
        String username = "admi@gmail.com";
        if (perfilRepository.findByUsername(username).isEmpty()) {
            Usuario usuario = Usuario.builder()
                    .nombreUsuario("Administrador")
                    .emailUsuario(username)
                    .dniUsuario(12345678)
                    .descripcionUsuario("Usuario administrador del sistema")
                    .telefonoUsuario(123456789)
                    .fechaAltaUsuario(LocalDate.now())
                    .estadoUsuario(EstadoUsuario.USUARIO_HABILITADO)
                    .build();

            Perfil perfil = Perfil.builder()
                    .username(username)
                    .password(passwordEncoder.encode("root"))
                    .fechaAltaPerfil(LocalDate.now())
                    .role(Role.ADMIN)
                    .usuario(usuario)
                    .build();

            perfilRepository.save(perfil); // SOLO guardar el perfil

            System.out.println("Usuario admin creado: " + username + " / root");
        }
    };
}
}
