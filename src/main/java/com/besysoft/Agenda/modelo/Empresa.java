package com.besysoft.Agenda.modelo;

import com.besysoft.Agenda.modelo.Persona;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String nombre;
    private String ubicacion;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "persona_empresa",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id")
    )
    Set<Persona> personas = new HashSet<>();

    public Set<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }

    public void addPersona(Persona persona) {
        this.personas.add(persona);
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Empresa(){}

    public Empresa(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'');
        if(!this.personas.equals(null)){
            sb.append(", contactos=");
            for (Persona p: this.personas) {
                sb.append('\'' + p.getNombre() + " " +  p.getApellido() + '\'');
            }
        }

        return sb.toString();
    }
}
