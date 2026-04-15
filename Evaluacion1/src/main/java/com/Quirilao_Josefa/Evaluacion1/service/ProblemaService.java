package com.Quirilao_Josefa.Evaluacion1.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Quirilao_Josefa.Evaluacion1.model.EstadoProblema;
import com.Quirilao_Josefa.Evaluacion1.model.Prioridad;
import com.Quirilao_Josefa.Evaluacion1.model.Problema;
import com.Quirilao_Josefa.Evaluacion1.model.ProblemaDTO;
import com.Quirilao_Josefa.Evaluacion1.repository.ProblemaRepository;

@Service
public class ProblemaService {
    private final ProblemaRepository repository;

    public ProblemaService(ProblemaRepository repository) {
        this.repository = repository;
    }


    public Problema registrar(ProblemaDTO dto) {
        Problema problema = new Problema(
                null,
                dto.getTitulo(),
                dto.getDescripcion(),
                dto.getEstado(),
                dto.getPrioridad(),
                dto.getUsuarioReporta()
        );
        return repository.guardar(problema);
    }


    public List<Problema> obtenerTodas() {
        return repository.obtenerTodas();
    }

    public Problema obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IncidenciaNotFoundException(id));
    }


    public Problema actualizar(Long id, ProblemaDTO dto) {
        Problema existente = repository.buscarPorId(id)
                .orElseThrow(() -> new IncidenciaNotFoundException(id));

        existente.setTitulo(dto.getTitulo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setEstado(dto.getEstado());
        existente.setPrioridad(dto.getPrioridad());
        existente.setUsuarioReporta(dto.getUsuarioReporta());
        existente.setFechaActualizacion(LocalDateTime.now());

        return repository.guardar(existente);
    }

    public void eliminar(Long id) {
        if (!repository.existePorId(id)) {
            throw new ProblemaNotFoundException(id);
        }
        repository.eliminar(id);
    }

    public List<Problema> buscarPorEstado(String estado) {
        try {
            EstadoProblema estadoEnum = EstadoProblema.valueOf(estado.toUpperCase());
            return repository.buscarPorEstado(estadoEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado inválido: " + estado +
                    ". Valores válidos: ABIERTA, EN_PROCESO, RESUELTA, CERRADA");
        }
    }

    // Buscar por prioridad
    public List<Problema> buscarPorPrioridad(String prioridad) {
        try {
            Prioridad prioridadEnum = Prioridad.valueOf(prioridad.toUpperCase());
            return repository.buscarPorPrioridad(prioridadEnum);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Prioridad inválida: " + prioridad +
                    ". Valores válidos: BAJA, MEDIA, ALTA, CRITICA");
        }
    }

    // Buscar por usuario
    public List<Problema> buscarPorUsuario(String usuario) {
        return repository.buscarPorUsuario(usuario);
    }

    // Obtener incidencias ordenadas por prioridad (CRITICA primero)
    public List<Problema> obtenerOrdenadasPorPrioridad() {
        return repository.obtenerOrdenadasPorPrioridad();
    }
}

