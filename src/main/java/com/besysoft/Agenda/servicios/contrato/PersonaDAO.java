package com.besysoft.Agenda.servicios.contrato;

import com.besysoft.Agenda.modelo.Persona;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonaDAO {
    Optional<Persona> findById(Integer id);
    Persona save(Persona empresa);
    Iterable<Persona> findAll();

    Iterable<Persona> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<Persona> buscarPersonaPorCuil(String cuil);
    Iterable<Persona> buscarPersonaPorCiudad(String ciudad);
    Iterable<Persona> buscarPersonaPorApellido(String apellido);
    Iterable<Persona> buscarPorNombreApellidoYCiudades(String nombre, String apellido, List<String> ciudades);
}