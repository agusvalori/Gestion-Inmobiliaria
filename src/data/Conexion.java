/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author agusv
 */
public class Conexion {
    
    private final String url = "localhost";
    private final String nombreDB = "gestion-inmobiliaria";
    private final String usuario = "root";
    private final String password = "ewqewq321";
    private Connection establecer = null;

    public Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException exc) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DRIVER DE BD\n" + exc.toString());
            System.out.println("ERROR AL CARGAR DRIVER DE BD\n" + exc.toString());
        }
    }

    public Connection getConexion() {
        try {
            if (this.establecer == null) {
                //Establece conexion a db a traves del DriverManager para MariaDB
                this.establecer = DriverManager
                        .getConnection("jdbc:mysql://" + url + "/" + nombreDB + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
                                + "&user=" + usuario + "&password=" + password);
                System.out.println("Base de datos conectada");
            }
        } catch (SQLException exc) {
            if ("class java.sql.SQLInvalidAuthorizationSpecException".equals(exc.getClass().toString())) {
                JOptionPane.showMessageDialog(null,"Pass o User de la base de datos invalido", "ERROR AL CONECTARSE A LA BASE DE DATOS" ,JOptionPane.ERROR_MESSAGE);
                System.out.println("ERROR AL CONECTARSE A LA BASE DE DATOS\n"+"Pass o User de la base de datos invalido");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR AL CONECTARSE A LA BASE DE DATOS\n" + exc.toString());
                System.out.println("ERROR AL CONECTARSE A LA BASE DE DATOS\n" + exc.toString());
            }
        }
        return this.establecer;
    }
}
