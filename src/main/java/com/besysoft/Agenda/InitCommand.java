package com.besysoft.Agenda;

import com.besysoft.Agenda.modelo.Empresa;
import com.besysoft.Agenda.modelo.Persona;
import com.besysoft.Agenda.servicios.contrato.EmpresaDAO;
import com.besysoft.Agenda.servicios.contrato.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class InitCommand implements CommandLineRunner {

    @Autowired
    private EmpresaDAO EmpresaService;

    @Autowired
    private PersonaDAO PersonaService;

    @Override
    public void run(String... args) throws Exception {
        datosPredefinidos();
        Menu menu = new Menu();
        menu.armarMenu(EmpresaService,PersonaService);
    }

    public void datosPredefinidos(){
        Empresa empresa1 = new Empresa("Besysoft","Pilar");
        Empresa empresa2 = new Empresa("Google","CABA");
        Persona persona1_1 = new Persona("Pablo","Perez","23-12345678-9","111-1111","CABA");
        Persona persona1_2 = new Persona("Pedro","Gomez","20-52345678-9","222-2222","Cordoba");
        Persona persona2_1 = new Persona("Esteban","Tuero","27-52123678-9","333-3333","Rosario");
        Persona persona2_2 = new Persona("Carlos","Flores","20-52342178-9","444-4444","CABA");

        empresa1.addPersona(persona1_1);
        empresa1.addPersona(persona1_2);
        empresa2.addPersona(persona2_1);
        empresa2.addPersona(persona2_2);
        Empresa save1 = EmpresaService.save(empresa1);
        Empresa save2 = EmpresaService.save(empresa2);


    }


}
