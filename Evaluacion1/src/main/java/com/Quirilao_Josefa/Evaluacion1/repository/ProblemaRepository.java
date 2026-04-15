package com.Quirilao_Josefa.Evaluacion1.repository;

import org.springframework.stereotype.Repository;

@Repository
public class ProblemaRepository {
    List<Problema> problemas= new ArrayList<>();

    public Problema agregar(Problema problema){
        problemas.add(problema);
        return problema;
    }
}
