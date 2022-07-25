/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author agusv
 */
public class Persona {
    private Integer id;
    private String nombre;
    private String apellido;
    private Long dni;
    private Long cuit=0L;
    private String email;
    private Long telefono=0L;
    private Boolean estado=true;
    private String calificacionInquilino="Ninguna";
    private String calificacionPropietario="Ninguna";
    private String calificacionGarante="Ninguna";
    private String calificacionEmpleado="Ninguna";

    public Persona() {
    }

    public Persona(Integer id, String nombre, String apellido, Long dni, String email, Boolean estado, String calificacionInquilino, String calificacionPropietario, String calificacionGarante, String calificacionEmpleado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.estado = estado;
        this.calificacionInquilino = calificacionInquilino;
        this.calificacionPropietario = calificacionPropietario;
        this.calificacionGarante = calificacionGarante;
        this.calificacionEmpleado = calificacionEmpleado;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Long getDni() {
        return dni;
    }

    public Long getCuit() {
        return cuit;
    }

    public String getEmail() {
        return email;
    }

    public Long getTelefono() {
        return telefono;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getCalificacionInquilino() {
        return calificacionInquilino;
    }

    public String getCalificacionPropietario() {
        return calificacionPropietario;
    }

    public String getCalificacionGarante() {
        return calificacionGarante;
    }

    public String getCalificacionEmpleado() {
        return calificacionEmpleado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setCalificacionInquilino(String calificacionInquilino) {
        this.calificacionInquilino = calificacionInquilino;
    }

    public void setCalificacionPropietario(String calificacionPropietario) {
        this.calificacionPropietario = calificacionPropietario;
    }

    public void setCalificacionGarante(String calificacionGarante) {
        this.calificacionGarante = calificacionGarante;
    }

    public void setCalificacionEmpleado(String calificacionEmpleado) {
        this.calificacionEmpleado = calificacionEmpleado;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "ID: "+getId()+" Nombre: "+getNombre() +" Apellido: "+getApellido();
    }
    
}
