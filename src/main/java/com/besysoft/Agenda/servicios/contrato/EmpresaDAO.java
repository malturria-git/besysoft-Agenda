package com.besysoft.Agenda.servicios.contrato;

import com.besysoft.Agenda.modelo.Empresa;

import java.util.Optional;

public interface EmpresaDAO {
    Optional<Empresa> findById(Integer id);
    Empresa save(Empresa empresa);
    Iterable<Empresa> findAll();
}