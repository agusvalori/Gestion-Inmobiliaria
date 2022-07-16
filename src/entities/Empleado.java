/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author agusv
 */
public class Empleado {

    private Integer id;
    private Persona persona;
    private String cargo;

    public Empleado() {
        super();
    }

    public Empleado(Integer id, Persona persona, String cargo) {
        this.id = id;
        this.persona = persona;
        this.cargo = cargo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getCargo() {
        return cargo;
    }

}
