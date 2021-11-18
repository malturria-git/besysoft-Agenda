package com.besysoft.Agenda.servicios.implementacion;

import com.besysoft.Agenda.excepciones.ExcepcionPersonaRepetida;
import com.besysoft.Agenda.modelo.Persona;
import com.besysoft.Agenda.repositorio.PersonaRepository;
import com.besysoft.Agenda.servicios.contrato.PersonaDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaDAOImpl extends GenericoDAOImpl<Persona, PersonaRepository> implements PersonaDAO {

    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Persona save(Persona persona) {
        try{
            if(repository.buscarPersonaPorCuil(persona.getCuil()).isPresent())
                throw new ExcepcionPersonaRepetida("Ya existe una persona con el cuil "+ persona.getCuil() );

            return repository.save(persona);
        }catch (ExcepcionPersonaRepetida epr){
            System.out.print(epr);
        }
        catch (Exception e){
            System.out.print(e);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarPorNombreYApellido(String nombre, String apellido) {
        return repository.buscarPorNombreYApellido(nombre, apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarPersonaPorCiudad(String ciudad) {
        return repository.buscarPersonaPorCiudad(ciudad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarPersonaPorApellido(String apellido) {
        return repository.buscarPersonaPorApellido(apellido);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> buscarPersonaPorCuil(String cuil) {
        return repository.buscarPersonaPorCuil(cuil);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Persona> buscarPorNombreApellidoYCiudades(String nombre, String apellido, List<String> ciudades){
        return repository.buscarPorNombreApellidoYCiudades(nombre, apellido, ciudades);

    }

}
