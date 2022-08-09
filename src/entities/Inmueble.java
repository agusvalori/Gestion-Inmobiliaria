/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author agusv
 */
public class Inmueble {
    private Integer id;
    private Propietario propietario;
    private String tipoInmueble;
    private String estadoInmueble;
    private String zona;
    private String direccion;
    private String localidad;
    private String provincia;
    private String caracteristicas;
    private Long montoInicial = 0L;
    private Boolean estado = true;

    // Valores predefinidos para comboBox o Select
    private ArrayList<String> listTipoInmueble = new ArrayList<>();
    private ArrayList<String> listEstadoInmueble = new ArrayList<>();
    private ArrayList<String> listZonaInmueble = new ArrayList<>();    

    public Inmueble() {
    }

    public Inmueble(Integer id, Propietario propietario, String tipoInmueble, String estadoInmueble, String zona,
            String direccion, String localidad, String provincia, String caracteristicas, Long montoInicial,
            Boolean estado) {
        this.id = id;
        this.propietario = propietario;
        this.tipoInmueble = tipoInmueble;
        this.estadoInmueble = estadoInmueble;
        this.zona = zona;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.caracteristicas = caracteristicas;
        this.montoInicial = montoInicial;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public String getEstadoInmueble() {
        return estadoInmueble;
    }

    public String getZona() {
        return zona;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public Long getMontoInicial() {
        return montoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public void setEstadoInmueble(String estadoInmueble) {
        this.estadoInmueble = estadoInmueble;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public void setMontoInicial(Long montoInicial) {
        this.montoInicial = montoInicial;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    //################################

    public ArrayList<String> getListTipoInmueble() {
        listTipoInmueble.add("Casa");
        listTipoInmueble.add("Departamento");
        listTipoInmueble.add("Monoambiente");
        listTipoInmueble.add("Local");
        listTipoInmueble.add("Garage");
        return listTipoInmueble;
    }

    public ArrayList<String> getListEstadoInmueble() {        
        listEstadoInmueble.add("Nueva");
        listEstadoInmueble.add("Reacondicionada Exelente");
        listEstadoInmueble.add("Reacondicionada Buena");
        listEstadoInmueble.add("Reacondicionada Mala");        
        listEstadoInmueble.add("Precaria");        
        return listEstadoInmueble;
    }

    public ArrayList<String> getListZonaInmueble() {
        listZonaInmueble.add("Centro");
        listZonaInmueble.add("La Ribera");
        listZonaInmueble.add("Estacion");
        listZonaInmueble.add("Jardin Del Sur");
        listZonaInmueble.add("Att 2");
        listZonaInmueble.add("1000 Viviendas");
        listZonaInmueble.add("Eva peron 2");
        listZonaInmueble.add("Las Miranda");
        return listZonaInmueble;
    }

    @Override
    public String toString() {        
        return getDireccion();
    }


}
