package com.diaz.denunciaservice.controller;

import com.diaz.denunciaservice.entity.Denuncia;
import com.diaz.denunciaservice.service.DenunciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    // "" y "/" para aceptar /denuncias y /denuncias/
    @PostMapping({"", "/"})
    public Denuncia registrar(@RequestBody Denuncia denuncia) {
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
