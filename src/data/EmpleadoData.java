/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import javax.swing.JOptionPane;

import entities.Empleado;

/**
 *
 * @author agusv
 */
public class EmpleadoData {

    private Connection conexion = null;
    private PersonaData personaData;

    public EmpleadoData(Conexion conexion) {
        super();
        this.conexion = conexion.getConexion();
        personaData = new PersonaData(conexion);
    }

    public Boolean agregarEmpleado(Empleado empleado) {
        Boolean result = false;
        if (personaData.agregarPersona(empleado.getPersona())) {
            try {
                String querySql = "INSERT INTRO empleado (id_persona,cargo) VALUES (?,?)";
                PreparedStatement ps = conexion.prepareStatement(querySql, RETURN_GENERATED_KEYS);
                ps.setInt(1, empleado.getPersona().getId());
                ps.setString(2, empleado.getCargo());
                ps.executeQuery();
                ResultSet resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    empleado.setId(resultSet.getInt(1));
                    result = true;
                }
                ps.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se puedo agregar el empleado \n" + e.getMessage(),
                        "Error al agregar el empleado", JOptionPane.WARNING_MESSAGE);
            }
        }
        return result;
    }


    public ArrayList<Empleado> obtenerEmpleados(){
        ArrayList<Empleado> empleadoList = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puedo obtener los empleado \n" + e.getMessage(),
                        "Error al obtener los empleados", JOptionPane.WARNING_MESSAGE);
        }

        return empleadoList;
    }

}
