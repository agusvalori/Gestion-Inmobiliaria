package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import javax.swing.JOptionPane;

import entities.Persona;

public class PersonaData {
    private Connection conn = null;

    public PersonaData(Conexion conn) {
        super();
        this.conn = conn.getConexion();
    }

    public Boolean agregarPersona(Persona persona) {
        Boolean result = false;
        try {

            String querySql = "INSERT INTO persona(nombre, apellido, dni, cuit, email, telefono,estado) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(querySql, RETURN_GENERATED_KEYS);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setLong(3, persona.getDni());
            ps.setLong(4, persona.getCuit());
            ps.setString(5, persona.getEmail());
            ps.setLong(6, persona.getTelefono());
            ps.setBoolean(7, persona.getEstado());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                persona.setId(resultSet.getInt(1));
                result = true;
                JOptionPane.showMessageDialog(null, "Se ha agregado con exito el inquilino.");
            }
            ps.close();

        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null,
                    "No se ah podido guardar la persona en la base de datos \n" + e.getMessage()+"\n"+e.getLocalizedMessage(),
                    "Error al guardar en la base de datos", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

}
