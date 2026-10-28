package com.diaz.denunciaservice.controller;

import com.diaz.denunciaservice.entity.Denuncia;
import com.diaz.denunciaservice.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    // "" y "/" porque Spring 6+ ya no iguala /denuncias con /denuncias/
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Denuncia registrar(@Valid @RequestBody Denuncia denuncia) {
        return service.registrar(denuncia);
    }

    @GetMapping({"", "/"})
    public List<Denuncia> listar(@RequestParam(defaultValue = "0") int offset,
                                 @RequestParam(defaultValue = "5") int limit) {
        return service.listar(offset, limit);
    }

    @GetMapping("/{id}")
    public Denuncia obtener(@PathVariable Integer id) {
        return service.obtener(id);
    }

    @GetMapping("/ciudadano/{dni}")
    public List<Denuncia> listarPorDni(@PathVariable String dni) {
        return service.listarPorDni(dni);
    }

    @PutMapping("/{id}")
    public Denuncia atender(@PathVariable Integer id) {
        return service.atender(id);
    }
}
