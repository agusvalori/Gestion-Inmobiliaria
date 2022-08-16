/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    private InquilinoData inquilinoData;
    private InmuebleData inmuebleData;

    public ContratoData(Conexion conexion) {
        super();
        this.conexion = conexion.getConexion();
        inquilinoData = new InquilinoData(conexion);
        inmuebleData = new InmuebleData(conexion);
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

    public ArrayList<Contrato> obtenerContratos() {
        ArrayList<Contrato> contratoList = new ArrayList<>();

        try {
            String querySql = "SELECT * FROM contrato";
            PreparedStatement ps = conexion.prepareStatement(querySql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Contrato contrato = new Contrato();
                contrato.setId(resultSet.getInt("id_contrato"));
                contrato.setInquilino(inquilinoData.obtenerInquilinosXId(resultSet.getInt("id_inquilino")));
                contrato.setInmueble(inmuebleData.obtenerInmueblesXID(resultSet.getInt("id_inmueble")));
                // contrato.setGarante();
                contrato.setFechaInicio(resultSet.getDate("fecha_inicio").toLocalDate());
                contrato.setDuracionMeses(resultSet.getInt("duracion_meses"));
                contrato.setMontoInicial(resultSet.getDouble("monto_inicial"));
                contrato.setAumentosPorcentaje(resultSet.getInt("aumentos_porcentaje"));
                contrato.setAumentosPeriodos(resultSet.getInt("aumentos_periodo"));
                contrato.setObservaciones(resultSet.getString("observacion"));
                // Obtenemos fecha fin con la fecha de inicio y la duracion
                contrato.setFechaFin(contrato.getFechaInicio().plusMonths(contrato.getDuracionMeses()));

                contratoList.add(contrato);
            }
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conseguir lista de Contratos\n" + e.getMessage());
        }

        return contratoList;
    }

    public Contrato obtenerContratosXId(Integer id) {
        Contrato contrato = new Contrato();

        try {
            String querySql = "SELECT * FROM contrato WHERE id_contrato=?";
            PreparedStatement ps = conexion.prepareStatement(querySql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                contrato.setId(resultSet.getInt("id_contrato"));
                contrato.setInquilino(inquilinoData.obtenerInquilinosXId(resultSet.getInt("id_inquilino")));
                contrato.setInmueble(inmuebleData.obtenerInmueblesXID(resultSet.getInt("id_inmueble")));
                // contrato.setGarante();
                contrato.setFechaInicio(resultSet.getDate("fecha_inicio").toLocalDate());
                contrato.setDuracionMeses(resultSet.getInt("duracion_meses"));
                contrato.setMontoInicial(resultSet.getDouble("monto_inicial"));
                contrato.setAumentosPorcentaje(resultSet.getInt("aumentos_porcentaje"));
                contrato.setAumentosPeriodos(resultSet.getInt("aumentos_periodo"));
                contrato.setObservaciones(resultSet.getString("observacion"));
                // Obtenemos fecha fin con la fecha de inicio y la duracion
                contrato.setFechaFin(contrato.getFechaInicio().plusMonths(contrato.getDuracionMeses()));
            }
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conseguir lista de Contratos\n" + e.getMessage());
        }

        return contrato;
    }


    

    public Boolean editarContrato(Contrato contrato) {
        Boolean result = false;

        try {
            String querySql;
            //Vemos si es un contrato con o sin garantes.
            if (contrato.getGarante() != null) {
                querySql = "UPDATE contrato SET id_inquilino=?, id_inmueble=?,fecha_inicio=?,duracion_meses=?,monto_inicial=?,aumentos_porcentaje=?, aumentos_periodo=?,estado=?,observacion=?,id_garante=?  WHERE id_contrato=?";
            } else {
                querySql = "UPDATE contrato SET id_inquilino=?, id_inmueble=?,fecha_inicio=?,duracion_meses=?,monto_inicial=?,aumentos_porcentaje=?, aumentos_periodo=?,estado=?,observacion=? WHERE id_contrato=? ";
            }

            PreparedStatement ps = conexion.prepareStatement(querySql);
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
                ps.setInt(11, contrato.getId());
            }else{
                ps.setInt(10, contrato.getId());
            }

            if (ps.executeUpdate()!=0) {                
                result = true;
                JOptionPane.showMessageDialog(null, "El contrato fue editado con exito");
            }
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puedo realizar el contrato \n" + e.getMessage(),
                    "Error al realizar el contrato", JOptionPane.WARNING_MESSAGE);
        }

        return result;
    }


    public Boolean eliminarContrato(Integer id) {
        Boolean result = false;
        try {
            String querySql = "DELETE FROM contrato WHERE id_contrato=?";
            PreparedStatement ps = conexion.prepareStatement(querySql);
            ps.setInt(1, id);
            if (ps.executeUpdate() != 0) {                
                result = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el contrato: "+e.getMessage(), "Error al eliminar el contrato", JOptionPane.WARNING_MESSAGE);
        }
        return result;
        
    }

}
