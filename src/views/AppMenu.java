/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import data.Conexion;
import data.ContratoData;
import data.EmpleadoData;
import data.InmuebleData;
import data.InquilinoData;
import data.PropietarioData;
import entities.Contrato;
import entities.Empleado;
import entities.Inmueble;
import entities.Inquilino;
import entities.Propietario;
import javax.swing.JOptionPane;

/**
 *
 * @author agusv
 */
public class AppMenu extends javax.swing.JFrame {

    private Conexion conexion = new Conexion();
    private InquilinoData inquilinoData;
    private PropietarioData propietarioData;
    private InmuebleData inmuebleData;
    private EmpleadoData empleadoData;
    private ContratoData contratoData;

    // Cargamos nuevos datos a la tabla
    ArrayList<Inmueble> inmuebleList = new ArrayList<>();
    

    

    private DefaultTableModel tableModel = new DefaultTableModel();

    public AppMenu() {
        initComponents();
        inquilinoData = new InquilinoData(conexion);
        propietarioData = new PropietarioData(conexion);
        inmuebleData = new InmuebleData(conexion);
        empleadoData = new EmpleadoData(conexion);
        contratoData = new ContratoData(conexion);

        inmuebleList = inmuebleData.obtenerInmuebles();
        cargarTablas();
        cargarInicio();
    }

    private void cargarInicio(){
        lblInicioInmueblesTotal.setText(String.valueOf(inmuebleData.obtenerInmuebles().size()));
        lblInicioInmueblesAlquilados.setText(String.valueOf(inmuebleData.obtenerInmueblesAlquilados().size()));
        lblInicioInmueblesLibres.setText(String.valueOf(inmuebleData.obtenerInmueblesNoAlquilados().size()));
    }

    private void cargarTablas() {
        cargarTablaInquilinos();
        cargarTablaPropietarios();
        cargarTablaInmuebles();
        cargarTablaEmpleados();
        cargarTablaContratos();
    }

    private void cargarTablaInquilinos() {
        DefaultTableModel tableModel = (DefaultTableModel) tablaInquilinos.getModel();
        // Limpiamos la tabla
        int filas = tablaInquilinos.getRowCount();
        for (int i = 0; filas > i; i++) {
            tableModel.removeRow(0);
        }

        // Cargamos nuevos datos a la tabla
        ArrayList<Inquilino> inquilinoList = new ArrayList<>();
        inquilinoList = inquilinoData.obtenerInquilinos();
        for (Inquilino inquilinoAux : inquilinoList) {
            tableModel
                    .insertRow(inquilinoList.indexOf(inquilinoAux), new Object[] {
                            inquilinoAux.getId(),
                            inquilinoAux.getPersona().getNombre(),
                            inquilinoAux.getPersona().getApellido(),
                            inquilinoAux.getPersona().getDni(),
                            "VER" });
        }
    }

    private void cargarTablaPropietarios() {
        DefaultTableModel tableModel = (DefaultTableModel) tablaPropietarios.getModel();
        // Limpiamos la tabla
        int filas = tablaPropietarios.getRowCount();
        for (int i = 0; filas > i; i++) {
            tableModel.removeRow(0);
        }

        // Cargamos nuevos datos a la tabla
        ArrayList<Propietario> propietarioList = new ArrayList<>();
        propietarioList = propietarioData.obtenerPropietarios();
        for (Propietario propietarioAux : propietarioList) {
            tableModel
                    .insertRow(propietarioList.indexOf(propietarioAux), new Object[] {
                            propietarioAux.getId(),
                            propietarioAux.getPersona().getNombre(),
                            propietarioAux.getPersona().getApellido(),
                            propietarioAux.getPersona().getDni(),
                            "VER" });
        }

    }

    private void cargarTablaInmuebles() {
        DefaultTableModel tableModel = (DefaultTableModel) tablaInmuebles.getModel();
        // Limpiamos la tabla
        int filas = tablaInmuebles.getRowCount();
        for (int i = 0; filas > i; i++) {
            tableModel.removeRow(0);
        }

        
        for (Inmueble inmuebleAux : inmuebleList) {
            tableModel
                    .insertRow(inmuebleList.indexOf(inmuebleAux), new Object[] {
                            inmuebleAux.getId(),
                            inmuebleAux.getDireccion(),
                            inmuebleAux.getZona(),
                            inmuebleAux.getLocalidad(),
                            inmuebleAux.getProvincia(),
                            inmuebleAux.getPropietario().getPersona().getNombre() + " "
                                    + inmuebleAux.getPropietario().getPersona().getApellido(),
                            "VER" });
        }
    }

