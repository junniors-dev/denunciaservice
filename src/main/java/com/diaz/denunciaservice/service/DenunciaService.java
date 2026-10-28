package com.diaz.denunciaservice.service;

import com.diaz.denunciaservice.entity.Denuncia;
import com.diaz.denunciaservice.repository.DenunciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DenunciaService {

    private final DenunciaRepository repository;

    public DenunciaService(DenunciaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Denuncia registrar(Denuncia denuncia) {
        // el id lo genera la BD y el estado siempre arranca en 1
        denuncia.setId(null);
        denuncia.setEstado(1);
        return repository.save(denuncia);
    }

    @Transactional(readOnly = true)
    public List<Denuncia> listar(int offset, int limit) {
        if (offset < 0 || limit < 1 || limit > 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "offset >= 0 y limit entre 1 y 50");
        }
        return repository.listarPaginado(offset, limit);
    }

    @Transactional(readOnly = true)
    public Denuncia obtener(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la denuncia " + id));
    }

    @Transactional(readOnly = true)
    public List<Denuncia> listarPorDni(String dni) {
        return repository.findByDniOrderByIdDesc(dni);
    }

    @Transactional
    public Denuncia atender(Integer id) {
        Denuncia denuncia = obtener(id);
        denuncia.setEstado(0);
        return repository.save(denuncia);
    }
}
