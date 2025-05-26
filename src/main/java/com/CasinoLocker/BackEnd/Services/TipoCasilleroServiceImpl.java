package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.TipoCasillero;
import com.CasinoLocker.BackEnd.Repositories.TipoCasilleroRepository;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TipoCasilleroServiceImpl extends BaseServiceImpl<TipoCasillero, Long> implements TipoCasilleroService {

    private final TipoCasilleroRepository tipoCasilleroRepository;

    public TipoCasilleroServiceImpl(BaseRepository<TipoCasillero, Long> baseRepository, TipoCasilleroRepository tipoCasilleroRepository) {
        super(baseRepository);
        this.tipoCasilleroRepository = tipoCasilleroRepository;
    }

    @Override
    public TipoCasillero update(Long id, TipoCasillero tipoCasillero) throws Exception {
        // Buscar la entidad existente
        TipoCasillero existente = tipoCasilleroRepository.findById(id)
                .orElseThrow(() -> new Exception("TipoCasillero no encontrado"));

        // Actualizar solo los campos editables (nombre)
        existente.setNombreTipoCasillero(tipoCasillero.getNombreTipoCasillero());

        // Actualizar la fecha de modificación
        existente.setFechaModificacionTipoCasillero(LocalDate.now());

        // Guardar y devolver
        return tipoCasilleroRepository.save(existente);
    }

    @Override
    public TipoCasillero save(TipoCasillero tipoCasillero) throws Exception {
        // Asegurar que no venga fecha desde frontend y setear la fecha de alta
        tipoCasillero.setFechaAltaTipoCasillero(LocalDate.now());
        tipoCasillero.setFechaModificacionTipoCasillero(null);
        tipoCasillero.setFechaBajaTipoCasillero(null);
        return super.save(tipoCasillero);
    }
    public TipoCasillero darDeBaja(Long id) throws Exception {
        TipoCasillero tipo = tipoCasilleroRepository.findById(id)
                .orElseThrow(() -> new Exception("TipoCasillero no encontrado"));
        tipo.setFechaBajaTipoCasillero(LocalDate.now());
        return tipoCasilleroRepository.save(tipo);
    }

    public TipoCasillero restaurar(Long id) throws Exception {
        TipoCasillero tipo = tipoCasilleroRepository.findById(id)
                .orElseThrow(() -> new Exception("TipoCasillero no encontrado"));
        tipo.setFechaBajaTipoCasillero(null);
        return tipoCasilleroRepository.save(tipo);
    }
}
