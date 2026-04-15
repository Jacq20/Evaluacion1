package com.Quirilao_Josefa.Evaluacion1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Quirilao_Josefa.Evaluacion1.model.Problema;
import com.Quirilao_Josefa.Evaluacion1.model.ProblemaDTO;
import com.Quirilao_Josefa.Evaluacion1.service.ProblemaService;

@RestController
@RequestMapping
public class ProblemaController {
    private final ProblemaService service;

    public ProblemaController(ProblemaService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Problema> registrar(@Valid @RequestBody ProblemaDTO dto) {
        Problema nueva = service.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }


    @GetMapping
    public ResponseEntity<List<Problema>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Problema> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Problema> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProblemaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/buscar/estado/{estado}")
    public ResponseEntity<List<Problema>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.buscarPorEstado(estado));
    }

 
    @GetMapping("/buscar/prioridad/{prioridad}")
    public ResponseEntity<List<Problema>> buscarPorPrioridad(@PathVariable String prioridad) {
        return ResponseEntity.ok(service.buscarPorPrioridad(prioridad));
    }


    @GetMapping("/buscar/usuario/{usuario}")
    public ResponseEntity<List<Problema>> buscarPorUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(service.buscarPorUsuario(usuario));
    }

    @GetMapping("/ordenadas/prioridad")
    public ResponseEntity<List<Problema>> obtenerOrdenadasPorPrioridad() {
        return ResponseEntity.ok(service.obtenerOrdenadasPorPrioridad());
    }
}
