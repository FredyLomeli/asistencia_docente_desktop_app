/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javax.swing.DefaultListModel;

/**
 *
 * @author Asesor
 */
public class jfdConfiguracion extends javax.swing.JDialog {

    /**
     * Creates new form jfdConfiguracion
     */
    
    public DefaultListModel<String> dlmCalendar;
    public DefaultListModel<String> dlmAsueto;
    public DefaultListModel<String> dlmMaterias;
    
    public jfdConfiguracion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.dlmCalendar = new DefaultListModel<>();
        this.dlmAsueto = new DefaultListModel<>();
        this.dlmMaterias = new DefaultListModel<>();
        initComponents();
        this.setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtbMenu = new javax.swing.JToolBar();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jtpConfiguracion = new javax.swing.JTabbedPane();
        jpCalendario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCalendario = new javax.swing.JList<>();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        lblNombreCalendario = new javax.swing.JLabel();
        txtNombreCalendario = new javax.swing.JTextField();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        jpAsueto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstAsueto = new javax.swing.JList<>();
        jdcFechaAsueto = new com.toedter.calendar.JDateChooser();
        lblNombreAsueto = new javax.swing.JLabel();
        txtNombreAsueto = new javax.swing.JTextField();
        lblFechaAsueto = new javax.swing.JLabel();
        jpConfig = new javax.swing.JPanel();
        lblPass = new javax.swing.JLabel();
        chkPass = new javax.swing.JCheckBox();
        txtPass = new javax.swing.JPasswordField();
        lblPassConfirm = new javax.swing.JLabel();
        txtPassConfirm = new javax.swing.JPasswordField();
        btnTestCon = new javax.swing.JButton();
        chkBloqueDocente = new javax.swing.JCheckBox();
        chkBloqueoCRN = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jtbMenu.setFloatable(false);
        jtbMenu.setRollover(true);

        btnAgregar.setText("Agregar");
        btnAgregar.setFocusable(false);
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbMenu.add(btnAgregar);

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbMenu.add(btnEditar);

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbMenu.add(btnGuardar);

        btnEliminar.setText("Eliminar");
        btnEliminar.setFocusable(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbMenu.add(btnEliminar);

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.setFocusable(false);
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtbMenu.add(btnCancelar);

        jtpConfiguracion.setToolTipText("");

        jpCalendario.setEnabled(false);

        lstCalendario.setModel(dlmCalendar);
        jScrollPane1.setViewportView(lstCalendario);

        jdcFechaInicio.setDateFormatString("dd/MM/yyyy");
        jdcFechaInicio.setEnabled(false);

        lblNombreCalendario.setText("Nombre:");

        txtNombreCalendario.setEnabled(false);

        lblFechaInicio.setText("Fecha Inicio:");

        lblFechaFin.setText("Fecha Fin:");

        jdcFechaFin.setDateFormatString("dd/MM/yyyy");
        jdcFechaFin.setEnabled(false);

        javax.swing.GroupLayout jpCalendarioLayout = new javax.swing.GroupLayout(jpCalendario);
        jpCalendario.setLayout(jpCalendarioLayout);
        jpCalendarioLayout.setHorizontalGroup(
            jpCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCalendarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaFin)
                    .addComponent(lblFechaInicio)
                    .addComponent(lblNombreCalendario))
                .addGap(18, 18, 18)
                .addGroup(jpCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreCalendario)
                    .addComponent(jdcFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(jdcFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpCalendarioLayout.setVerticalGroup(
            jpCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCalendarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpCalendarioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jpCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreCalendario)
                            .addComponent(txtNombreCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaInicio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaFin))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jtpConfiguracion.addTab("Calendario", jpCalendario);

        jpAsueto.setEnabled(false);

        lstAsueto.setModel(dlmAsueto);
        jScrollPane2.setViewportView(lstAsueto);

        jdcFechaAsueto.setDateFormatString("dd/MM/yyyy");
        jdcFechaAsueto.setEnabled(false);

        lblNombreAsueto.setText("Nombre:");

        txtNombreAsueto.setEnabled(false);

        lblFechaAsueto.setText("Fecha Asueto:");

        javax.swing.GroupLayout jpAsuetoLayout = new javax.swing.GroupLayout(jpAsueto);
        jpAsueto.setLayout(jpAsuetoLayout);
        jpAsuetoLayout.setHorizontalGroup(
            jpAsuetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAsuetoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jpAsuetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreAsueto)
                    .addComponent(lblFechaAsueto))
                .addGap(18, 18, 18)
                .addGroup(jpAsuetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreAsueto)
                    .addComponent(jdcFechaAsueto, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpAsuetoLayout.setVerticalGroup(
            jpAsuetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAsuetoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAsuetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpAsuetoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jpAsuetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreAsueto)
                            .addComponent(txtNombreAsueto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpAsuetoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdcFechaAsueto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaAsueto))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jtpConfiguracion.addTab("Asueto", jpAsueto);

