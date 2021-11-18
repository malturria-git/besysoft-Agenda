package com.besysoft.Agenda.servicios.implementacion;

import com.besysoft.Agenda.modelo.Empresa;
import com.besysoft.Agenda.repositorio.EmpresaRepository;
import com.besysoft.Agenda.servicios.contrato.EmpresaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmpresaDAOImpl implements EmpresaDAO {

    @Autowired
    private EmpresaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Empresa> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Empresa save(Empresa empresa) {
        return repository.save(empresa);
    }

    @Override
    @Transactional(readOnly = true)

    public Iterable<Empresa> findAll() {
        return repository.findAll();
    }
}
