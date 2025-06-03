package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.DTO.ConfObjetoPerdidoDetalleDTO;
import com.CasinoLocker.BackEnd.Entitys.*;
import com.CasinoLocker.BackEnd.Enum.EstadoReserva;
import com.CasinoLocker.BackEnd.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfObjetoPerdidoServiceImpl extends BaseServiceImpl<ConfObjetoPerdido,Long> implements ConfObjetoPerdidoService {

    @Autowired
    private ConfObjetoPerdidoRepository confObjetoPerdidoRepository;
    @Autowired
    private ReservaRepository reservaRepo;
    @Autowired
    private CasilleroRepository casilleroRepo;
    @Autowired
    private EstadoCasilleroPerchaRepository estadoCasilleroPerchaRepository;
    @Autowired
    private PerchaRepository perchaRepo;

    public ConfObjetoPerdidoServiceImpl(BaseRepository<ConfObjetoPerdido, Long> baseRespository, ConfObjetoPerdidoRepository confObjetoPerdidoRepository) {
        super(baseRespository);
        this.confObjetoPerdidoRepository = confObjetoPerdidoRepository;
    }
    @Override
    @Transactional
    public ConfObjetoPerdido crearConfObjetoPerdido(Long idReserva, Long idCasillero) {
        Reserva reserva = reservaRepo.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        Casillero casilleroDestino = casilleroRepo.findById(idCasillero)
                .orElseThrow(() -> new RuntimeException("Casillero no encontrado"));

        // Estados necesarios
        EstadoCasilleroPercha disponible = estadoCasilleroPerchaRepository.findByNombreEstadoCasilleroPercha("Disponible")
                .orElseThrow(() -> new RuntimeException("Estado 'Disponible' no encontrado"));

        EstadoCasilleroPercha ocupado = estadoCasilleroPerchaRepository.findByNombreEstadoCasilleroPercha("Ocupado")
                .orElseThrow(() -> new RuntimeException("Estado 'Ocupado' no encontrado"));

        // Detectar si la reserva pertenece a un casillero o a una percha
        Casillero casilleroOrigen = reserva.getCasillero();
        Percha perchaOrigen = reserva.getPercha();

        // Buscar configuración existente
        ConfObjetoPerdido confExistente = confObjetoPerdidoRepository
                .findByCasilleroId(idCasillero)
                .orElse(null);

        // Lógica común: cambiar estado de reserva y de casillero/percha de origen
        reserva.setEstadoReserva(EstadoReserva.Objetos_Perdidos);

        if (casilleroOrigen != null) {
            casilleroOrigen.setEstadoCasilleroPercha(disponible);
            casilleroRepo.save(casilleroOrigen);
        }

        if (perchaOrigen != null) {
            perchaOrigen.setEstadoCasilleroPercha(disponible);
            perchaRepo.save(perchaOrigen);
        }

        if (confExistente != null) {
            // Solo agregar la reserva si no existe ya una reserva con estado Objetos_Perdidos en la lista
            boolean yaTieneReservaActiva = confExistente.getReservas().stream()
                .anyMatch(r -> r.getEstadoReserva() == EstadoReserva.Objetos_Perdidos);
            confExistente.getReservas().add(reserva);
            reservaRepo.save(reserva);
            // Si no hay reservas activas, poner el casillero en Ocupado
            if (!yaTieneReservaActiva) {
                casilleroDestino.setEstadoCasilleroPercha(ocupado);
                casilleroRepo.save(casilleroDestino);
            }
            return confObjetoPerdidoRepository.save(confExistente);
        }

        // Si no hay configuración existente, crear una nueva
        ConfObjetoPerdido nuevaConf = new ConfObjetoPerdido();
        nuevaConf.setFechaAltaConfObjetoPerdido(LocalDateTime.now());
        nuevaConf.setCasillero(casilleroDestino);
        nuevaConf.getReservas().add(reserva);

        // Casillero de destino a Ocupado
        casilleroDestino.setEstadoCasilleroPercha(ocupado);
        casilleroRepo.save(casilleroDestino);

        reservaRepo.save(reserva);
        return confObjetoPerdidoRepository.save(nuevaConf);
    }

    @Override
    public ConfObjetoPerdidoDetalleDTO obtenerDetallePorIdCasillero(Long idCasillero) {
        ConfObjetoPerdido conf = confObjetoPerdidoRepository.findByCasilleroId(idCasillero)
                .orElseThrow(() -> new RuntimeException("No se encontró configuración para el casillero"));

        java.util.List<ConfObjetoPerdidoDetalleDTO.ReservaDetalleDTO> reservasDTO = new java.util.ArrayList<>();
        for (Reserva reserva : conf.getReservas()) {
            if (reserva.getEstadoReserva() != EstadoReserva.Objetos_Perdidos) continue;
            String clienteNombre = reserva.getCliente() != null ? reserva.getCliente().getNombreCliente() : "";
            String ubicacion;
            if (reserva.getCasillero() != null) {
                ubicacion = "Casillero N° " + reserva.getCasillero().getNumeroCasillero();
            } else if (reserva.getPercha() != null) {
                ubicacion = "Percha N° " + reserva.getPercha().getNumeroPercha();
            } else {
                ubicacion = "Sin ubicación";
            }
            String fechaHoraReserva = reserva.getFechaAltaReserva() != null ?
                    reserva.getFechaAltaReserva().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")) : "";
            java.util.List<ConfObjetoPerdidoDetalleDTO.ObjetoDTO> objetos = new java.util.ArrayList<>();
            if (reserva.getObjetoList() != null) {
                for (var obj : reserva.getObjetoList()) {
                    objetos.add(ConfObjetoPerdidoDetalleDTO.ObjetoDTO.builder()
                            .numeroObjeto(obj.getNumeroObjeto())
                            .descripcionObjeto(obj.getDescripcionObjeto())
                            .build());
                }
            }
            reservasDTO.add(ConfObjetoPerdidoDetalleDTO.ReservaDetalleDTO.builder()
                    .idReserva(reserva.getId())
                    .clienteNombre(clienteNombre)
                    .ubicacion(ubicacion)
                    .fechaHoraReserva(fechaHoraReserva)
                    .objetos(objetos)
                    .build());
        }
        return ConfObjetoPerdidoDetalleDTO.builder()
                .reservas(reservasDTO)
                .build();
    }

    @Override
    public void despacharReserva(Long idReserva) {
        Reserva reserva = reservaRepo.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // Buscar la configuración de objeto perdido que contiene esta reserva
        ConfObjetoPerdido conf = confObjetoPerdidoRepository.findAll().stream()
                .filter(c -> c.getReservas().stream().anyMatch(r -> r.getId().equals(idReserva)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConfObjetoPerdido no encontrada para la reserva"));

        // Cambiar estado y fecha de la reserva
        reserva.setEstadoReserva(EstadoReserva.Finalizado);
        reserva.setFechaFinalizacionReserva(java.time.LocalDateTime.now());
        reservaRepo.save(reserva);

        // Cambiar fecha retirada en la conf
        conf.setFechaBajaConfObjetoPerdido(java.time.LocalDateTime.now());

        // Si es la última reserva con estado Objetos_Perdidos, cambiar estado del casillero a Disponible
        boolean ultima = conf.getReservas().stream()
                .filter(r -> r.getEstadoReserva() == EstadoReserva.Objetos_Perdidos)
                .count() == 0;
        if (ultima && conf.getCasillero() != null) {
            EstadoCasilleroPercha disponible = estadoCasilleroPerchaRepository.findByNombreEstadoCasilleroPercha("Disponible")
                    .orElseThrow(() -> new RuntimeException("Estado 'Disponible' no encontrado"));
            conf.getCasillero().setEstadoCasilleroPercha(disponible);
            casilleroRepo.save(conf.getCasillero());
        }
        confObjetoPerdidoRepository.save(conf);
    }

}