        jpConfig.setEnabled(false);

        lblPass.setText("Contraseña:");

        chkPass.setText("Bloquear con contraseña");

        txtPass.setEnabled(false);

        lblPassConfirm.setText("Repetir Contraseña:");

        txtPassConfirm.setEnabled(false);

        btnTestCon.setText("Test Conexion");

        chkBloqueDocente.setText("Bloqueo por Docente no registrado");
        chkBloqueDocente.setToolTipText("");

        chkBloqueoCRN.setText("Bloqueo por CRN no registrado");

        javax.swing.GroupLayout jpConfigLayout = new javax.swing.GroupLayout(jpConfig);
        jpConfig.setLayout(jpConfigLayout);
        jpConfigLayout.setHorizontalGroup(
            jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConfigLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTestCon)
                .addGap(18, 18, 18))
            .addGroup(jpConfigLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConfigLayout.createSequentialGroup()
                        .addGroup(jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPassConfirm)
                            .addComponent(lblPass))
                        .addGap(31, 31, 31)
                        .addGroup(jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPass)
                            .addComponent(txtPassConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpConfigLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkBloqueDocente)
                            .addComponent(chkPass)
                            .addComponent(chkBloqueoCRN))))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jpConfigLayout.setVerticalGroup(
            jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConfigLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(chkBloqueDocente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkBloqueoCRN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPass)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassConfirm)
                    .addComponent(txtPassConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addComponent(btnTestCon)
                .addContainerGap())
        );

        jtpConfiguracion.addTab("Bloqueo", null, jpConfig, "");
        jpConfig.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtbMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpConfiguracion)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jtbMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtpConfiguracion)
                .addContainerGap())
        );

        jtpConfiguracion.getAccessibleContext().setAccessibleName("Asueto");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnEditar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnTestCon;
    public javax.swing.JCheckBox chkBloqueDocente;
    public javax.swing.JCheckBox chkBloqueoCRN;
    public javax.swing.JCheckBox chkPass;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public com.toedter.calendar.JDateChooser jdcFechaAsueto;
    public com.toedter.calendar.JDateChooser jdcFechaFin;
    public com.toedter.calendar.JDateChooser jdcFechaInicio;
    public javax.swing.JPanel jpAsueto;
    public javax.swing.JPanel jpCalendario;
    public javax.swing.JPanel jpConfig;
    public javax.swing.JToolBar jtbMenu;
    public javax.swing.JTabbedPane jtpConfiguracion;
    public javax.swing.JLabel lblFechaAsueto;
    public javax.swing.JLabel lblFechaFin;
    public javax.swing.JLabel lblFechaInicio;
    public javax.swing.JLabel lblNombreAsueto;
    public javax.swing.JLabel lblNombreCalendario;
    public javax.swing.JLabel lblPass;
    public javax.swing.JLabel lblPassConfirm;
    public javax.swing.JList<String> lstAsueto;
    public javax.swing.JList<String> lstCalendario;
    public javax.swing.JTextField txtNombreAsueto;
    public javax.swing.JTextField txtNombreCalendario;
    public javax.swing.JPasswordField txtPass;
    public javax.swing.JPasswordField txtPassConfirm;
    // End of variables declaration//GEN-END:variables
}
