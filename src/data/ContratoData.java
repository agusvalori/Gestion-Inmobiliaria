/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import entities.Contrato;
import entities.Inmueble;

/**
 *
 * @author agusv
 */
public class ContratoData {

    private Connection conexion = null;

    public ContratoData(Conexion conexion) {
        super();
        this.conexion = conexion.getConexion();
    }

    public Boolean agregarContrato(Contrato contrato) {
        Boolean result = false;

        try {
            String querySql;
            if (contrato.getGarante() != null) {
                querySql = "INSERT INTO contrato (id_inquilino, id_inmueble,fecha_inicio,duracion_meses,monto_inicial,aumentos_porcentaje, aumentos_periodo,estado,observacion,id_garante) VALUES (?,?,?,?,?,?,?,?,?,?)";
            } else {
                querySql = "INSERT INTO contrato (id_inquilino, id_inmueble,fecha_inicio,duracion_meses,monto_inicial,aumentos_porcentaje, aumentos_periodo,estado,observacion) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            PreparedStatement ps = conexion.prepareStatement(querySql, RETURN_GENERATED_KEYS);
            ps.setInt(1, contrato.getInquilino().getId());
            ps.setInt(2, contrato.getInmueble().getId());
            
            ps.setDate(3, Date.valueOf(contrato.getFechaInicio()));
            ps.setInt(4, contrato.getDuracionMeses());
            ps.setDouble(5, contrato.getMontoInicial());
            ps.setInt(6, contrato.getAumentosPorcentaje());
            ps.setInt(7, contrato.getAumentosPeriodos());
            ps.setBoolean(8, contrato.getEstado());
            ps.setString(9, contrato.getObservaciones());
            if (contrato.getGarante() != null) {
                ps.setInt(10, contrato.getGarante().getId());
            }
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                contrato.setId(resultSet.getInt(1));
                result = true;
            }
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puedo realizar el contrato \n" + e.getMessage(),
                    "Error al realizar el contrato", JOptionPane.WARNING_MESSAGE);
        }

        return result;
    }

}
