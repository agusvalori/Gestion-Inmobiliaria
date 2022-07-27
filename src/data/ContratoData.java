/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import entities.Inmueble;

/**
 *
 * @author agusv
 */
public class ContratoData {

    public Boolean inmuebleAlquilado(Inmueble inmueble) {
        Boolean result = false;
        try {
            String querySql = "SELECT * FROM contrato where id_inmueble=?";
        } catch (Exception e) {
            //TODO: handle exception
        }
        return result;
    }
    
}
