package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

            String querySql = "INSERT INTO persona(nombre, apellido, dni, cuit, email, telefono,estado,calificacion_inquilino,calificacion_propietario,calificacion_garante,calificacion_empleado) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(querySql, RETURN_GENERATED_KEYS);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setLong(3, persona.getDni());
            ps.setLong(4, persona.getCuit());
            ps.setString(5, persona.getEmail());
            ps.setLong(6, persona.getTelefono());
            ps.setBoolean(7, persona.getEstado());
            ps.setString(8, persona.getCalificacionInquilino());
            ps.setString(9, persona.getCalificacionPropietario());
            ps.setString(10, persona.getCalificacionGarante());
            ps.setString(11, persona.getCalificacionEmpleado());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                persona.setId(resultSet.getInt(1));
                result = true;
            }
            ps.close();

        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null,
                    "No se ah podido guardar la persona en la base de datos \n" + e.getMessage(),
                    "Error al guardar en la base de datos", JOptionPane.WARNING_MESSAGE);
        }
        return result;
    }

    public ArrayList<Persona> obtenerPersonas() {
        ArrayList<Persona> personaList = new ArrayList<>();
        try {
            String querySql = "SELECT * FROM persona";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Persona persona = new Persona();
                persona.setId(resultSet.getInt("id_persona"));
                persona.setNombre(resultSet.getString("nombre"));
                persona.setApellido(resultSet.getString("apellido"));
                persona.setDni(resultSet.getLong("dni"));
                persona.setCuit(resultSet.getLong("cuit"));
                persona.setEmail(resultSet.getString("email"));
                persona.setTelefono(resultSet.getLong("dni"));
                persona.setCalificacionInquilino(resultSet.getString("calificacion_inquilino"));
                persona.setCalificacionPropietario(resultSet.getString("calificacion_propietario"));
                persona.setCalificacionGarante(resultSet.getString("calificacion_garante"));
                persona.setCalificacionEmpleado(resultSet.getString("calificacion_empleado"));
                personaList.add(persona);
            }
            ps.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al obtener las personas desde la base de datos: \n" + e.getMessage());
        }
        return personaList;
    }

    public Persona obtenerPersonaXId(Integer id) {
        Persona persona = new Persona();
        try {
            String querySql = "SELECT * FROM persona WHERE id_persona=?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                persona.setId(resultSet.getInt("id_persona"));
                persona.setNombre(resultSet.getString("nombre"));
                persona.setApellido(resultSet.getString("apellido"));
                persona.setDni(resultSet.getLong("dni"));
                persona.setCuit(resultSet.getLong("cuit"));
                persona.setEmail(resultSet.getString("email"));
                persona.setTelefono(resultSet.getLong("telefono"));
                persona.setCalificacionInquilino(resultSet.getString("calificacion_inquilino"));
                persona.setCalificacionPropietario(resultSet.getString("calificacion_propietario"));
                persona.setCalificacionGarante(resultSet.getString("calificacion_garante"));
                persona.setCalificacionEmpleado(resultSet.getString("calificacion_empleado"));
            }
            ps.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al obtener la persona desde la base de datos: \n" + e.getMessage());
        }
        return persona;
    }

    public Persona obtenerPersonaXDni(Long dni) {
        Persona persona = new Persona();        

        try {
            String querySql = "SELECT * FROM persona WHERE dni=?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setLong(1, dni);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                persona.setId(resultSet.getInt("id_persona"));
                persona.setNombre(resultSet.getString("nombre"));
                persona.setApellido(resultSet.getString("apellido"));
                persona.setDni(resultSet.getLong("dni"));
                persona.setCuit(resultSet.getLong("cuit"));
                persona.setEmail(resultSet.getString("email"));
                persona.setTelefono(resultSet.getLong("telefono"));
                persona.setCalificacionInquilino(resultSet.getString("calificacion_inquilino"));
                persona.setCalificacionPropietario(resultSet.getString("calificacion_propietario"));
                persona.setCalificacionGarante(resultSet.getString("calificacion_garante"));
                persona.setCalificacionEmpleado(resultSet.getString("calificacion_empleado"));
            }
            ps.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al obtener la persona desde la base de datos: \n" + e.getMessage());
        }
        return persona;
    }

    public Boolean editarPersona(Persona persona) {        
        Boolean result = false;
        try {            
            String querySql = "UPDATE persona SET nombre=?, apellido=?, dni=?, cuit=?, email=?, telefono=?,estado=?,calificacion_inquilino=?,calificacion_propietario=? ,calificacion_garante=? ,calificacion_empleado=?  WHERE id_persona=?";            
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido());
            ps.setLong(3, persona.getDni());
            ps.setLong(4, persona.getCuit());
            ps.setString(5, persona.getEmail());
            ps.setLong(6, persona.getTelefono());
            ps.setBoolean(7, persona.getEstado());
            ps.setString(8, persona.getCalificacionInquilino());
            ps.setString(9, persona.getCalificacionPropietario());
            ps.setString(10, persona.getCalificacionGarante());
            ps.setString(11, persona.getCalificacionEmpleado());
            ps.setInt(12, persona.getId());                   
            if (ps.executeUpdate()!= 0) {
                System.out.println("La persona fue modificada con exito");
                result = true;
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al editar los datos de la persona: \n" + e.getMessage());
        }
        return result;

    }

    public Boolean eliminarPersona(Integer id) {
        Boolean result = false;
        try {
            String querySql = "DELETE FROM persona WHERE id_persona=?";
            PreparedStatement ps = conn.prepareStatement(querySql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 0) {
                System.out.println("La persona fue eliminada con exito");
                result = true;
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error al eliminar la persona desde la base de datos: \n" + e.getMessage());
        }
        return result;
    }

}
