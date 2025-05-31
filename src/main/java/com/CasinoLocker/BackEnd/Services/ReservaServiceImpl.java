package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.DTO.ReservaDTO;
import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import com.CasinoLocker.BackEnd.Entitys.Objeto;
import com.CasinoLocker.BackEnd.Entitys.Reserva;
import com.CasinoLocker.BackEnd.Enum.EstadoReserva;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.CasilleroRepository;
import com.CasinoLocker.BackEnd.Repositories.EstadoCasilleroPerchaRepository;
import com.CasinoLocker.BackEnd.Repositories.ObjetoRepository;
import com.CasinoLocker.BackEnd.Repositories.ReservaRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl extends BaseServiceImpl<Reserva, Long> implements ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private CasilleroRepository casilleroRepository;
    

   public ReservaServiceImpl(BaseRepository<Reserva, Long> baseRepository,
                              ReservaRepository reservaRepository,
                              CasilleroRepository casilleroRepository) {
        super(baseRepository);
        this.reservaRepository = reservaRepository;
        this.casilleroRepository = casilleroRepository;
    }

@Autowired
private EstadoCasilleroPerchaRepository estadoCasilleroPerchaRepository;

@Override
@Transactional
public Reserva createReserva(Reserva reserva) throws Exception {
    Integer maxNumero = reservaRepository.findMaxNumeroReserva();
    int nuevoNumero = (maxNumero == null || maxNumero < 1000) ? 1000 : maxNumero + 1;
    reserva.setNumeroReserva(nuevoNumero);

    reserva.setFechaAltaReserva(LocalDateTime.now());
    reserva.setFechaModificacionReserva(null);
    reserva.setFechaBajaReserva(null);

    // Buscar el estado "Ocupado" y asignarlo al casillero
    EstadoCasilleroPercha ocupado = estadoCasilleroPerchaRepository
        .findByNombreEstadoCasilleroPercha("Ocupado")
        .orElseThrow(() -> new Exception("Estado 'Ocupado' no encontrado"));
    Casillero casillero = reserva.getCasillero();
    casillero.setEstadoCasilleroPercha(ocupado);
    casilleroRepository.save(casillero);

    reserva.setEstadoReserva(EstadoReserva.Reservado);

    return reservaRepository.save(reserva);
}



    public Reserva findReservaReservadaByIdCasillero(Long idCasillero) {
    return reservaRepository.findReservadaByCasilleroId(idCasillero)
            .orElseThrow(() -> new ResourceNotFoundException(
                "No hay reserva activa para el casillero con ID: " + idCasillero));
    
}


    @Override
    @Transactional
    public Reserva finalizarReserva(Long idReserva) throws Exception {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + idReserva));

        // Cambiar el estado de la reserva a Finalizado
        reserva.setEstadoReserva(EstadoReserva.Finalizado);
        reserva.setFechaFinalizacionReserva(LocalDateTime.now());

        // Cambiar el estado del casillero a Disponible
        EstadoCasilleroPercha disponible = estadoCasilleroPerchaRepository
                .findByNombreEstadoCasilleroPercha("Disponible")
                .orElseThrow(() -> new Exception("Estado 'Disponible' no encontrado"));

        Casillero casillero = reserva.getCasillero();
        casillero.setEstadoCasilleroPercha(disponible);
        casilleroRepository.save(casillero);

        return reservaRepository.save(reserva);
    }
    @Override
    public List<ReservaDTO> obtenerReservasActivasDTO() {
        List<Reserva> reservas = reservaRepository.findByEstadoReserva(EstadoReserva.Reservado);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

        return reservas.stream().map(reserva -> {
            String fechaFormateada = reserva.getFechaAltaReserva().format(formatter);
            int cantidadObjetos = reserva.getObjetoList() != null ? reserva.getObjetoList().size() : 0;

            String ubicacion = "";
            if (reserva.getCasillero() != null) {
                ubicacion = reserva.getCasillero().getNumeroCasillero() + " - Casillero";
            } else if (reserva.getPercha() != null) {
                ubicacion = reserva.getPercha().getNumeroPercha() + " - Percha";
            }

            String cliente = reserva.getCliente() != null ? reserva.getCliente().getNombreCliente() : "Desconocido";

            return new ReservaDTO(
                    reserva.getId(),
                    reserva.getNumeroReserva(),
                    fechaFormateada,
                    cantidadObjetos,
                    ubicacion,
                    cliente
            );
        }).toList();
    }

}
