/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import entities.Empleado;
import entities.Persona;

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

        if (empleado.getPersona().getId() != null) {
            // Editamos persona
            personaData.editarPersona(empleado.getPersona());            
        } else {
            // Agregamos persona
            personaData.agregarPersona(empleado.getPersona());            
        }

        try {
            String querySql = "INSERT INTO empleado (id_persona,cargo) VALUES (?,?)";
            PreparedStatement ps = conexion.prepareStatement(querySql, RETURN_GENERATED_KEYS);
            ps.setInt(1, empleado.getPersona().getId());
            ps.setString(2, empleado.getCargo());

            ps.executeUpdate();
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
        return result;
    }

    public ArrayList<Empleado> obtenerEmpleados() {
        ArrayList<Empleado> empleadoList = new ArrayList<>();
        try {
            String querySql = "SELECT * FROM empleado";
            PreparedStatement ps = conexion.prepareStatement(querySql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("id_empleado"));
                empleado.setCargo(resultSet.getString("cargo"));
                empleado.setPersona(personaData.obtenerPersonaXId(resultSet.getInt("id_persona")));
                empleadoList.add(empleado);
            }
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puedo obtener los empleado \n" + e.getMessage(),
                    "Error al obtener los empleados", JOptionPane.WARNING_MESSAGE);
        }

        return empleadoList;
    }

    public Empleado obtenerEmpleadosXDni(Long dni) {
        Empleado empleado = new Empleado();
        try {
            Persona persona = personaData.obtenerPersonaXDni(dni);
            if (persona.getId() != null) {
                empleado.setPersona(persona);
                String querySql = "SELECT * FROM empleado WHERE id_persona=? ";
                PreparedStatement ps = conexion.prepareStatement(querySql);
                ps.setInt(1, persona.getId());
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    empleado.setId(resultSet.getInt("id_empleado"));
                    empleado.setCargo(resultSet.getString("cargo"));
                }
                ps.close();
            } else {
                empleado = null;
            }

        } catch (Exception e) {
            empleado = null;
            JOptionPane.showMessageDialog(null, "No se puedo obtener los empleado \n" + e.getMessage(),
                    "Error al obtener los empleados", JOptionPane.WARNING_MESSAGE);
        }

        return empleado;
    }

    public Boolean editarEmpleado(Empleado empleado) {
        Boolean result = false;
        if (personaData.editarPersona(empleado.getPersona())) {
            try {
                String querySql = "UPDATE empleado SET id_persona=?, cargo=?  WHERE id_empleado=?";
                PreparedStatement ps = conexion.prepareStatement(querySql);
                ps.setInt(1, empleado.getPersona().getId());
                ps.setString(2, empleado.getCargo());
                ps.setInt(3, empleado.getId());
                if (ps.executeUpdate() != 0) {
                    result = true;                    
                }
                ps.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al editar el propietario: \n" + e.getMessage());
            }
        }
        return result;
    }

    public Boolean eliminarEmpleado(Integer id) {
        Boolean result = false;
        try {
            String querySql = "DELETE FROM empleado WHERE id_empleado=?";
            PreparedStatement ps = conexion.prepareStatement(querySql);
            ps.setInt(1, id);
            result =ps.executeUpdate()!=0;
            ps.close();                
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado: \n"+e.getMessage(), "Error al eliminar el empleado", JOptionPane.WARNING_MESSAGE);
        }

        return result;
    }

}
