/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;

/**
 *
 * @author agusv
 */
public class InmuebleData {

    private Connection conexion =null;
    PersonaData personaData;

    public InmuebleData(Conexion conexion) {
        super();
        this.conexion = conexion.getConexion();
        personaData = new PersonaData(conexion);
    }


    public Boolean agregarInmueble(){
        Boolean result = false;

        return result;
    }


    
}
