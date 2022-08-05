package entities;
/**
 *
 * @author agusv
 */

import java.util.Date;

public class Contrato {

    private Integer id;
    private Inquilino inquilino;
    private Inmueble inmueble;
    private Garante garante;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer duracionMeses;
    private Double montoInicial;
    private Integer aumentosPorcentaje;
    private Integer aumentosPeriodos;
    private Boolean estado;    

    public Contrato() {
    }

    public Contrato(Integer id, Inquilino inquilino, Inmueble inmueble, Garante garante, Date fechaInicio, Date fechaFin,Integer duracionMeses, Double montoInicial, Integer aumentosPorcentaje, Integer aumentosPeriodos, Boolean estado) {
        this.id = id;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.garante = garante;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracionMeses = duracionMeses;
        this.montoInicial = montoInicial;
        this.aumentosPorcentaje = aumentosPorcentaje;
        this.aumentosPeriodos = aumentosPeriodos;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public Garante getGarante() {
        return garante;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public Integer getDuracionMeses() {
        return duracionMeses;
    }

    public Double getMontoInicial() {
        return montoInicial;
    }

    public Integer getAumentosPorcentaje() {
        return aumentosPorcentaje;
    }

    public Integer getAumentosPeriodos() {
        return aumentosPeriodos;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public void setGarante(Garante garante) {
        this.garante = garante;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDuracionMeses(Integer duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public void setMontoInicial(Double montoInicial) {
        this.montoInicial = montoInicial;
    }

    public void setAumentosPorcentaje(Integer aumentosPorcentaje) {
        this.aumentosPorcentaje = aumentosPorcentaje;
    }

    public void setAumentosPeriodos(Integer aumentosPeriodos) {
        this.aumentosPeriodos = aumentosPeriodos;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
}
