/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import javax.swing.JOptionPane;

import data.Conexion;
import data.InquilinoData;
import entities.Inquilino;
import entities.Persona;

/**
 *
 * @author agusv
 */
public class InquilinoDialogView extends javax.swing.JDialog {

    private InquilinoData inquilinoData;

    public InquilinoDialogView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pnlCantRenevaciones.setVisible(false);

    }

    public InquilinoDialogView(java.awt.Frame parent, boolean modal, Conexion conexion) {
        super(parent, modal);
        initComponents();
        inquilinoData = new InquilinoData(conexion);
        pnlCantRenevaciones.setVisible(false);
    }

    public InquilinoDialogView(java.awt.Frame parent, boolean modal, Conexion conexion, Inquilino inquilino) {
        super(parent, modal);
        initComponents();
        inquilinoData = new InquilinoData(conexion);
        obtenerDatosInquilino(inquilino);
        pnlCantRenevaciones.setVisible(false);
    }

    private void obtenerDatosInquilino(Inquilino inquilino) {
        txfId.setText(String.valueOf(inquilino.getId()));

        txfDni.setText(String.valueOf(inquilino.getPersona().getDni()));
        txfNombre.setEnabled(true);
        txfNombre.setText(inquilino.getPersona().getNombre());

        txfApellido.setEnabled(true);
        txfApellido.setText(inquilino.getPersona().getApellido());

        txfCuit.setEnabled(true);
        txfCuit.setText(String.valueOf(inquilino.getPersona().getCuit()));

        txfEmail.setEnabled(true);
        txfEmail.setText(inquilino.getPersona().getEmail());

        txfTelefono.setEnabled(true);
        txfTelefono.setText(String.valueOf(inquilino.getPersona().getTelefono()));

        selectCondicion.setEnabled(true);
        if (inquilino.getId() != null) {
            selectCondicion.setSelectedItem("Renovante");
            pnlCantRenevaciones.setEnabled(true);
            txfCantRenovaciones.setText(String.valueOf(inquilino.getCantRenovaciones()));
        } else {
            selectCondicion.setSelectedItem(inquilino.getCondicion());
            if (inquilino.getCondicion().equals("Renovante")) {
                pnlCantRenevaciones.setEnabled(true);
            } else {
                pnlCantRenevaciones.setEnabled(false);
            }
        }

        selectCalificacionInquilino.setEnabled(true);
        selectCalificacionInquilino.setSelectedItem(inquilino.getPersona().getCalificacionInquilino());

        selectCalificacionPropietario.setEnabled(true);
        selectCalificacionPropietario.setSelectedItem(inquilino.getPersona().getCalificacionPropietario());

        selectCalificacionGarante.setEnabled(true);
        selectCalificacionGarante.setSelectedItem(inquilino.getPersona().getCalificacionGarante());

        selectCalificacionEmpleado.setEnabled(true);
        selectCalificacionEmpleado.setSelectedItem(inquilino.getPersona().getCalificacionEmpleado());

        btnBorrar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnSalir.setEnabled(true);
        btnLimpiar.setEnabled(true);
        btnGuardar.setEnabled(false);
    }

    private void limpiarDatosPersonas(Boolean state) {
        txfId.setText("");
        txfDni.setText("");

        txfNombre.setEnabled(state);
        txfNombre.setText("");

        txfApellido.setEnabled(state);
        txfApellido.setText("");

        txfCuit.setEnabled(state);
        txfCuit.setText("");

        txfEmail.setEnabled(state);
        txfEmail.setText("");

        txfTelefono.setEnabled(state);
        txfTelefono.setText("");

        selectCondicion.setEnabled(state);

        selectCalificacionInquilino.setEnabled(state);
        selectCalificacionInquilino.setSelectedItem("Ninguna");
        selectCalificacionPropietario.setEnabled(state);
        selectCalificacionPropietario.setSelectedItem("Ninguna");
        selectCalificacionGarante.setEnabled(state);
        selectCalificacionGarante.setSelectedItem("Ninguna");
        selectCalificacionEmpleado.setEnabled(state);
        selectCalificacionEmpleado.setSelectedItem("Ninguna");
        pnlCantRenevaciones.setVisible(false);

        btnBorrar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnLimpiar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }

    public Inquilino validar() {
        String result = "";
        try {
            Inquilino inquilino = new Inquilino();
            Persona persona = new Persona();

            if (txfNombre.getText().isBlank()) {
                result += "Nombre: Vacio\n";
            } else {
                persona.setNombre(txfNombre.getText());
            }

            if (txfApellido.getText().isBlank()) {
                result += "Apellido: Vacio\n";
            } else {
                persona.setApellido(txfApellido.getText());
            }

            if (txfDni.getText().isBlank()) {
                result += "Dni: Vacio\n";
            } else {
                try {
                    persona.setDni(Long.parseLong(txfDni.getText()));
                } catch (Exception e) {
                    // TODO: handle exception
                    result += "Dni: Debe ser un numero\n";
                }
            }

            if (txfCuit.getText().isBlank()) {
                result += "Cuit: Vacio\n";
            } else {
                try {
                    persona.setCuit(Long.parseLong(txfCuit.getText()));
                } catch (Exception e) {
                    // TODO: handle exception
                    result += "Cuit: Debe ser un numero\n";
                }
            }

            persona.setEmail(txfEmail.getText());

            if (!txfTelefono.getText().isBlank()) {
                try {
                    persona.setTelefono(Long.parseLong(txfTelefono.getText()));
                } catch (Exception e) {
                    // TODO: handle exception
                    result += "Telefono: Debe ser un numero\n";
                }
            }

            persona.setCalificacionInquilino(selectCalificacionInquilino.getSelectedItem().toString());
            persona
                    .setCalificacionPropietario(selectCalificacionPropietario.getSelectedItem().toString());
            persona.setCalificacionGarante(selectCalificacionGarante.getSelectedItem().toString());
            persona.setCalificacionEmpleado(selectCalificacionEmpleado.getSelectedItem().toString());

            inquilino.setCondicion(selectCondicion.getSelectedItem().toString());

            if (result.isEmpty() || result.isBlank()) {
                inquilino.setPersona(persona);
                return inquilino;
            } else {
                JOptionPane.showMessageDialog(null, "Valores ingresados invalidos\n" + result,
                        "Valores invalidos",
                        JOptionPane.WARNING_MESSAGE);
                return null;
            }
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "Valores ingresados invalidos\n" + result + "\n" + e.getMessage(),
                    "Valores invalidos",
                    JOptionPane.ERROR_MESSAGE);
            return null;
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        selectCalificacionInquilino = new javax.swing.JComboBox<>();
        selectCalificacionPropietario = new javax.swing.JComboBox<>();
        selectCalificacionGarante = new javax.swing.JComboBox<>();
        selectCalificacionEmpleado = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        selectCondicion = new javax.swing.JComboBox<>();
        pnlCantRenevaciones = new javax.swing.JPanel();
        txfCantRenovaciones = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txfId = new javax.swing.JTextField();
        txfDni = new javax.swing.JTextField();
        txfNombre = new javax.swing.JTextField();
        txfApellido = new javax.swing.JTextField();
        txfCuit = new javax.swing.JTextField();
        txfEmail = new javax.swing.JTextField();
        txfTelefono = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel3.setPreferredSize(new java.awt.Dimension(235, 287));

        jLabel8.setText("Condicion:");

        jLabel12.setText("Garante:");

        jLabel11.setText("Propietario:");

        jLabel10.setText("Inquilino:");

        jLabel13.setText("Calificacion de la persona");

        selectCalificacionInquilino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Excelente", "Buena", "Mala", "Ninguna" }));
        selectCalificacionInquilino.setEnabled(false);
        selectCalificacionInquilino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCalificacionInquilinoActionPerformed(evt);
            }
        });

        selectCalificacionPropietario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Excelente", "Buena", "Mala", "Ninguna" }));
        selectCalificacionPropietario.setEnabled(false);

        selectCalificacionGarante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Excelente", "Buena", "Mala", "Ninguna" }));
        selectCalificacionGarante.setEnabled(false);

        selectCalificacionEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Excelente", "Buena", "Mala", "Ninguna" }));
        selectCalificacionEmpleado.setEnabled(false);
        selectCalificacionEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCalificacionEmpleadoActionPerformed(evt);
            }
        });

        jLabel14.setText("Empleado:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(36, 36, 36))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selectCalificacionEmpleado, 0, 130, Short.MAX_VALUE)
                    .addComponent(selectCalificacionGarante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectCalificacionPropietario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectCalificacionInquilino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectCalificacionInquilino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectCalificacionPropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(selectCalificacionGarante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(selectCalificacionEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        selectCondicion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nuevo", "Renovante" }));
        selectCondicion.setEnabled(false);
        selectCondicion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selectCondicionItemStateChanged(evt);
            }
        });
        selectCondicion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                selectCondicionPropertyChange(evt);
            }
        });

        txfCantRenovaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfCantRenovacionesFocusLost(evt);
            }
        });
        txfCantRenovaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfCantRenovacionesActionPerformed(evt);
            }
        });

        jLabel19.setText("Cantidad de renovaciones:");

        javax.swing.GroupLayout pnlCantRenevacionesLayout = new javax.swing.GroupLayout(pnlCantRenevaciones);
        pnlCantRenevaciones.setLayout(pnlCantRenevacionesLayout);
        pnlCantRenevacionesLayout.setHorizontalGroup(
            pnlCantRenevacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCantRenevacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txfCantRenovaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCantRenevacionesLayout.setVerticalGroup(
            pnlCantRenevacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCantRenevacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCantRenevacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfCantRenovaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(selectCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(pnlCantRenevaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(51, 51, 51))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(selectCondicion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCantRenevaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));

        jLabel1.setText("ID:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellido:");

        jLabel4.setText("Dni:");

        jLabel5.setText("Cuit:");

        jLabel6.setText("Email:");

        jLabel7.setText("Telefono");

        txfId.setEnabled(false);
        txfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfIdActionPerformed(evt);
            }
        });

        txfDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfDniActionPerformed(evt);
            }
        });

        txfNombre.setEnabled(false);
        txfNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfNombreFocusLost(evt);
            }
        });

        txfApellido.setEnabled(false);

        txfCuit.setEnabled(false);
        txfCuit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txfCuitFocusLost(evt);
            }
        });

        txfEmail.setEnabled(false);

        txfTelefono.setEnabled(false);

        jLabel9.setText("Datos Personales");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txfId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txfCuit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.setEnabled(false);
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setEnabled(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnSalir)
                    .addComponent(btnBorrar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnEditar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectCalificacionInquilinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCalificacionInquilinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectCalificacionInquilinoActionPerformed

    private void txfNombreFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txfNombreFocusLost
        // TODO add your handling code here:

    }// GEN-LAST:event_txfNombreFocusLost

    private void txfCuitFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txfCuitFocusLost
        // TODO add your handling code here:
    }// GEN-LAST:event_txfCuitFocusLost

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (validar() != null) {
            JOptionPane.showMessageDialog(null, "Validacion Correcta");
        } else {
            JOptionPane.showMessageDialog(null, "Validacion incorrecta");
        }

    }// GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        Inquilino inquilino = new Inquilino();
        inquilino = validar();
        if (inquilino != null) {            
            if (inquilinoData.agregarInquilino(inquilino)) {
                txfId.setText(String.valueOf(inquilino.getId()));
            }
        }

    }// GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }// GEN-LAST:event_jButton2ActionPerformed

    private void txfDniActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txfDniActionPerformed
        // TODO add your handling code here:
        try {
            Inquilino inquilino = new Inquilino();

            if (txfDni.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de dni", "Dni invalido",
                        JOptionPane.INFORMATION_MESSAGE);
                btnBorrar.setEnabled(false);
                btnEditar.setEnabled(false);
                btnSalir.setEnabled(true);
                btnLimpiar.setEnabled(true);
                btnGuardar.setEnabled(true);

            } else {
                inquilino = inquilinoData.obtenerInquilinosXDni(Long.parseLong(txfDni.getText()));
                if (inquilino.getPersona() != null) {
                    obtenerDatosInquilino(inquilino);
                } else {
                    String dni = txfDni.getText();
                    limpiarDatosPersonas(true);
                    txfDni.setText(dni);
                }
                txfNombre.setFocusable(true);
            }

        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "El dni ingresado es invalido \n" + e.getMessage(), "Dni invalido",
                    JOptionPane.WARNING_MESSAGE);
        }
    }// GEN-LAST:event_txfDniActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarDatosPersonas(false);
    }// GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        Integer id;
        try {
            id = Integer.parseInt(txfId.getText());
            inquilinoData.eliminarInquilino(id);
            limpiarDatosPersonas(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valor del inquilino invalido", "Error al eliminar el inquilino",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }// GEN-LAST:event_btnBorrarActionPerformed

    private void txfCantRenovacionesFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_txfCantRenovacionesFocusLost
        // TODO add your handling code here:
    }// GEN-LAST:event_txfCantRenovacionesFocusLost

    private void txfCantRenovacionesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txfCantRenovacionesActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txfCantRenovacionesActionPerformed

    private void selectCondicionPropertyChange(java.beans.PropertyChangeEvent evt) {// GEN-FIRST:event_selectCondicionPropertyChange
        // TODO add your handling code here:

    }// GEN-LAST:event_selectCondicionPropertyChange

    private void selectCondicionItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_selectCondicionItemStateChanged
        // TODO add your handling code here:
        if (!txfDni.getText().isEmpty()) {
            if (selectCondicion.getSelectedItem().toString().equals("Nuevo")) {
                pnlCantRenevaciones.setVisible(false);
            }
            if (selectCondicion.getSelectedItem().toString().equals("Renovante")) {
                pnlCantRenevaciones.setVisible(true);
            }
        }
    }// GEN-LAST:event_selectCondicionItemStateChanged

    private void selectCalificacionEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_selectCalificacionEmpleadoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_selectCalificacionEmpleadoActionPerformed

    private void txfIdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txfIdActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txfIdActionPerformed

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
            java.util.logging.Logger.getLogger(InquilinoDialogView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InquilinoDialogView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InquilinoDialogView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InquilinoDialogView.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InquilinoDialogView dialog = new InquilinoDialogView(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel pnlCantRenevaciones;
    private javax.swing.JComboBox<String> selectCalificacionEmpleado;
    private javax.swing.JComboBox<String> selectCalificacionGarante;
    private javax.swing.JComboBox<String> selectCalificacionInquilino;
    private javax.swing.JComboBox<String> selectCalificacionPropietario;
    private javax.swing.JComboBox<String> selectCondicion;
    private javax.swing.JTextField txfApellido;
    private javax.swing.JTextField txfCantRenovaciones;
    private javax.swing.JTextField txfCuit;
    private javax.swing.JTextField txfDni;
    private javax.swing.JTextField txfEmail;
    private javax.swing.JTextField txfId;
    private javax.swing.JTextField txfNombre;
    private javax.swing.JTextField txfTelefono;
    // End of variables declaration//GEN-END:variables
}
