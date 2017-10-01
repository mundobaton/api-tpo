package edu.uade.api.tpo.model;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

    private String id;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private Password password;
    private CuentaCorriente cuentaCorriente;
    private List<Publicacion> publicaciones;
    private String mail;
    private List<Calificacion> calificaciones;

    public Usuario() {

    }

    public Usuario(String nombreUsuario, String nombre, String apellido, String mail, Domicilio domicilio, Password password,
                   CuentaCorriente cuentaCorriente, List<Publicacion> publicaciones, List<Calificacion> calificaciones) {
        super();
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.password = password;
        this.cuentaCorriente = cuentaCorriente;
        this.publicaciones = publicaciones;
        this.mail = mail;
        this.calificaciones = calificaciones;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public void addPublicacion(Publicacion publicacion) {
        this.publicaciones.add(publicacion);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }
}
