package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import com.CasinoLocker.BackEnd.Entitys.Percha;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.EstadoCasilleroPerchaRepository;
import com.CasinoLocker.BackEnd.Repositories.PerchaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerchaServiceImpl implements PerchaService {

    private final PerchaRepository perchaRepository;
    private final EstadoCasilleroPerchaRepository estadoRepo;

    @Override
    public List<Percha> getAll() {
        return perchaRepository.findAll();
    }

    @Override
    public Percha getById(Long id) {
        return perchaRepository.findById(id).orElseThrow();
    }

    @Override
    public Percha create(Percha percha) {
        percha.setFechaAltaPercha(LocalDate.now());
        percha.setFechaModificacionPercha(null);
        percha.setFechaBajaPercha(null);
        return perchaRepository.save(percha);
    }

    @Override
    public Percha update(Long id, Percha percha) {
        Percha existente = getById(id);
        existente.setNumeroPercha(percha.getNumeroPercha());
        existente.setEstadoCasilleroPercha(percha.getEstadoCasilleroPercha());
        existente.setFechaModificacionPercha(LocalDate.now());
        return perchaRepository.save(existente);
    }

    @Override
    public Percha darDeBaja(Long id) {
        Percha percha = getById(id);
        EstadoCasilleroPercha estadoBaja = estadoRepo
                .findByNombreEstadoCasilleroPercha("Dado_de_baja")
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        percha.setFechaBajaPercha(LocalDate.now());
        percha.setFechaModificacionPercha(LocalDate.now());
        percha.setEstadoCasilleroPercha(estadoBaja);
        return perchaRepository.save(percha);
    }

    @Override
    public Percha darDeAlta(Long id) {
        Percha percha = getById(id);
        EstadoCasilleroPercha estadoDisponible = estadoRepo
                .findByNombreEstadoCasilleroPercha("Disponible")
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        percha.setFechaBajaPercha(null);
        percha.setFechaModificacionPercha(LocalDate.now());
        percha.setEstadoCasilleroPercha(estadoDisponible);
        return perchaRepository.save(percha);
    }
}
