package com.CasinoLocker.BackEnd.Services;

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
            confExistente.getReservas().add(reserva);
            reservaRepo.save(reserva);
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

}
