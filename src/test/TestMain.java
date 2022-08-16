/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import data.Conexion;
import data.InmuebleData;
import data.InquilinoData;
import data.PersonaData;
import entities.Inmueble;
import entities.Inquilino;
import entities.Persona;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author agusv
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Conexion conexion = new Conexion();
        Inmueble inmueble = new Inmueble();
        ArrayList<Inmueble> inmuebleList = new ArrayList<>();
        InmuebleData inmuebleData = new InmuebleData(conexion);


        System.out.println("Inmuebles Alquilados");
        inmuebleList = inmuebleData.obtenerInmueblesAlquilados();
        for (Inmueble inmueble2 : inmuebleList) {
            System.out.println(inmueble2.getId()+" - "+inmueble2.getDireccion()+"\n");
        }
        
        System.out.println("Inmuebles NO Alquilados");
        inmuebleList = inmuebleData.obtenerInmueblesNoAlquilados();
        for (Inmueble inmueble2 : inmuebleList) {
            System.out.println(inmueble2.getId()+" - "+inmueble2.getDireccion()+"\n");
        }



        /*
         * LocalDate currentDate = LocalDate.now();
         * LocalDate oldDate = LocalDate.parse("2022-08-04");
         * 
         * System.out.println("Fecha Actual: "+currentDate);
         * System.out.println("Fecha Anterior: "+oldDate);
         * 
         * 
         * System.out.println("\nDamos formato a la fecha: dd-MM-YYYY");
         * DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-YYYY");
         * System.out.println("Fecha Actual: "+formatoFecha.format(currentDate));
         * System.out.println("Fecha Anterior: "+formatoFecha.format(oldDate));
         * 
         * System.out.println("\nDamos formato a la fecha: dd/MM/YYYY");
         * formatoFecha = DateTimeFormatter.ofPattern("dd/MM/YYYY");
         * System.out.println("Fecha Actual: "+formatoFecha.format(currentDate));
         * System.out.println("Fecha Anterior: "+formatoFecha.format(oldDate));
         * 
         * System.out.println("\nEs anterior a hoy: \n"+currentDate.isBefore(oldDate));
         */

        /*
         * // TODO code application logic here
         * Conexion conexion = new Conexion();
         * 
         * // Agregar persona
         * 
         * PersonaData personaData = new PersonaData(conexion);
         * Persona persona = new Persona();
         * persona.setNombre("Agustin");
         * persona.setApellido("Valori");
         * persona.setDni(35893333L);
         * persona.setCuit(20358933301L);
         * persona.setTelefono(2664860724L);
         * persona.setEmail("agusvalori@gmail.com");
         * persona.setEstado(true);
         * // personaData.agregarPersona(persona);
         */

        /*
         * ### Obtener persona x ID ###
         * PersonaData personaData = new PersonaData(conexion);
         * Persona persona = new Persona();
         * persona = personaData.obtenerPersonaXId(11);
         * if (persona.getNombre()!=null) {
         * System.out.println("Persona encontrada: " + persona.getNombre() + " " +
         * persona.getApellido());
         * System.out.println("Email de persona encontrada: " + persona.getEmail());
         * }
         * else{
         * System.out.println("Persona no encontrada");
         * }
         */
        /*
         * ### Obtener persona x DNI ###
         * PersonaData personaData = new PersonaData(conexion);
         * Persona persona = new Persona();
         * persona = personaData.obtenerPersonaXDni(35893330L);
         * if (persona.getNombre()!=null) {
         * System.out.println("Persona encontrada: " + persona.getNombre() + " " +
         * persona.getDni());
         * }
         * else{
         * System.out.println("Persona no encontrada");
         * }
         */
        /*
         * ### Obtener personas ArrayList ###
         * PersonaData personaData = new PersonaData(conexion);
         * ArrayList<Persona> personaList = new ArrayList();
         * 
         * personaList = personaData.obtenerPersonas();
         * if (personaList != null) {
         * for (Persona persona : personaList) {
         * 
         * System.out.println(personaList.indexOf(persona) + " - Persona encontrada: " +
         * persona.getNombre() + " " + persona.getDni());
         * 
         * }
         * } else {
         * System.out.println("Persona no encontrada");
         * }
         */
        /*
         * ### EDITAR PERSONA ###
         * PersonaData personaData = new PersonaData(conexion);
         * Persona persona = new Persona();
         * persona.setId(1);
         * persona.setNombre("Dalina");
         * persona.setApellido("Valori");
         * persona.setDni(39992295L);
         * persona.setCuit(20399922951L);
         * persona.setTelefono(0012010212L);
         * persona.setEmail("dalina97@gmail.com");
         * persona.setEstado(true);
         * personaData.editarPersona(persona);
         */

        /*
         * ### ELIMINAR PERSONA ###
         * PersonaData personaData = new PersonaData(conexion);
         * personaData.eliminarPersona(7);
         * personaData.eliminarPersona(8);
         */
        // #################################################################################
        // Agregar un inquilino
        /*
         * InquilinoData inquilinoData = new InquilinoData(conexion);
         * Inquilino inquilino = new Inquilino();
         * inquilino.setPersona(persona);
         * inquilinoData.agregarInquilino(inquilino);
         */

    }

}
