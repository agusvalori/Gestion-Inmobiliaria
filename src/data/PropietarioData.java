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
    PersonaData personaData;

    public PropietarioData(Conexion conexion) {
        super();
        this.conexion = conexion.getConexion();
        personaData = new PersonaData(conexion);
    }

    public Boolean agregarPropietario(Propietario propietario) {
        Boolean result = false;
        if (personaData.agregarPersona(propietario.getPersona())) {
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

}
