package com.besysoft.Agenda;

import com.besysoft.Agenda.excepciones.ExcepcionGeneral;
import com.besysoft.Agenda.modelo.Empresa;
import com.besysoft.Agenda.modelo.Persona;
import com.besysoft.Agenda.servicios.contrato.EmpresaDAO;
import com.besysoft.Agenda.servicios.contrato.PersonaDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    public void armarMenu(EmpresaDAO empresaService, PersonaDAO personaService) {

        Scanner input = new Scanner(System.in);
        String opcion = "";

        while (!opcion.equals("0")) {
            System.out.println("\n-----------------------------------------");
            System.out.println("1  Agregar una persona a la Agenda");
            System.out.println("2  Agregar una empresa");
            System.out.println("3  Asociar una persona de la agenda a una empresa");
            System.out.println("4  Listar todas las personas");
            System.out.println("5  Listar todas las empresas");
            System.out.println("6  Buscar personas por parámetros");
            System.out.println("0  Salir");
            System.out.println("-----------------------------------------");
            opcion = input.nextLine();
            switch (opcion) {
                case "1":
                    agregarPersona(personaService);
                    break;
                case "2":
                    agregarEmpresa(empresaService);
                    break;
                case "3": vincularPersonaEmpresa(personaService,empresaService);
                    break;
                case "4":
                    listaPersonas(personaService);
                    break;
                case "5":
                    listaEmpresas(empresaService);
                    break;
                case "6":
                    buscaPersonaPorParametro(personaService);
                    break;
                case "0":
                    break;
            }
        }
    }

    public void agregarPersona(PersonaDAO personaService) {
        System.out.println("Eligió ingresar una persona.");
        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la persona: ");
        String nombre = input.nextLine();
        System.out.print("Ingrese el apellido de la persona: ");
        String apellido = input.nextLine();
        System.out.print("Ingrese el cuil de la persona: ");
        String cuil = input.nextLine();
        System.out.print("Ingrese el telefono de la persona: ");
        String telefono = input.nextLine();
        System.out.print("Ingrese el ciudad de la persona: ");
        String ciudad = input.nextLine();
        Persona persona = new Persona(nombre, apellido, cuil, telefono, ciudad);
        try{
            personaService.save(persona);
            System.out.println("Persona registrada.");
        }catch(Exception e){
            System.out.println("Error al grabar persona: " + e.toString());
        }
    }

    public void agregarEmpresa(EmpresaDAO empresaService) {
        System.out.println("Eligió ingresar una empresa.");
        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la empresa: ");
        String nombre = input.nextLine();
        System.out.print("Ingrese el ubicacion de la empresa: ");
        String ubicacion = input.nextLine();
        Empresa empresa = new Empresa(nombre, ubicacion);
        try{
            empresaService.save(empresa);
            System.out.println("Empresa registrada.");
        }catch(Exception e){
            System.out.println("Error al grabar empresa: " + e.toString());
        }
    }

    public void vincularPersonaEmpresa(PersonaDAO personaService, EmpresaDAO empresaService) {
        try {
            System.out.print("Codigo de la persona : ");
            Scanner inputPersona = new Scanner(System.in);
            Integer id = inputPersona.nextInt();
            Optional<Persona> persona = personaService.findById(id);

            if(persona.isEmpty())
                throw new ExcepcionGeneral("No existe persona con el ID: " + id );

            System.out.print("Codigo de la empresa : ");
            Scanner inputEmpresa = new Scanner(System.in);
            id = inputPersona.nextInt();
            Optional<Empresa> empresa = empresaService.findById(id);

            if(empresa.isEmpty())
                throw new ExcepcionGeneral("No existe empresa con el ID: " + id );

            Persona p = persona.get();
            empresa.get().addPersona(p);
            empresaService.save(empresa.get());

        }catch (Exception e){
            System.out.print(e);
        }
    }

    public void listaPersonas(PersonaDAO personaService) {
        List<Persona> personas = (List<Persona>) personaService.findAll();
        for (Persona p : personas) {
            System.out.println(p.toString());

        }
    }

    public void listaEmpresas(EmpresaDAO empresaService) {
        List<Empresa> empresas = (List<Empresa>) empresaService.findAll();
        for (Empresa e : empresas) {
            System.out.println(e.toString());

        }
    }

    public void buscaPersonaPorParametro(PersonaDAO personaService) {
        System.out.println("1 - Buscar personas por CUIL exacto");
        System.out.println("2 - Buscar persona por nombre y apellido exacto");
        System.out.println("3 - Buscar personas por ciudad like");
        System.out.println("4 - Buscar personas por nombre, apellido y varias ciudades");
        System.out.println("0 - Salir");
        Scanner input = new Scanner(System.in);
        String opcion = input.nextLine();
        String opcion_case4 = "";
        String cuil = "";
        String nombre = "";
        String apellido = "";
        String ciudad = "";
        Iterable<Persona> personas;
        List<String> ciudades = new ArrayList<>();
            switch (opcion) {
            case "1":
                System.out.println("Ingrese el CUIL a buscar");
                cuil = input.nextLine();
                Optional<Persona> persona = personaService.buscarPersonaPorCuil(cuil);
                if (!persona.isEmpty())
                    System.out.println( persona.toString() );
                else
                    System.out.println( "No hay datos con los parametros ingresados" );
                break;
            case "2":
                System.out.println("Ingrese el nombre a buscar");
                nombre = input.nextLine();
                System.out.println("Ingrese el apellido a buscar");
                apellido = input.nextLine();
                personas = personaService.buscarPorNombreYApellido(nombre,apellido);
                if ( personas.spliterator().getExactSizeIfKnown() > 0 )
                    personas.forEach(System.out::println);
                else
                    System.out.println( "No hay datos con los parametros ingresados" );
                break;
            case "3":
                System.out.println("Ingrese parte de la ciudad a buscar");
                ciudad = input.nextLine();
                personas = personaService.buscarPersonaPorCiudad(ciudad);
                if ( personas.spliterator().getExactSizeIfKnown() > 0 )
                    personas.forEach(System.out::println);
                else
                    System.out.println( "No hay datos con los parametros ingresados" );
                break;
            case "4":
                System.out.println("Ingrese el nombre a buscar");
                nombre = input.nextLine();
                System.out.println("Ingrese el apellido a buscar");
                apellido = input.nextLine();
                System.out.println("Ingrese una ciudad");
                ciudades.add( input.nextLine() );

                while (!opcion_case4.equals("0")) {
                    System.out.println("1 - Agregar otra ciudad");
                    System.out.println("0 - Terminar");
                    opcion_case4 = input.nextLine();
                    switch (opcion_case4) {
                        case "1":
                            System.out.println("Ingrese una ciudad");
                            ciudades.add( input.nextLine() );
                            break;
                    }
                }
                personas = personaService.buscarPorNombreApellidoYCiudades(nombre,apellido,ciudades);
                if ( personas.spliterator().getExactSizeIfKnown() > 0 )
                    personas.forEach(System.out::println);
                else
                    System.out.println( "No hay datos con los parametros ingresados" );
                break;
        }
    }
}
