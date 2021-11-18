package com.besysoft.Agenda.modelo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personas")

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String apellido;
    @Column(length = 20)
    private String telefono;
    @Column(length = 30)
    private String ciudad;
    @Column(nullable = false, unique = true, length = 13)
    private String cuil;

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    @ManyToMany(
            fetch = FetchType.EAGER,
            mappedBy = "personas"
    )
    private Set<Empresa> empresas = new HashSet<>();

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Persona(){}
    public Persona(String nombre, String apellido, String cuil, String telefono, String ciudad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuil = cuil;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("codigo=" + codigo +
                ", cuil='" + cuil + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", telefono='" + telefono + '\'');
        if(!this.empresas.equals(null)){
            sb.append(", Empresa de la cual es contacto=");
            for (Empresa e: this.empresas) {
                sb.append('\'' + e.getNombre() + '\'');
            }
        }

        return sb.toString();
    }
}

