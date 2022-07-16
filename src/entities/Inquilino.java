/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author agusv
 */ 
public class Inquilino {

    private Integer id;
    private Persona persona;    

    public Inquilino() {
    }

    public Inquilino(Integer id, Persona persona) {
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
