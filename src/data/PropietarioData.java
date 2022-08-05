/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import entities.Propietario;

/**
 *
 * @author agusv
 */
public class PropietarioData {

    private Connection conexion = null;
    private PersonaData personaData;

    public PropietarioData(Conexion conexion) {
        super();
        this.conexion = conexion.getConexion();
        personaData = new PersonaData(conexion);
    }

    public Boolean agregarPropietario(Propietario propietario) {
        Boolean result = false;

        if (propietario.getPersona().getId() != null) {
            // Editamos persona
            personaData.editarPersona(propietario.getPersona());            
        } else {
            // Agregamos persona
            personaData.agregarPersona(propietario.getPersona());            
        }

        try {
            String querySql = "INSERT INTO propietario(id_persona) VALUES (?)";
            PreparedStatement ps = conexion.prepareStatement(querySql, RETURN_GENERATED_KEYS);
            ps.setInt(1, propietario.getPersona().getId());
            ps.executeQuery();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                propietario.setId(resultSet.getInt(1));
                result = true;
            }
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No se ah podido guardar el propietario\n" + e.getMessage(),
                    "Error al guardar el propietario", JOptionPane.WARNING_MESSAGE);
        }

        return result;

    }

    public ArrayList<Propietario> obtenerPropietarios() {
        ArrayList<Propietario> propietarioList = new ArrayList<>();
        try {
            String querySql = "SELECT * FROM propietario";
            PreparedStatement ps = conexion.prepareStatement(querySql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Propietario propietario = new Propietario();
                propietario.setId(resultSet.getInt("id_propietario"));
                propietario.setPersona(personaData.obtenerPersonaXId(resultSet.getInt("id_persona")));
                propietarioList.add(propietario);
            }
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conseguir lista de propietarios" + e.getMessage());
        }
        return propietarioList;
    }

    public Propietario obtenerPropietariosXId(Integer id) {
        Propietario propietario = new Propietario();
        try {
            String querySql = "SELECT * FROM propietario WHERE id_propietario=?";
            PreparedStatement ps = conexion.prepareStatement(querySql);
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                propietario.setId(result.getInt("id_propietario"));
                propietario.setPersona(personaData.obtenerPersonaXId(result.getInt("id_persona")));
            }

            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el propietario ID: " + id + "\n" + e.getMessage());
            propietario = null;
        }

        return propietario;
    }

    public Propietario obtenerPropietariosXDni(Long dni) {
        Propietario propietario = new Propietario();
        try {
            propietario.setPersona(personaData.obtenerPersonaXDni(dni));            
            if (propietario.getPersona().getId() != null) {
                String querySql = "SELECT * FROM propietario  WHERE id_persona=?";
                PreparedStatement ps = conexion.prepareStatement(querySql);
                ps.setLong(1, propietario.getPersona().getId());
                ResultSet result = ps.executeQuery();
                while (result.next()) {                    
                    propietario.setId(result.getInt("id_propietario"));
                } 
                ps.close();
            } else {
                propietario = null;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el propietario DNI: " + dni + "\n" + e.getMessage());
            propietario = null;
        }

        return propietario;
    }

    public Boolean editarPropietario(Propietario propietario) {
        Boolean result = false;
        try {
            personaData.editarPersona(propietario.getPersona());
        } catch (Exception e) {            
            JOptionPane.showMessageDialog(null, "Error al actualizar el propietario DNI: "
                    + propietario.getPersona().getDni() + "\n" + e.getMessage());
        }

        return result;
    }

    public Boolean eliminarPropietario(Integer id) {
        Boolean result = false;
        try {
            String querySql = "DELETE FROM propietario WHERE id_propietario=?";
            PreparedStatement ps = conexion.prepareStatement(querySql);
            ps.setInt(1, id);
            result = ps.executeUpdate() != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo eliminar al propietario ID: " + id + "\n" + e.getMessage(),
                    "Error al borrar el propietario", JOptionPane.WARNING_MESSAGE);
        }

        return result;
    }
}
