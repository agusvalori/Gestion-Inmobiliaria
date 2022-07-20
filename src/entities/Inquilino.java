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
    private String condicion;
    private int cantRenovaciones;
            
    public Inquilino() {
    }

    public Inquilino(Integer id, Persona persona, String condicion, int cantRenovaciones) {
        this.id = id;
        this.persona = persona;
        this.condicion = condicion;
        this.cantRenovaciones = cantRenovaciones;
    }

    public Integer getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getCondicion() {
        return condicion;
    }

    public int getCantRenovaciones() {
        return cantRenovaciones;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public void setCantRenovaciones(int cantRenovaciones) {
        this.cantRenovaciones = cantRenovaciones;
    }

    
    
}
