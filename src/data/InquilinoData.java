/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.Inquilino;
import entities.Persona;

/**
 *
 * @author agusv
 */
public class InquilinoData {

    private Connection conn = null;
    PersonaData personaData;

    public InquilinoData(Conexion conn) {
        super();
        this.conn = conn.getConexion();
        personaData = new PersonaData(conn);
    }

    public Boolean agregarInquilino(Inquilino inquilino) {
        Boolean result = false;
        if (personaData.agregarPersona(inquilino.getPersona())) {
            try {

                String querySql = "INSERT INTO inquilino(id_persona) VALUES (?)";
                PreparedStatement ps = conn.prepareStatement(querySql, RETURN_GENERATED_KEYS);
                ps.setInt(1, inquilino.getPersona().getId());
                ps.executeUpdate();
                ResultSet resultSet = ps.getGeneratedKeys();

                if (resultSet.next()) {
                    inquilino.setId(resultSet.getInt(1));
                    result = true;
                }
                ps.close();
                JOptionPane.showMessageDialog(null,
                        "Inquilino dni:" + inquilino.getPersona().getDni() + " agregado con exito\n",
                        "Inquilino agregado con exito", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(null,
                        "Error al ingresar el inquilino en la base de dato\n" + e.getMessage(),
                        "Error al insertar un dato en la BD", JOptionPane.WARNING_MESSAGE);
            }
        }

        return result;
    }

    public ArrayList<Inquilino> obtenerInquilinos() {
        ArrayList<Inquilino> inquilinoList = new ArrayList<>();
        try {
            String querySql = "SELECT * FROM inquilinos";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Inquilino inquilino = new Inquilino();                
                inquilino.setId(result.getInt("id_inquilino"));
                inquilinoList.add(inquilino);
            }
            ps.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al conseguir lista de INquilinos" + ex);
        }

        return inquilinoList;
    }

}
