/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.Conexion;
import data.ContratoData;
import data.InmuebleData;
import data.InquilinoData;
import data.PropietarioData;
import entities.Contrato;
import entities.Garante;
import entities.Inmueble;
import entities.Inquilino;
import java.time.ZoneId;

/**
 *
 * @author agusv
 */
public class ContratoDialogView extends javax.swing.JDialog {

    private ContratoData contratoData;
    private InmuebleData inmuebleData;
    private InquilinoData inquilinoData;
    private PropietarioData propietarioData;

    private Inquilino inquilino = new Inquilino();
    private Inmueble inmueble = new Inmueble();
    private Contrato contrato = new Contrato();
    private Garante garante = new Garante();

    private DefaultComboBoxModel modelComboBox;

    public ContratoDialogView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Inmueble inmueble = new Inmueble();
        limpiarInmueble();

    }

    public ContratoDialogView(java.awt.Frame parent, boolean modal, Conexion conexion) {
        super(parent, modal);
        initComponents();
        inmuebleData = new InmuebleData(conexion);
        inquilinoData = new InquilinoData(conexion);
        contratoData = new ContratoData(conexion);
        propietarioData = new PropietarioData(conexion);
        Inmueble inmueble = new Inmueble();
        limpiarInmueble();
        limpiarContrato();
    }

    public ContratoDialogView(java.awt.Frame parent, boolean modal, Conexion conexion, Contrato contrato) {
        super(parent, modal);
        initComponents();
        inmuebleData = new InmuebleData(conexion);
        inquilinoData = new InquilinoData(conexion);
        contratoData = new ContratoData(conexion);
        propietarioData = new PropietarioData(conexion);

        this.contrato = contrato;
        obtenerContrato();

    }

    private void obtenerInquilino() {
        habilitarContrato();
        txfInquilinoId.setText(contrato.getInquilino().getId().toString());
        txfInquilinoDni.setText(contrato.getInquilino().getPersona().getDni().toString());
        txfInquilinoNombre.setText(contrato.getInquilino().getPersona().getNombre());
        txfInquilinoApellido.setText(contrato.getInquilino().getPersona().getApellido());
        txfInquilinoCalificacion.setText(contrato.getInquilino().getPersona().getCalificacionInquilino());
    }

    private void obtenerInmueble() {

        txfInmuebleId.setText(contrato.getInmueble().getId().toString());

        // Inmueble Zona
        modelComboBox = new DefaultComboBoxModel(inmueble.getListZonaInmueble().toArray());
        selectInmuebleZona.setModel(modelComboBox);
        selectInmuebleZona.setSelectedItem(contrato.getInmueble().getZona());
        selectInmuebleZona.setEnabled(false);

        // Inmueble Tipo
        modelComboBox = new DefaultComboBoxModel(inmueble.getListTipoInmueble().toArray());
        selectInmuebleTipo.setModel(modelComboBox);
        selectInmuebleTipo.setSelectedItem(contrato.getInmueble().getTipoInmueble());
        selectInmuebleTipo.setEnabled(false);

        // Inmueble Direccion
        String[] direccion = { contrato.getInmueble().getDireccion() };
        modelComboBox = new DefaultComboBoxModel(direccion);
        selectInmuebleDireccion.setModel(modelComboBox);
        selectInmuebleDireccion.setEnabled(false);

        // Inmueble monto
        txfInmuebleMonto.setText(contrato.getInmueble().getMontoInicial().toString());

        // Inmueble Datos Propietario
        txtDatosPropietario.setText(contrato.getInmueble().getPropietario().getPersona().getNombre() + " "
                + contrato.getInmueble().getPropietario().getPersona().getApellido() + " \n"
                + "DNI: " + contrato.getInmueble().getPropietario().getPersona().getDni() + " \n"
                + "Calificacion: "
                + contrato.getInmueble().getPropietario().getPersona().getCalificacionPropietario()
                + " \n");

        habilitarContrato();
    }

    private void obtenerContrato() {

        txfContratoId.setText(contrato.getId().toString());
        // Obtenemos fecha inicial del contrato
        ZoneId defaultZoneId = ZoneId.systemDefault();
        dcContratoFechaInicio.setDate(Date.from(contrato.getFechaInicio().atStartOfDay(defaultZoneId).toInstant()));

        txfContratoDuracion.setText(contrato.getDuracionMeses().toString());
        txfContratoAumento.setText(contrato.getAumentosPorcentaje().toString());
        txfContratoAumentoPeriodo.setText(contrato.getAumentosPeriodos().toString());
        txaContratoObservaciones.setText(contrato.getObservaciones());

        obtenerInmueble();
        obtenerInquilino();
    }

    private void limpiarInquilino() {
        contrato.setInquilino(null);

        txfInquilinoId.setText("");
        txfInquilinoNombre.setText("");
        txfInquilinoApellido.setText("");
        txfInquilinoCalificacion.setText("");

        habilitarContrato();
    }

    private void limpiarInmueble() {
        inmueble = new Inmueble();
        contrato.setInmueble(null);
        habilitarContrato();

        txfInmuebleId.setText("");
        txfInmuebleMonto.setText("");
        txtDatosPropietario.setText("");

        selectInmuebleZona.setEnabled(true);
        selectInmuebleTipo.setEnabled(true);
        selectInmuebleDireccion.setEnabled(true);

        // Inmueble Zona
        modelComboBox = new DefaultComboBoxModel(inmueble.getListZonaInmueble().toArray());
        selectInmuebleZona.setModel(modelComboBox);

        // Inmueble Tipo
        modelComboBox = new DefaultComboBoxModel(inmueble.getListTipoInmueble().toArray());
        selectInmuebleTipo.setModel(modelComboBox);

        // Direccion
        modelComboBox = new DefaultComboBoxModel(inmuebleData
                .obtenerInmueblesNoAlquiladosXTipoYZona(
                        String.valueOf(selectInmuebleTipo.getSelectedItem()),
                        String.valueOf(selectInmuebleZona.getSelectedItem()))
                .toArray());
        selectInmuebleDireccion.setModel(modelComboBox);

    }

    private void limpiarContrato() {
        Date date = new Date();
        dcContratoFechaInicio.setDate(date);
        txfContratoDuracion.setText("");
        txfContratoAumento.setText("");
        txfContratoAumentoPeriodo.setText("");
        txaContratoObservaciones.setText("");

        habilitarContrato();
    }

    private void habilitarContrato() {
        if (contrato.getInmueble() != null && contrato.getInquilino() != null) {
            btnBorrarContrato.setEnabled(true);
            btnLimpiarContrato.setEnabled(true);
            btnGuardarContrato.setEnabled(true);
            txfContratoAumento.setEnabled(true);
            txfContratoAumentoPeriodo.setEnabled(true);
            txfContratoDuracion.setEnabled(true);
            dcContratoFechaInicio.setEnabled(true);
        } else {
            txfContratoAumento.setEnabled(false);
            txfContratoAumentoPeriodo.setEnabled(false);
            txfContratoDuracion.setEnabled(false);
            dcContratoFechaInicio.setEnabled(false);
            btnBorrarContrato.setEnabled(false);
            btnLimpiarContrato.setEnabled(false);
            btnGuardarContrato.setEnabled(false);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        selectCalificacionInquilino = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txfInmuebleId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txfInmuebleMonto = new javax.swing.JTextField();
        selectInmuebleTipo = new javax.swing.JComboBox<>();
        selectInmuebleZona = new javax.swing.JComboBox<>();
        selectInmuebleDireccion = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDatosPropietario = new javax.swing.JTextPane();
        btnLimpiarInmueble = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txfInquilinoId = new javax.swing.JTextField();
        txfInquilinoDni = new javax.swing.JTextField();
        txfInquilinoNombre = new javax.swing.JTextField();
        txfInquilinoApellido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txfInquilinoCalificacion = new javax.swing.JTextField();
        btnLimpiarInquilino = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txfContratoId = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txfContratoDuracion = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        dcContratoFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        txfContratoAumento = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txfContratoAumentoPeriodo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnGuardarContrato = new javax.swing.JButton();
        btnLimpiarContrato = new javax.swing.JButton();
        btnBorrarContrato = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaContratoObservaciones = new javax.swing.JTextPane();

        jLabel10.setText("Inquilino:");

        selectCalificacionInquilino.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "Excelente", "Buena", "Mala", "Ninguna" }));
        selectCalificacionInquilino.setEnabled(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Inmueble");

        txfInmuebleId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfInmuebleIdActionPerformed(evt);
            }
        });

        jLabel8.setText("DIRECCION:");

        jLabel12.setText("ID:");

        jLabel13.setText("TIPO:");

        jLabel15.setText("ZONA:");

        jLabel19.setText("MONTO:");

        txfInmuebleMonto.setEnabled(false);

        selectInmuebleTipo.setEditable(true);
        selectInmuebleTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectInmuebleTipoActionPerformed(evt);
            }
        });

        selectInmuebleZona.setEnabled(false);
        selectInmuebleZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectInmuebleZonaActionPerformed(evt);
            }
        });

        selectInmuebleDireccion.setEnabled(false);
        selectInmuebleDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectInmuebleDireccionActionPerformed(evt);
            }
        });

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Datos del Propietario");

        txtDatosPropietario.setEditable(false);
        jScrollPane1.setViewportView(txtDatosPropietario);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel5Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jLabel7)
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap()));
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                .addContainerGap()));

        btnLimpiarInmueble.setText("Limpiar");
        btnLimpiarInmueble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarInmuebleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout
                                                .createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(selectInmuebleZona, 0,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout
                                                .createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(selectInmuebleTipo, 0,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout
                                                .createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txfInmuebleMonto)
                                                        .addComponent(selectInmuebleDireccion, 0,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout
                                                .createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                jPanel2Layout.createSequentialGroup()
                                                                        .addComponent(jLabel12,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                75,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(txfInmuebleId,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                120,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel1,
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                jPanel2Layout.createSequentialGroup()
                                                                        .addGap(61, 61, 61)
                                                                        .addComponent(btnLimpiarInmueble,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                73,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap()));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(txfInmuebleId, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(selectInmuebleTipo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(selectInmuebleZona, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(selectInmuebleDireccion, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfInmuebleMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11,
                                        Short.MAX_VALUE)
                                .addComponent(btnLimpiarInmueble)
                                .addContainerGap()));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Inquilino");

        jLabel3.setText("ID:");

        jLabel4.setText("Dni:");

        jLabel5.setText("Nombre:");

        jLabel6.setText("Apellido:");

        txfInquilinoId.setEnabled(false);

        txfInquilinoDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfInquilinoDniActionPerformed(evt);
            }
        });

        txfInquilinoNombre.setEnabled(false);

        txfInquilinoApellido.setEnabled(false);

        jLabel11.setText("Calificacion:");

        txfInquilinoCalificacion.setEnabled(false);

        btnLimpiarInquilino.setText("Limpiar");
        btnLimpiarInquilino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarInquilinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6))
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(txfInquilinoId,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txfInquilinoApellido,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(txfInquilinoDni,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txfInquilinoNombre,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addContainerGap(23, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel2)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnLimpiarInquilino,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 73,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txfInquilinoCalificacion,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)))));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfInquilinoId, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfInquilinoDni, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfInquilinoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(txfInquilinoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txfInquilinoCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiarInquilino)
                                .addContainerGap()));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Contrato");

        txfContratoId.setEnabled(false);

        jLabel17.setText("ID:");

        jLabel18.setText("Duracion:");

        txfContratoDuracion.setEnabled(false);
        txfContratoDuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfContratoDuracionActionPerformed(evt);
            }
        });

        jLabel21.setText("Fecha Inicio");

        jLabel22.setText("Aumentos:");

        txfContratoAumento.setEnabled(false);

        jLabel23.setText("%");

        txfContratoAumentoPeriodo.setEnabled(false);

        jLabel9.setText("meses");

        jLabel14.setText("meses");

        jLabel25.setText("Cada:");

        btnGuardarContrato.setText("Guardar");
        btnGuardarContrato.setEnabled(false);
        btnGuardarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarContratoActionPerformed(evt);
            }
        });

        btnLimpiarContrato.setText("Limpiar");
        btnLimpiarContrato.setEnabled(false);
        btnLimpiarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarContratoActionPerformed(evt);
            }
        });

        btnBorrarContrato.setText("Borrar");
        btnBorrarContrato.setEnabled(false);
        btnBorrarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarContratoActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txaContratoObservaciones);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel16)
                                                .addGap(101, 101, 101))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                                .addGap(12, 12, 12)
                                                                                .addComponent(jLabel18)
                                                                                .addGap(18, 18, 18))
                                                                        .addGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                jPanel4Layout.createSequentialGroup()
                                                                                        .addComponent(jLabel17,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGap(19, 19, 19)))
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txfContratoId,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(dcContratoFechaInicio,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                                .addComponent(txfContratoDuracion,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        82,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel9,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        45,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel22,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel25,
                                                                                javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(txfContratoAumentoPeriodo,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                81,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txfContratoAumento,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                81,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel14,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                45,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel23))
                                                                .addGap(13, 13, 13)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel21)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(btnGuardarContrato,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 73,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnLimpiarContrato,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 73,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        8, Short.MAX_VALUE)
                                                                .addComponent(btnBorrarContrato,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 73,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jScrollPane2)
                                                .addContainerGap()))));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfContratoId, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(dcContratoFechaInicio,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel4Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txfContratoDuracion,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel18)
                                                        .addComponent(jLabel9))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfContratoAumento, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txfContratoAumentoPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnGuardarContrato)
                                        .addComponent(btnLimpiarContrato)
                                        .addComponent(btnBorrarContrato))
                                .addContainerGap()));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarContratoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLimpiarContratoActionPerformed
        limpiarContrato();
    }// GEN-LAST:event_btnLimpiarContratoActionPerformed

    private void btnLimpiarInquilinoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLimpiarInquilinoActionPerformed
        limpiarInquilino();
    }// GEN-LAST:event_btnLimpiarInquilinoActionPerformed

    private void btnLimpiarInmuebleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLimpiarInmuebleActionPerformed
        limpiarInmueble();
    }// GEN-LAST:event_btnLimpiarInmuebleActionPerformed

    private void btnGuardarContratoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarContratoActionPerformed
        Date _fechaInicio = dcContratoFechaInicio.getDate();
        String _duracionMeses = txfContratoDuracion.getText();
        String _montoInicial = txfInmuebleMonto.getText();
        String _aumentosPorcentaje = txfContratoAumento.getText();
        String _aumentosPeriodos = txfContratoAumentoPeriodo.getText();
        String _observaciones = txaContratoObservaciones.getText();
        if (contrato.getId() == null) {
            // Guardamos un nuevo contrato

            if (contrato.validarContrato(contrato.getInquilino(), contrato.getInmueble(), contrato.getGarante(),
                    _fechaInicio, _duracionMeses, _montoInicial, _aumentosPorcentaje, _aumentosPeriodos,
                    _observaciones)) {
                if (contratoData.agregarContrato(contrato)) {
                    JOptionPane.showMessageDialog(null, "El contrato entre :\nInquilino: "
                            + contrato.getInquilino().getPersona().getNombre()
                            + " " + contrato.getInquilino().getPersona().getApellido()
                            + "\nPropietario: " + contrato.getInmueble().getPropietario().getPersona().getNombre()
                            + " " + contrato.getInmueble().getPropietario().getPersona().getApellido()
                            + "\nSe realizo con EXITO");
                            obtenerContrato();
                }
            }
        } else {
            // Editamos el contrato
            int result = JOptionPane.showConfirmDialog(null,
                    "Esta seguro que desea editar el contrato ID: " + contrato.getId(), "Editar Contrato",
                    JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                if (contrato.validarContrato(contrato.getInquilino(), contrato.getInmueble(), contrato.getGarante(),
                        _fechaInicio, _duracionMeses, _montoInicial, _aumentosPorcentaje, _aumentosPeriodos,
                        _observaciones)) {
                    contratoData.editarContrato(contrato);
                    dispose();
                }
            }
        }
    }// GEN-LAST:event_btnGuardarContratoActionPerformed

    private void btnBorrarContratoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBorrarContratoActionPerformed
        if (contrato.getId() != null) {
            int result = JOptionPane.showConfirmDialog(null,
                    "Esta seguro que desea eliminar el contrato ID: " + contrato.getId(), "Eliminar Contrato",
                    JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                contratoData.eliminarContrato(contrato.getId());
                dispose();
            }
        }

    }// GEN-LAST:event_btnBorrarContratoActionPerformed

    private void txfInmuebleIdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txfInmuebleIdActionPerformed
        if (!txfInmuebleId.getText().isBlank()) {
            try {
                contrato.setInmueble(inmuebleData
                        .obtenerInmueblesXID(Integer.parseInt(txfInmuebleId.getText())));
                if (contrato.getInmueble() != null) {
                    // Inmueble Encontrado
                    System.out.println("Inmueble encontrado");
                    obtenerInmueble();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el inmueble");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ingrese un ID valido " + e.getMessage());
            }

        }
    }// GEN-LAST:event_txfInmuebleIdActionPerformed

    private void selectInmuebleTipoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_selectInmuebleTipoActionPerformed

        try {
            if (contrato.getInmueble() == null) {
                // JOptionPane.showMessageDialog(null, "Tipo seleccionado");
                selectInmuebleZona.setEnabled(true);

                // Mostramos direcciones de acuerdo al tipo de inmueble
                selectInmuebleDireccion.setEnabled(true);

                modelComboBox = new DefaultComboBoxModel(inmuebleData
                        .obtenerInmueblesNoAlquiladosXTipoYZona(
                                String.valueOf(selectInmuebleTipo.getSelectedItem()),
                                String.valueOf(selectInmuebleZona.getSelectedItem()))
                        .toArray());
                selectInmuebleDireccion.setModel(modelComboBox);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar un inmueble del tipo: "
                    + selectInmuebleTipo.getSelectedItem() + "\n" + e.getMessage());
        }
    }// GEN-LAST:event_selectInmuebleTipoActionPerformed

    private void selectInmuebleZonaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_selectInmuebleZonaActionPerformed
        if (contrato.getInmueble() == null) {
            // JOptionPane.showMessageDialog(null, "Tipo seleccionado");
            selectInmuebleZona.setEnabled(true);

            // Mostramos direcciones de acuerdo al tipo de inmueble
            selectInmuebleDireccion.setEnabled(true);

            modelComboBox = new DefaultComboBoxModel(inmuebleData
                    .obtenerInmueblesNoAlquiladosXTipoYZona(
                            String.valueOf(selectInmuebleTipo.getSelectedItem()),
                            String.valueOf(selectInmuebleZona.getSelectedItem()))
                    .toArray());
            selectInmuebleDireccion.setModel(modelComboBox);

        }

    }// GEN-LAST:event_selectInmuebleZonaActionPerformed

    private void selectInmuebleDireccionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_selectInmuebleDireccionActionPerformed
        contrato.setInmueble((Inmueble) selectInmuebleDireccion.getSelectedItem());
        obtenerInmueble();
    }// GEN-LAST:event_selectInmuebleDireccionActionPerformed

    private void txfInquilinoDniActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txfInquilinoDniActionPerformed
        if (!txfInquilinoDni.getText().isBlank()) {
            try {
                contrato.setInquilino(inquilinoData
                        .obtenerInquilinosXDni(Long.parseLong(txfInquilinoDni.getText())));
                if (contrato.getInquilino().getId() != null) {
                    obtenerInquilino();
                } else {
                    limpiarInquilino();
                }
            } catch (Exception e) {
                limpiarInquilino();
                JOptionPane.showMessageDialog(null, "ingrese un dni valido\n" + e.getMessage());
            }

        } else {
            limpiarInquilino();
            JOptionPane.showMessageDialog(null, "ingrese un dni valido");
        }
    }// GEN-LAST:event_txfInquilinoDniActionPerformed

    private void txfContratoDuracionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txfContratoDuracionActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txfContratoDuracionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContratoDialogView.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContratoDialogView.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContratoDialogView.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContratoDialogView.class.getName()).log(
                    java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ContratoDialogView dialog = new ContratoDialogView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrarContrato;
    private javax.swing.JButton btnGuardarContrato;
    private javax.swing.JButton btnLimpiarContrato;
    private javax.swing.JButton btnLimpiarInmueble;
    private javax.swing.JButton btnLimpiarInquilino;
    private com.toedter.calendar.JDateChooser dcContratoFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> selectCalificacionInquilino;
    private javax.swing.JComboBox<Inmueble> selectInmuebleDireccion;
    private javax.swing.JComboBox<String> selectInmuebleTipo;
    private javax.swing.JComboBox<String> selectInmuebleZona;
    private javax.swing.JTextPane txaContratoObservaciones;
    private javax.swing.JTextField txfContratoAumento;
    private javax.swing.JTextField txfContratoAumentoPeriodo;
    private javax.swing.JTextField txfContratoDuracion;
    private javax.swing.JTextField txfContratoId;
    private javax.swing.JTextField txfInmuebleId;
    private javax.swing.JTextField txfInmuebleMonto;
    private javax.swing.JTextField txfInquilinoApellido;
    private javax.swing.JTextField txfInquilinoCalificacion;
    private javax.swing.JTextField txfInquilinoDni;
    private javax.swing.JTextField txfInquilinoId;
    private javax.swing.JTextField txfInquilinoNombre;
    private javax.swing.JTextPane txtDatosPropietario;
    // End of variables declaration//GEN-END:variables
}
