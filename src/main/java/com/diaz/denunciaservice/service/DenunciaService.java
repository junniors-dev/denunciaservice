package com.diaz.denunciaservice.service;

import com.diaz.denunciaservice.entity.Denuncia;
import com.diaz.denunciaservice.repository.DenunciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class DenunciaService {

    private final DenunciaRepository repository;

    public DenunciaService(DenunciaRepository repository) {
        this.repository = repository;
    }

    public Denuncia registrar(Denuncia denuncia) {
        denuncia.setId(null);
        denuncia.setEstado(1);
        if (denuncia.getFecha() == null) {
            // hora de Perú aunque el servidor de Render esté en UTC
            denuncia.setFecha(LocalDateTime.now(ZoneId.of("America/Lima")));
        }
        return repository.save(denuncia);
    }

    public List<Denuncia> listar(int offset, int limit) {
        return repository.listar(offset, limit);
    }

    public Denuncia obtener(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Denuncia> listarPorDni(String dni) {
        return repository.findByDni(dni);
    }

    public Denuncia atender(Integer id) {
        Denuncia denuncia = obtener(id);
        denuncia.setEstado(0);
        return repository.save(denuncia);
    }
}
