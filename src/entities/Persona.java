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
    private Boolean estado;

    public Persona() {
    }

    public Persona(Integer id, String nombre, String apellido, Long dni,  Long cuit,String email, Long telefono, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuit = cuit;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
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
    
    
    
    
    
}
