/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author agusv
 */
public class Propietario {
    private Integer id;
    private Persona persona;

    public Propietario() {
        super();
    }

    public Propietario(Integer id, Persona persona) {
        super();
        this.id = id;
        this.persona = persona;
    }

    public Integer getId() {
        return id;
    }
    public Persona getPersona() {
        return persona;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
}
