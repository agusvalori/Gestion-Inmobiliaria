/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;

import entities.Inquilino;

/**
 *
 * @author agusv
 */
public class InquilinoData {

    private Connection conn = null;

    public InquilinoData(Conexion conn) {
        super();

        this.conn = conn.getConexion();
    }
    

    public Boolean agregarInquilino (){
        Boolean result =false;
        try {
            String querySql="INSERT INTO inquilino(nombre, apellido, dni, cuit, telefono, nombre_Garante, apellido_garante, dni_garante, activo) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(querySql,RETURN_GENERATED_KEYS);
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        return result;
    }

    public ArrayList<Inquilino> obtenerInquilinos(){
        ArrayList<Inquilino> inquilinoList = new ArrayList<>();

        return inquilinoList;
    }
    
    
}
