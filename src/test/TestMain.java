/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import data.Conexion;
import data.PersonaData;
import entities.Persona;

/**
 *
 * @author agusv
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conexion = new Conexion();
        


        //Agregar persona
        PersonaData personaData = new PersonaData(conexion);
        Persona persona = new Persona();
        persona.setNombre("Agustin");
        persona.setApellido("Valori");
        persona.setDni(35893331L);
        //persona.setCuit(20358933301L);
        //persona.setTelefono(2664860724L);
        persona.setEmail("agusvalori@gmail.com");
        persona.setEstado(true);

        personaData.agregarPersona(persona);
        
        
    }

}
