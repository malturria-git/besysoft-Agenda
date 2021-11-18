package com.besysoft.Agenda.repositorio;

import com.besysoft.Agenda.modelo.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
    @Query("select p from Persona p where p.nombre = ?1 and p.apellido = ?2")
    Iterable<Persona> buscarPorNombreYApellido(String nombre, String apellido);
    @Query("select p from Persona p where p.apellido like %?1%")
    Iterable<Persona> buscarPersonaPorApellido(String apellido);
    @Query("select p from Persona p where p.ciudad like %?1%")
    Iterable<Persona> buscarPersonaPorCiudad(String ciudad);
    @Query("select p from Persona p where p.cuil  = ?1")
    Optional<Persona> buscarPersonaPorCuil(String cuil);
    @Query("select p from Persona p where p.nombre = ?1 and p.apellido = ?2 and p.ciudad in ?3" )
    Iterable<Persona> buscarPorNombreApellidoYCiudades(String nombre, String apellido, List<String> ciudades);
}
