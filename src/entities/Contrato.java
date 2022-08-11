package entities;

/**
 *
 * @author agusv
 */

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

public class Contrato {

    private Integer id;
    private Inquilino inquilino;
    private Inmueble inmueble;
    private Garante garante;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer duracionMeses;
    private Double montoInicial;
    private Integer aumentosPorcentaje;
    private Integer aumentosPeriodos;
    private Boolean estado=true;
    private String observaciones;

    public Contrato() {
    }

    public Contrato(Integer id, Inquilino inquilino, Inmueble inmueble, Garante garante, LocalDate fechaInicio,
            LocalDate fechaFin, Integer duracionMeses, Double montoInicial, Integer aumentosPorcentaje,
            Integer aumentosPeriodos, Boolean estado, String observaciones) {
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
        this.observaciones = observaciones;
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

    public LocalDate getFechaInicio() {
        
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
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

    public String getObservaciones() {
        return observaciones;
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

    public void setFechaInicio(LocalDate fechaInicio) {        
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
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

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // Validamos el contrato
    public Boolean validarContrato(Inquilino _inquilino, Inmueble _inmueble, Garante _garante, Date _fechaInicio,
            String _duracionMeses, String _montoInicial, String _aumentosPorcentaje,
            String _aumentosPeriodos, String _observaciones) {
        String mensaje = "";

        // Validamos el inquilino
        if (_inquilino != null && _inquilino.getId() != null) {
            setInquilino(_inquilino);
        } else {
            mensaje += "Inquilino: No existe un inquilino\n";
        }

        // Validamos el Inmueble
        if (_inmueble != null && _inmueble.getId() != null) {
            setInmueble(_inmueble);
        } else {
            mensaje += "Inmueble: No existe un inmueble\n";
        }

        // Validamos el Garante
        if (_garante != null && _garante.getId() != null) {
            setGarante(_garante);
        } else {
            setGarante(null);
        }

        // Validamos la fecha de inicio
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate localDate = _fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // Comprobamos que no sea una fecha anterior
            if (!currentDate.isBefore(localDate)) {
                setFechaInicio(localDate);
            } else {
                mensaje += "Fecha: La fecha no puede ser anterior\n";
            }

        } catch (Exception e) {
            mensaje += "Fecha: Fecha invalida\n";
        }

        // Validamos la Duracion en meses del contrato
        try {
            if (Integer.parseInt(_duracionMeses) >= 12) {
                setDuracionMeses(Integer.parseInt(_duracionMeses));
            } else {
                mensaje += "Duracion del contrato: No puede ser menor a 12 meses\n";
            }
        } catch (Exception e) {
            mensaje += "Duracion del contrato: Valores invalidos\n";
        }

        // Validamos la fecha de fin y le sumamos los meses de
        if (getDuracionMeses() >= 12) {
            try {
                setFechaFin(getFechaInicio().plusMonths(getDuracionMeses()));
            } catch (Exception e) {
                mensaje += "Fecha: Fecha invalida\n";
            }
        }

        // Validamos el monto inicial del contrato
        try {
            if (Double.parseDouble(_montoInicial) > 0) {
                setMontoInicial(Double.parseDouble(_montoInicial));
            } else {
                mensaje += "Monto inicial: El monto no puede ser menor a 0\n";
            }

        } catch (Exception e) {
            mensaje += "Monto inicial: Valores invalidos\n";
        }

        // Validamos los aumentos el porcentaje

        try {
            if (Integer.parseInt(_aumentosPorcentaje) > 0 && Integer.parseInt(_aumentosPorcentaje) < 100) {
                setAumentosPorcentaje(Integer.parseInt(_aumentosPorcentaje));
            } else {
                mensaje += "Aumento porcentaje: No puede ser menor a 0 o mayor a 100\n";
            }
        } catch (Exception e) {
            mensaje += "Aumento porcentaje: Valores invalidos\n";
        }

        // Validamos los aumentos los periodos en meses
        try {
            if (Integer.parseInt(_aumentosPeriodos) > 0 && Integer.parseInt(_aumentosPeriodos) <= getDuracionMeses()) {
                setAumentosPeriodos(Integer.parseInt(_aumentosPeriodos));
            } else {
                mensaje += "Aumento periodos: No puede ser menor a 0 o mayor a la duracion del contrato en meses\n";
            }
        } catch (Exception e) {
            mensaje += "Aumento periodos: Valores invalidos\n";
        }

        // Validamos las observaciones
        if (!_observaciones.isBlank()) {
            setObservaciones(_observaciones);
        }

        if (mensaje == "") {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, mensaje);
            return false;
        }

    }

    @Override
    public String toString() {
        return "Inquilino: " + inquilino.toString()
                + "\nInmueble: " + inmueble
                + "\nGarante: " + garante
                + "\nfechaInicio: " + fechaInicio
                + "\nfechaFin: " + fechaFin
                + "\nduracionMeses: " + duracionMeses
                + "\nmontoInicial: " + montoInicial
                + "\naumentosPorcentaje: " + aumentosPorcentaje
                + "\naumentosPeriodos: " + aumentosPeriodos
                + "\nobservaciones: " + observaciones;
    }

}