    private void cargarTablaEmpleados() {
        DefaultTableModel tableModel = (DefaultTableModel) tablaEmpleados.getModel();
        // Limpiamos la tabla
        int filas = tablaEmpleados.getRowCount();
        for (int i = 0; filas > i; i++) {
            tableModel.removeRow(0);
        }

        // Cargamos nuevos datos a la tabla
        ArrayList<Empleado> empleadoList = new ArrayList<>();
        empleadoList = empleadoData.obtenerEmpleados();

        for (Empleado empleadoAux : empleadoList) {
            tableModel
                    .insertRow(empleadoList.indexOf(empleadoAux), new Object[] {
                            empleadoAux.getId(),
                            empleadoAux.getPersona().getNombre(),
                            empleadoAux.getPersona().getApellido(),
                            empleadoAux.getPersona().getDni(),
                            empleadoAux.getCargo(),
                            "VER" });
        }
    }

    private void cargarTablaContratos() {
        DefaultTableModel tableModel = (DefaultTableModel) tablaContratos.getModel();
        // Limpiamos la tabla
        int filas = tablaContratos.getRowCount();
        for (int i = 0; filas > i; i++) {
            tableModel.removeRow(0);
        }

        // Cargamos nuevos datos a la tabla
        ArrayList<Contrato> contratoList = new ArrayList<>();
        contratoList = contratoData.obtenerContratos();

        //Fomateamos la fecha        
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-YYYY");    

        for (Contrato contratoAux : contratoList) {
            tableModel
                    .insertRow(contratoList.indexOf(contratoAux), new Object[] {
                            contratoAux.getId(),
                            contratoAux.getInmueble().getDireccion(),
                            contratoAux.getInmueble().getPropietario().getPersona().getNombre()
                                    + " " + contratoAux.getInmueble().getPropietario().getPersona().getApellido(),
                            contratoAux.getInquilino().getPersona().getNombre()
                                    + " " + contratoAux.getInquilino().getPersona().getApellido(),
                            formatoFecha.format(contratoAux.getFechaInicio()),
                            formatoFecha.format(contratoAux.getFechaFin()),
                            "VER" });
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrupInmueble = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabInicio = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblInicioInmueblesTotal = new javax.swing.JLabel();
        lblInicioInmueblesAlquilados = new javax.swing.JLabel();
        lblInicioInmueblesLibres = new javax.swing.JLabel();
        tabInmueble = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaInmuebles = new javax.swing.JTable();
        btnAgregarInmueble = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rbtnInmuebleTodos = new javax.swing.JRadioButton();
        rbtnInmuebleAlquilados = new javax.swing.JRadioButton();
        rbtnInmuebleLibres = new javax.swing.JRadioButton();
        tabInquilino = new javax.swing.JPanel();
        btnAgregarInquilinos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInquilinos = new javax.swing.JTable();
        tabContrato = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaContratos = new javax.swing.JTable();
        btnRealizarContrato = new javax.swing.JButton();
        tabEmpleados = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        btnAgregarEmpleados = new javax.swing.JButton();
        tabPropietario = new javax.swing.JPanel();
        btnAgregarPropietario = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPropietarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel2.setText("Inmobiliaria Villa Mercedes");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(217, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Libres:");

        jLabel5.setText("Alquilados:");

        jLabel4.setText("Total:");

        jLabel3.setText("Inmuebles");

        lblInicioInmueblesTotal.setText("0");

        lblInicioInmueblesAlquilados.setText("0");

        lblInicioInmueblesLibres.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblInicioInmueblesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblInicioInmueblesAlquilados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblInicioInmueblesLibres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInicioInmueblesAlquilados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblInicioInmueblesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInicioInmueblesLibres, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabInicioLayout = new javax.swing.GroupLayout(tabInicio);
        tabInicio.setLayout(tabInicioLayout);
        tabInicioLayout.setHorizontalGroup(
            tabInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabInicioLayout.setVerticalGroup(
            tabInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inicio", tabInicio);

        tablaInmuebles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DIRECCION", "ZONA", "LOCALIDAD", "PROVINCIA", "PROPIETARIO", "VER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaInmuebles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInmueblesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaInmuebles);
        if (tablaInmuebles.getColumnModel().getColumnCount() > 0) {
            tablaInmuebles.getColumnModel().getColumn(6).setResizable(false);
        }

        btnAgregarInmueble.setText("Agregar Inmueble");
        btnAgregarInmueble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarInmuebleActionPerformed(evt);
            }
        });

        jLabel1.setText("Mostrar:");

        btnGrupInmueble.add(rbtnInmuebleTodos);
        rbtnInmuebleTodos.setSelected(true);
        rbtnInmuebleTodos.setText("Todos");
        rbtnInmuebleTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInmuebleTodosActionPerformed(evt);
            }
        });

        btnGrupInmueble.add(rbtnInmuebleAlquilados);
        rbtnInmuebleAlquilados.setText("Alquilados");
        rbtnInmuebleAlquilados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInmuebleAlquiladosActionPerformed(evt);
            }
        });

        btnGrupInmueble.add(rbtnInmuebleLibres);
        rbtnInmuebleLibres.setText("Libres");
        rbtnInmuebleLibres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInmuebleLibresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(rbtnInmuebleTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbtnInmuebleAlquilados, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbtnInmuebleLibres, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnInmuebleTodos)
                    .addComponent(rbtnInmuebleAlquilados)
                    .addComponent(rbtnInmuebleLibres)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabInmuebleLayout = new javax.swing.GroupLayout(tabInmueble);
        tabInmueble.setLayout(tabInmuebleLayout);
        tabInmuebleLayout.setHorizontalGroup(
            tabInmuebleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInmuebleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabInmuebleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(tabInmuebleLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addGroup(tabInmuebleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabInmuebleLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregarInmueble, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(249, 249, 249)))
                .addContainerGap())
        );
        tabInmuebleLayout.setVerticalGroup(
            tabInmuebleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInmuebleLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnAgregarInmueble)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Inmuebles", tabInmueble);

        btnAgregarInquilinos.setText("Agregar Inquilinos");
        btnAgregarInquilinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarInquilinosActionPerformed(evt);
            }
        });

        tablaInquilinos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "DNI", "VER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaInquilinos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInquilinosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInquilinos);
        if (tablaInquilinos.getColumnModel().getColumnCount() > 0) {
            tablaInquilinos.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout tabInquilinoLayout = new javax.swing.GroupLayout(tabInquilino);
        tabInquilino.setLayout(tabInquilinoLayout);
        tabInquilinoLayout.setHorizontalGroup(
            tabInquilinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInquilinoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabInquilinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabInquilinoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarInquilinos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabInquilinoLayout.setVerticalGroup(
            tabInquilinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInquilinoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAgregarInquilinos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Inquilinos", tabInquilino);

        tablaContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Inmueble Direccion", "Propietario", "Inquilino", "Inicio", "Fin", "VER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaContratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaContratosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaContratos);
        if (tablaContratos.getColumnModel().getColumnCount() > 0) {
            tablaContratos.getColumnModel().getColumn(6).setResizable(false);
        }

        btnRealizarContrato.setText("Realizar Contrato");
        btnRealizarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarContratoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabContratoLayout = new javax.swing.GroupLayout(tabContrato);
        tabContrato.setLayout(tabContratoLayout);
        tabContratoLayout.setHorizontalGroup(
            tabContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabContratoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                    .addGroup(tabContratoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRealizarContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabContratoLayout.setVerticalGroup(
            tabContratoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabContratoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnRealizarContrato)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Contratos", tabContrato);

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "DNI", "CARGO", "VER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaEmpleados);
        if (tablaEmpleados.getColumnModel().getColumnCount() > 0) {
            tablaEmpleados.getColumnModel().getColumn(0).setResizable(false);
            tablaEmpleados.getColumnModel().getColumn(1).setResizable(false);
            tablaEmpleados.getColumnModel().getColumn(2).setResizable(false);
            tablaEmpleados.getColumnModel().getColumn(3).setResizable(false);
            tablaEmpleados.getColumnModel().getColumn(4).setResizable(false);
            tablaEmpleados.getColumnModel().getColumn(5).setResizable(false);
        }

        btnAgregarEmpleados.setText("Agregar Empleados");
        btnAgregarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpleadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabEmpleadosLayout = new javax.swing.GroupLayout(tabEmpleados);
        tabEmpleados.setLayout(tabEmpleadosLayout);
        tabEmpleadosLayout.setHorizontalGroup(
            tabEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabEmpleadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabEmpleadosLayout.setVerticalGroup(
            tabEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabEmpleadosLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAgregarEmpleados)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Empleados", tabEmpleados);

        btnAgregarPropietario.setText("Agregar Propietarios");
        btnAgregarPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPropietarioActionPerformed(evt);
            }
        });

        tablaPropietarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO", "DNI", "VER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPropietarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPropietariosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaPropietarios);
        if (tablaPropietarios.getColumnModel().getColumnCount() > 0) {
            tablaPropietarios.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout tabPropietarioLayout = new javax.swing.GroupLayout(tabPropietario);
        tabPropietario.setLayout(tabPropietarioLayout);
        tabPropietarioLayout.setHorizontalGroup(
            tabPropietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPropietarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPropietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabPropietarioLayout.createSequentialGroup()
                        .addGap(0, 359, Short.MAX_VALUE)
                        .addComponent(btnAgregarPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 359, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        tabPropietarioLayout.setVerticalGroup(
            tabPropietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPropietarioLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAgregarPropietario)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Propietarios", tabPropietario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaContratosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaContratosMouseClicked
        javax.swing.JTable tableSource = (javax.swing.JTable) evt.getSource();
        int fila = tableSource.rowAtPoint(evt.getPoint());
        int columna = tableSource.columnAtPoint(evt.getPoint());
        if (columna == tableSource.getColumnModel().getColumnCount() - 1) {
            ContratoDialogView contratoDialogView = new  ContratoDialogView(this, true, conexion,contratoData.obtenerContratos().get(fila));
            contratoDialogView.setVisible(true);            
            cargarTablas();
        }
    }//GEN-LAST:event_tablaContratosMouseClicked

    private void rbtnInmuebleLibresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInmuebleLibresActionPerformed
        //Inmuebles libre
        inmuebleList = inmuebleData.obtenerInmueblesNoAlquilados();
        cargarTablaInmuebles();
    }//GEN-LAST:event_rbtnInmuebleLibresActionPerformed

    private void rbtnInmuebleAlquiladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInmuebleAlquiladosActionPerformed
        inmuebleList = inmuebleData.obtenerInmueblesAlquilados();
        cargarTablaInmuebles();
    }//GEN-LAST:event_rbtnInmuebleAlquiladosActionPerformed

    private void rbtnInmuebleTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInmuebleTodosActionPerformed
        inmuebleList = inmuebleData.obtenerInmuebles();
        cargarTablaInmuebles();
    }//GEN-LAST:event_rbtnInmuebleTodosActionPerformed

    private void tablaPropietariosMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tablaPropietariosMouseClicked
        javax.swing.JTable tableSource = (javax.swing.JTable) evt.getSource();
        int fila = tableSource.rowAtPoint(evt.getPoint());
        int columna = tableSource.columnAtPoint(evt.getPoint());
        if (columna == tableSource.getColumnModel().getColumnCount() - 1) {
            PropietarioDialogView propietarioDialogView = new PropietarioDialogView(this, true, conexion,
                    propietarioData.obtenerPropietarios().get(fila));
            propietarioDialogView.setVisible(true);
            cargarTablas();
        }
    }// GEN-LAST:event_tablaPropietariosMouseClicked

    private void tablaEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tablaEmpleadosMouseClicked
        javax.swing.JTable tableSource = (javax.swing.JTable) evt.getSource();
        int fila = tableSource.rowAtPoint(evt.getPoint());
        int columna = tableSource.columnAtPoint(evt.getPoint());
        if (columna == tableSource.getColumnModel().getColumnCount() - 1) {
            EmpleadoDialogView empleadoDialogView = new EmpleadoDialogView(this, true, conexion,
                    empleadoData.obtenerEmpleados().get(fila));
            empleadoDialogView.setVisible(true);
            cargarTablas();
        }
    }// GEN-LAST:event_tablaEmpleadosMouseClicked

    private void tablaInmueblesMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tablaInmueblesMouseClicked
        javax.swing.JTable tableSource = (javax.swing.JTable) evt.getSource();
        int fila = tableSource.rowAtPoint(evt.getPoint());
        int columna = tableSource.columnAtPoint(evt.getPoint());
        if (columna == tableSource.getColumnModel().getColumnCount() - 1) {
            InmuebleDialogView inmuebleView = new InmuebleDialogView(this, true, conexion,
                    inmuebleData.obtenerInmuebles().get(fila));
            inmuebleView.setVisible(true);
            cargarTablas();
        }
    }// GEN-LAST:event_tablaInmueblesMouseClicked

    private void tablaInquilinosMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tablaInquilinosMouseClicked
        javax.swing.JTable tableSource = (javax.swing.JTable) evt.getSource();
        int fila = tableSource.rowAtPoint(evt.getPoint());
        int columna = tableSource.columnAtPoint(evt.getPoint());
        if (columna == tableSource.getColumnModel().getColumnCount() - 1) {
            InquilinoDialogView inquilinoView = new InquilinoDialogView(this, true, conexion,
                    inquilinoData.obtenerInquilinos().get(fila));
            inquilinoView.setVisible(true);
            cargarTablas();
        }
    }// GEN-LAST:event_tablaInquilinosMouseClicked

    private void btnAgregarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarEmpleadosActionPerformed
        EmpleadoDialogView empleadoDialogView = new EmpleadoDialogView(this, true, conexion);
        empleadoDialogView.setVisible(true);
        cargarTablas();

    }// GEN-LAST:event_btnAgregarEmpleadosActionPerformed

    private void btnAgregarInmuebleActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarInmuebleActionPerformed
        InmuebleDialogView inquilinoView = new InmuebleDialogView(this, true, conexion);
        inquilinoView.setVisible(true);
        cargarTablas();
    }// GEN-LAST:event_btnAgregarInmuebleActionPerformed

    private void btnAgregarInquilinosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarInquilinosActionPerformed
        InquilinoDialogView inquilinoView = new InquilinoDialogView(this, true, conexion);
        inquilinoView.setVisible(true);
        cargarTablas();
    }// GEN-LAST:event_btnAgregarInquilinosActionPerformed

    private void btnAgregarPropietarioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAgregarPropietarioActionPerformed
        PropietarioDialogView propietarioView = new PropietarioDialogView(this, true, conexion);
        propietarioView.setVisible(true);
        cargarTablas();
    }// GEN-LAST:event_btnAgregarPropietarioActionPerformed

    private void btnRealizarContratoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRealizarContratoActionPerformed
        ContratoDialogView contratoDialogView = new ContratoDialogView(this, true, conexion);
        contratoDialogView.setVisible(true);
        cargarTablas();
    }// GEN-LAST:event_btnRealizarContratoActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarEmpleados;
    private javax.swing.JButton btnAgregarInmueble;
    private javax.swing.JButton btnAgregarInquilinos;
    private javax.swing.JButton btnAgregarPropietario;
    private javax.swing.ButtonGroup btnGrupInmueble;
    private javax.swing.JButton btnRealizarContrato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblInicioInmueblesAlquilados;
    private javax.swing.JLabel lblInicioInmueblesLibres;
    private javax.swing.JLabel lblInicioInmueblesTotal;
    private javax.swing.JRadioButton rbtnInmuebleAlquilados;
    private javax.swing.JRadioButton rbtnInmuebleLibres;
    private javax.swing.JRadioButton rbtnInmuebleTodos;
    private javax.swing.JPanel tabContrato;
    private javax.swing.JPanel tabEmpleados;
    private javax.swing.JPanel tabInicio;
    private javax.swing.JPanel tabInmueble;
    private javax.swing.JPanel tabInquilino;
    private javax.swing.JPanel tabPropietario;
    private javax.swing.JTable tablaContratos;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaInmuebles;
    private javax.swing.JTable tablaInquilinos;
    private javax.swing.JTable tablaPropietarios;
    // End of variables declaration//GEN-END:variables
}
