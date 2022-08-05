/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import data.Conexion;
import data.EmpleadoData;
import data.InmuebleData;
import data.InquilinoData;
import data.PropietarioData;
import entities.Empleado;
import entities.Inmueble;
import entities.Inquilino;
import entities.Propietario;

/**
 *
 * @author agusv
 */
public class AppMenu extends javax.swing.JFrame {

    Conexion conexion = new Conexion();
    InquilinoData inquilinoData;
    PropietarioData propietarioData;
    InmuebleData inmuebleData;
    EmpleadoData empleadoData;

    DefaultTableModel tableModel = new DefaultTableModel();

    public AppMenu() {
        initComponents();
        inquilinoData = new InquilinoData(conexion);
        propietarioData = new PropietarioData(conexion);
        inmuebleData = new InmuebleData(conexion);
        empleadoData = new EmpleadoData(conexion);
        cargarTablas();
    }

    private void cargarTablas() {
        cargarTablaInquilinos();
        cargarTablaPropietarios();
        cargarTablaInmuebles();
        cargarTablaEmpleados();
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

        // Cargamos nuevos datos a la tabla
        ArrayList<Inmueble> inmuebleList = new ArrayList<>();
        inmuebleList = inmuebleData.obtenerInmuebles();
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaInmuebles = new javax.swing.JTable();
        btnAgregarInmueble = new javax.swing.JButton();
        panelInquilinoTabs = new javax.swing.JPanel();
        btnAgregarInquilinos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInquilinos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaContratos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        btnAgregarEmpleados = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnAgregarPropietario = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPropietarios = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 892, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Inicio", jPanel1);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAgregarInmueble)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Inmuebles", jPanel2);

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

        javax.swing.GroupLayout panelInquilinoTabsLayout = new javax.swing.GroupLayout(panelInquilinoTabs);
        panelInquilinoTabs.setLayout(panelInquilinoTabsLayout);
        panelInquilinoTabsLayout.setHorizontalGroup(
            panelInquilinoTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInquilinoTabsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInquilinoTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInquilinoTabsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarInquilinos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInquilinoTabsLayout.setVerticalGroup(
            panelInquilinoTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInquilinoTabsLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAgregarInquilinos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Inquilinos", panelInquilinoTabs);

        tablaContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Inmueble Direccion", "Propietario", "Inquilino", "VER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tablaContratos);
        if (tablaContratos.getColumnModel().getColumnCount() > 0) {
            tablaContratos.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton1.setText("Realizar Contrato");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Contratos", jPanel3);

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAgregarEmpleados)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Empleados", jPanel4);

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 351, Short.MAX_VALUE)
                        .addComponent(btnAgregarPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 351, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAgregarPropietario)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Propietarios", jPanel5);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenu3.setText("jMenu3");
        jMenu2.add(jMenu3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelInquilinoTabs;
    private javax.swing.JTable tablaContratos;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaInmuebles;
    private javax.swing.JTable tablaInquilinos;
    private javax.swing.JTable tablaPropietarios;
    // End of variables declaration//GEN-END:variables
}
