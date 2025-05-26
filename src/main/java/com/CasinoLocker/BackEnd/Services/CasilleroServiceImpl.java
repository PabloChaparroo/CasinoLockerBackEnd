package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Casillero;
import com.CasinoLocker.BackEnd.Entitys.EstadoCasilleroPercha;
import com.CasinoLocker.BackEnd.Repositories.BaseRepository;
import com.CasinoLocker.BackEnd.Repositories.CasilleroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CasilleroServiceImpl extends BaseServiceImpl<Casillero,Long> implements CasilleroService {

    @Autowired
    CasilleroRepository repository;

    public CasilleroServiceImpl(BaseRepository<Casillero, Long> baseRespository, CasilleroRepository repository) {
        super(baseRespository);
        this.repository = repository;
    }


    // Método específico para obtener solo los casilleros no dados de baja
    @Override
    public List<Casillero> getCasillerosNoBaja() {
        return repository.findByFechaBajaCasilleroIsNull();
    }


    @Override
    @Transactional
    public Casillero createCasillero(Casillero casillero) throws Exception {
    // Verifica si ya existe un casillero con el mismo número
    if (repository.findByNumeroCasillero(casillero.getNumeroCasillero()).isPresent()) {
        throw new Exception("El número de casillero ya existe. Debe ingresar un número diferente.");
    }
    casillero.setId(null);
    casillero.setFechaAltaCasillero(LocalDate.now());
    casillero.setFechaModificacionCasillero(null);
    casillero.setFechaBajaCasillero(null);
    return repository.save(casillero);
}

    @Override
    @Transactional
    public Casillero updateCasillero(Long id, Casillero casillero) throws Exception {
        Casillero casilleroExistente = findById(id);  // usa BaseServiceImpl
        casilleroExistente.setNumeroCasillero(casillero.getNumeroCasillero());
        casilleroExistente.setFechaModificacionCasillero(LocalDate.now());
        return repository.save(casilleroExistente);
    }

    @Override
    @Transactional
    public Casillero darDeBajaCasillero(Long id) throws Exception {
        Casillero casilleroExistente = findById(id);
        if (casilleroExistente.getFechaBajaCasillero() != null) {
            throw new Exception("Casillero ya está dado de baja");
        }
        casilleroExistente.setFechaBajaCasillero(LocalDate.now());
        return repository.save(casilleroExistente);
    }

    @Override
    @Transactional
    public Casillero restaurarCasillero(Long id) throws Exception {
        Casillero casilleroExistente = findById(id);
        if (casilleroExistente.getFechaBajaCasillero() == null) {
            throw new Exception("Casillero no está dado de baja");
        }
        casilleroExistente.setFechaBajaCasillero(null);
        return repository.save(casilleroExistente);
    }

    @Override
    @Transactional
    public void deleteCasillero(Long id) throws Exception {
        Casillero casilleroExistente = findById(id);
        repository.delete(casilleroExistente);
    }
}
