package com.CasinoLocker.BackEnd.Services;

import com.CasinoLocker.BackEnd.Entitys.Percha;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PerchaService {
    List<Percha> getAll();
    Percha getById(Long id);
    Percha create(Percha percha);
    Percha update(Long id, Percha percha);
    Percha darDeBaja(Long id);
    Percha darDeAlta(Long id);
    List<Percha> findAllActivas();
    List<Percha> saveAll(List<Percha> perchas) throws Exception;
}
