package com.Quirilao_Josefa.Evaluacion1.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Quirilao_Josefa.Evaluacion1.model.EstadoProblema;
import com.Quirilao_Josefa.Evaluacion1.model.Prioridad;
import com.Quirilao_Josefa.Evaluacion1.model.Problema;
import com.Quirilao_Josefa.Evaluacion1.model.ProblemaDTO;

@Repository
public class ProblemaRepository {

    List<Problema> problemas = new ArrayList<>();

    // Agregar nueva Problema
    public Problema agregar(Problema problema) {
        problemas.add(problema);
        return problema;
    }

    // Leer todas
    public List<Problema> leerTodas() {
        return problemas;
    }

    // Buscar por ID
    public Problema buscarPorId(int id) {
        for (Problema problema : problemas) {
            if (problema.getId() == id) {
                return problema;
            }
        }
        return null;
    }

    // Actualizar incidencia existente
    public Problema actualizar(int id, Problema datos) {
        for (Problema problema : problemas) {
            if (problema.getId() == id) {
                problema.setTitulo(datos.getTitulo());
                problema.setDescripcion(datos.getDescripcion());
                problema.setEstado(datos.getEstado());
                problema.setPrioridad(datos.getPrioridad());
                problema.setUsuarioReporta(datos.getUsuarioReporta());
                problema.setFechaActualizacion(LocalDateTime.now());
                return problema;
            }
        }
        return null;
    }

    // Eliminar incidencia
    public boolean eliminar(int id) {
        return problemas.removeIf(i -> i.getId() == id);
    }

    // Buscar por estado
    public List<Problema> buscarPorEstado(EstadoProblema estado) {
        List<Problema> resultado = new ArrayList<>();
        for (Problema problema : problemas) {
            if (problemas.getEstado() == estado) {
                resultado.add(problema);
            }
        }
        return resultado;
    }

    // Buscar por prioridad
    public List<Problema> buscarPorPrioridad(Prioridad prioridad) {
        List<Problema> resultado = new ArrayList<>();
        for (Problema problema : problemas) {
            if (problema.getPrioridad() == prioridad) {
                resultado.add(problema);
            }
        }
        return resultado;
    }

    // Buscar por usuario
    public List<Problema> buscarPorUsuario(String usuario) {
        List<Problema> resultado = new ArrayList<>();
        for (Problema problema : problemas) {
            if (problema.getUsuarioReporta().equalsIgnoreCase(usuario)) {
                resultado.add(problema);
            }
        }
        return resultado;
    }

    // Reporte: resumen de incidencias (id, titulo, estado, prioridad)
    public List<ProblemaDTO> reporte() {
        List<ProblemaDTO> reporte = new ArrayList<>();
        for (Problema problema : problemas) {
            ProblemaDTO obj = new ProblemaDTO(
                    problema.getId(),
                    problema.getTitulo(),
                    problema.getEstado().toString(),
                    problema.getPrioridad().toString()
            );
            reporte.add(obj);
        }
        return reporte;
    }

    // Ordenar por prioridad: CRITICA > ALTA > MEDIA > BAJA
    public List<Problema> ordenarPorPrioridad() {
        List<Prioridad> orden = List.of(Prioridad.CRITICA, Prioridad.ALTA, Prioridad.MEDIA, Prioridad.BAJA);
        List<Problema> ordenadas = new ArrayList<>(problemas);
        ordenadas.sort((a, b) -> orden.indexOf(a.getPrioridad()) - orden.indexOf(b.getPrioridad()));
        return ordenadas;
    }

    // Seed: datos de prueba
    public void seed() {
        problemas.add(new Problema("Servidor caído", "El servidor principal no responde desde las 08:00 hrs", EstadoProblema.ABIERTA, Prioridad.CRITICA, "juan.perez"));
        problemas.add(new Problema("Error en login", "Los usuarios no pueden iniciar sesión con Google", EstadoProblema.EN_PROCESO, Prioridad.ALTA, "maria.gonzalez"));
        problemas.add(new Problema("Lentitud en reportes", "Los reportes tardan más de 30 segundos en cargar", EstadoProblema.ABIERTA, Prioridad.MEDIA, "carlos.rojas"));
        problemas.add(new Problema("Error exportar PDF", "Al exportar se genera un archivo corrupto", EstadoProblema.ABIERTA, Prioridad.ALTA, "ana.silva"));
        problemas.add(new Problema("Notificaciones duplicadas", "El sistema envía el mismo correo dos veces", EstadoProblema.EN_PROCESO, Prioridad.MEDIA, "pedro.mora"));
        problemas.add(new Problema("Botón no funciona", "El botón guardar no responde en Firefox", EstadoProblema.RESUELTA, Prioridad.BAJA, "lucia.fuentes"));
        problemas.add(new Problema("Pérdida de datos", "Se pierden datos al cerrar sesión inesperadamente", EstadoProblema.ABIERTA, Prioridad.CRITICA, "roberto.vega"));
        problemas.add(new Problema("Imagen no carga", "Las imágenes del perfil no se muestran", EstadoProblema.CERRADA, Prioridad.BAJA, "sofia.castro"));
    }
}

