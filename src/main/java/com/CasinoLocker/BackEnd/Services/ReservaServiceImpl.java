package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.DTO.ReservaClienteReporteDTO;
import com.CasinoLocker.BackEnd.DTO.ReservaDTO;
import com.CasinoLocker.BackEnd.DTO.ReservaReporteDTO;
import com.CasinoLocker.BackEnd.Entitys.*;
import com.CasinoLocker.BackEnd.Enum.EstadoReserva;
import com.CasinoLocker.BackEnd.Repositories.*;

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
    @Autowired
    private PerchaRepository perchaRepository;
    @Autowired
    private ObjetoRepository objetoRepository;
    

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

        EstadoCasilleroPercha ocupado = estadoCasilleroPerchaRepository
                .findByNombreEstadoCasilleroPercha("Ocupado")
                .orElseThrow(() -> new Exception("Estado 'Ocupado' no encontrado"));

        if (reserva.getCasillero() != null) {
            Casillero casillero = reserva.getCasillero();
            casillero.setEstadoCasilleroPercha(ocupado);
            casilleroRepository.save(casillero);
        } else if (reserva.getPercha() != null) {
            if (reserva.getObjetoList() == null || reserva.getObjetoList().size() != 1) {
                throw new Exception("La percha debe tener exactamente un objeto");
            }

            // Obtener la percha completa de la base de datos
            Percha percha = perchaRepository.findById(reserva.getPercha().getId())
                    .orElseThrow(() -> new Exception("Percha no encontrada"));

            // Actualizar solo el estado
            percha.setEstadoCasilleroPercha(ocupado);
            perchaRepository.save(percha);

            // Asignar la percha completa a la reserva
            reserva.setPercha(percha);
        } else {
            throw new Exception("Debe asignarse un casillero o una percha a la reserva");
        }

        reserva.setEstadoReserva(EstadoReserva.Reservado);
        return reservaRepository.save(reserva);
    }



    public Reserva findReservaReservadaByIdCasillero(Long idCasillero) {
    return reservaRepository.findReservadaByCasilleroId(idCasillero)
            .orElseThrow(() -> new ResourceNotFoundException(
                "No hay reserva activa para el casillero con ID: " + idCasillero));
    
}
    @Override
    public Reserva findReservaReservadaByIdPercha(Long idPercha) {
        return reservaRepository.findReservadaByPerchaId(idPercha)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No hay reserva activa para la percha con ID: " + idPercha));
    }


    @Override
    @Transactional
    public Reserva finalizarReserva(Long idReserva) throws Exception {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + idReserva));

        // Cambiar el estado de la reserva a Finalizado
        reserva.setEstadoReserva(EstadoReserva.Finalizado);
        reserva.setFechaFinalizacionReserva(LocalDateTime.now());

        // Obtener el estado "Disponible"
        EstadoCasilleroPercha disponible = estadoCasilleroPerchaRepository
                .findByNombreEstadoCasilleroPercha("Disponible")
                .orElseThrow(() -> new Exception("Estado 'Disponible' no encontrado"));

        // Liberar casillero o percha según corresponda
        if (reserva.getCasillero() != null) {
            Casillero casillero = reserva.getCasillero();
            casillero.setEstadoCasilleroPercha(disponible);
            casilleroRepository.save(casillero);
        } else if (reserva.getPercha() != null) {
            Percha percha = reserva.getPercha();
            percha.setEstadoCasilleroPercha(disponible);
            perchaRepository.save(percha);
        }

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
    @Override
    public List<ReservaReporteDTO> buscarReservasEntreFechas(LocalDateTime desde, LocalDateTime hasta) {
        List<Reserva> reservas = reservaRepository.findByFechaAltaReservaBetween(desde, hasta);
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        return reservas.stream().map(r -> ReservaReporteDTO.builder()
                .numeroReserva(r.getNumeroReserva())
                .cliente(r.getCliente() != null ? r.getCliente().getNombreCliente() : "Sin cliente")
                .estado(r.getEstadoReserva() != null ? r.getEstadoReserva().name() : "")
                .ubicacion(
                    r.getCasillero() != null
                        ? r.getCasillero().getNumeroCasillero() + " Casillero"
                        : (r.getPercha() != null
                            ? r.getPercha().getNumeroPercha() + " Percha"
                            : "Sin ubicación")
                )
                .fechaHoraAlta(r.getFechaAltaReserva() != null ? r.getFechaAltaReserva().format(formatter) : "")
                .fechaHoraFinalizacion(r.getFechaFinalizacionReserva() != null ? r.getFechaFinalizacionReserva().format(formatter) : "")
                .build()
        ).toList();
    }
    @Override
    public List<ReservaClienteReporteDTO> buscarReservasPorClienteYFechas(Long idCliente, LocalDateTime desde, LocalDateTime hasta) {
        List<Reserva> reservas = reservaRepository.findByClienteIdAndFechaAltaReservaBetween(idCliente, desde, hasta);
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        return reservas.stream().map(reserva -> {
            String ubicacion = null;
            if (reserva.getCasillero() != null) {
                ubicacion = reserva.getCasillero().getNumeroCasillero() + " - casillero";
            } else if (reserva.getPercha() != null) {
                ubicacion = reserva.getPercha().getNumeroPercha() + " - percha";
            }
            List<ReservaClienteReporteDTO.ObjetoDTO> objetos = reserva.getObjetoList().stream()
                .map(obj -> ReservaClienteReporteDTO.ObjetoDTO.builder()
                    .numeroObjeto(obj.getNumeroObjeto())
                    .descripcionObjeto(obj.getDescripcionObjeto())
                    .build())
                .toList();
            return ReservaClienteReporteDTO.builder()
                .nombreCliente(reserva.getCliente().getNombreCliente())
                .ubicacion(ubicacion)
                .fechaHoraInicio(reserva.getFechaAltaReserva() != null ? reserva.getFechaAltaReserva().format(formatter) : "")
                .fechaHoraFinalizacion(reserva.getFechaFinalizacionReserva() != null ? reserva.getFechaFinalizacionReserva().format(formatter) : "")
                .objetos(objetos)
                .estadoReserva(reserva.getEstadoReserva().name())
                .build();
        }).toList();
    }
    @Override
    @Transactional
    public List<Reserva> saveAll(List<Reserva> reservas) throws Exception {
        for (Reserva reserva : reservas) {
            if (reserva.getObjetoList() != null) {
                for (int i = 0; i < reserva.getObjetoList().size(); i++) {
                    Objeto obj = reserva.getObjetoList().get(i);
                    if (obj.getId() == null) {
                        Objeto saved = objetoRepository.save(obj);
                        reserva.getObjetoList().set(i, saved);
                    }
                }
            }
        }
        return super.saveAll(reservas);
    }

}
