/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import data.Conexion;
import data.InquilinoData;
import entities.Inquilino;

/**
 *
 * @author agusv
 */
public class AppMenu extends javax.swing.JFrame {

    Conexion conexion = new Conexion();
    InquilinoData inquilinoData;
    

    DefaultTableModel tableModel = new DefaultTableModel();

    /**
     * Creates new form AppMenu
     */
    public AppMenu() {
        initComponents();
        inquilinoData = new InquilinoData(conexion);
        cargarTablaInquilinos();
    }


    public void cargarTablaInquilinos() {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaInmobiliaria = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        panelInquilinoTabs = new javax.swing.JPanel();
        btnAgregarInquilinos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInquilinos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
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
            .addGap(0, 455, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Inicio", jPanel1);

        tablaInmobiliaria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaInmobiliaria);

        jButton1.setText("Agregar Inmobiliaria");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
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
        tablaInquilinos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaInquilinosKeyPressed(evt);
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
                        .addComponent(btnAgregarInquilinos)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelInquilinoTabsLayout.setVerticalGroup(
            panelInquilinoTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInquilinoTabsLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnAgregarInquilinos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Inquilinos", panelInquilinoTabs);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 892, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Contratos", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 892, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Empleados", jPanel4);

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

    //############################### INQUILINOTABS   #################3
    private void btnAgregarInquilinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInquilinosActionPerformed
        // TODO add your handling code here:
        InquilinoDialogView inquilinoView = new InquilinoDialogView(this, true, conexion);
        inquilinoView.setVisible(true);
        cargarTablaInquilinos();
    }//GEN-LAST:event_btnAgregarInquilinosActionPerformed

    private void tablaInquilinosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaInquilinosKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tablaInquilinosKeyPressed

    private void tablaInquilinosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInquilinosMouseClicked
        // TODO add your handling code here:
        javax.swing.JTable tableSource = (javax.swing.JTable) evt.getSource();
        int fila = tableSource.rowAtPoint(evt.getPoint());
        int columna = tableSource.columnAtPoint(evt.getPoint());        
        if (columna == tableSource.getColumnModel().getColumnCount() - 1) {
            InquilinoDialogView inquilinoView = new InquilinoDialogView(this, true, conexion,inquilinoData.obtenerInquilinos().get(fila));
            inquilinoView.setVisible(true);
            cargarTablaInquilinos();            
        }
    }//GEN-LAST:event_tablaInquilinosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarInquilinos;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelInquilinoTabs;
    private javax.swing.JTable tablaInmobiliaria;
    private javax.swing.JTable tablaInquilinos;
    // End of variables declaration//GEN-END:variables
}
