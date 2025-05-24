package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import com.CasinoLocker.BackEnd.Repositories.EstadoCasilleroPerchaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoCasilleroPerchaServiceImpl
        extends BaseServiceImpl<EstadoCasilleroPercha, Long>
        implements EstadoCasilleroPerchaService {

    private final EstadoCasilleroPerchaRepository repository;

    @Autowired
    public EstadoCasilleroPerchaServiceImpl(EstadoCasilleroPerchaRepository repository) {
        super(repository);
        this.repository = repository;
    }

    // Método específico para obtener solo los estados no dados de baja
    @Override
    public List<EstadoCasilleroPercha> getEstados() {
        return repository.findByFechaBajaEstadoCasilleroPerchaIsNull();
    }


    @Override
    @Transactional
    public EstadoCasilleroPercha createEstado(EstadoCasilleroPercha estado) {
        estado.setId(null);
        estado.setFechaAltaEstadoCasilleroPercha(LocalDate.now());
        estado.setFechaModificacionEstadoCasilleroPercha(null);
        estado.setFechaBajaEstadoCasilleroPercha(null);
        return repository.save(estado);
    }

    @Override
    @Transactional
    public EstadoCasilleroPercha updateEstado(Long id, EstadoCasilleroPercha estado) throws Exception {
        EstadoCasilleroPercha estadoExistente = findById(id);  // usa BaseServiceImpl
        estadoExistente.setNombreEstadoCasilleroPercha(estado.getNombreEstadoCasilleroPercha());
        estadoExistente.setColorEstadoCasilleroPercha(estado.getColorEstadoCasilleroPercha());
        estadoExistente.setReservable(estado.getReservable());
        estadoExistente.setFechaModificacionEstadoCasilleroPercha(LocalDate.now());
        return repository.save(estadoExistente);
    }

    @Override
    @Transactional
    public EstadoCasilleroPercha darDeBaja(Long id) throws Exception {
        EstadoCasilleroPercha estadoExistente = findById(id);
        if (estadoExistente.getFechaBajaEstadoCasilleroPercha() != null) {
            throw new Exception("Estado ya está dado de baja");
        }
        estadoExistente.setFechaBajaEstadoCasilleroPercha(LocalDate.now());
        return repository.save(estadoExistente);
    }

    @Override
    @Transactional
    public EstadoCasilleroPercha restaurar(Long id) throws Exception {
        EstadoCasilleroPercha estadoExistente = findById(id);
        if (estadoExistente.getFechaBajaEstadoCasilleroPercha() == null) {
            throw new Exception("Estado no está dado de baja");
        }
        estadoExistente.setFechaBajaEstadoCasilleroPercha(null);
        return repository.save(estadoExistente);
    }

    @Override
    @Transactional
    public void deleteEstado(Long id) throws Exception {
        EstadoCasilleroPercha estadoExistente = findById(id);
        repository.delete(estadoExistente);
    }
}