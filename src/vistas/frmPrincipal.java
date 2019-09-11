/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asesor
 */
public class frmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form frmPrincipal
     */
    
    public DefaultTableModel dtmRegistroAsistencia;
    
    public frmPrincipal() {
        dtmRegistroAsistencia = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
        };
        initComponents();
    }
    
    @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("vistas/imagen/icon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        lblUnidIcon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        lblReloj = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDocenteIcon = new javax.swing.JLabel();
        lblCrnIcon = new javax.swing.JLabel();
        txtIdDocente = new javax.swing.JTextField();
        txtCrn = new javax.swing.JTextField();
        btnEntrada = new javax.swing.JButton();
        btnSalida = new javax.swing.JButton();
        lblDocente = new javax.swing.JLabel();
        lblDocente2 = new javax.swing.JLabel();
        lblCrn = new javax.swing.JLabel();
        jspListadoAsistencia = new javax.swing.JScrollPane();
        jtListadoAsistencia = new javax.swing.JTable();
        jmbMenuPrincipal = new javax.swing.JMenuBar();
        jmOpciones = new javax.swing.JMenu();
        jmiDocentes = new javax.swing.JMenuItem();
        jmiCrn = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiReporte = new javax.swing.JMenuItem();
        jmiConfig = new javax.swing.JMenuItem();
        jmAdministrador = new javax.swing.JMenu();
        jmiAdmin = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setResizable(false);

        jpPrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de Asistencias"));

        lblUnidIcon.setBackground(new java.awt.Color(200, 200, 200));
        lblUnidIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/imagen/logo-unid.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblFecha.setBackground(java.awt.SystemColor.controlHighlight);
        lblFecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblReloj.setBackground(java.awt.SystemColor.controlHighlight);
        lblReloj.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblReloj.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        lblDocenteIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/imagen/docente1.png"))); // NOI18N

        lblCrnIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/imagen/materia1.png"))); // NOI18N

        txtIdDocente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        txtCrn.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/imagen/Entrada.png"))); // NOI18N

        btnSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/imagen/Salida.png"))); // NOI18N

        lblDocente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblDocente.setText("ID DOCENTE");

        lblDocente2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblDocente2.setForeground(new java.awt.Color(51, 153, 0));

        lblCrn.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblCrn.setText("CRN MATERIA");

        jspListadoAsistencia.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspListadoAsistencia.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jtListadoAsistencia.setModel(dtmRegistroAsistencia);
        jtListadoAsistencia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jspListadoAsistencia.setViewportView(jtListadoAsistencia);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 67, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCrnIcon)
                                .addGap(18, 18, 18)
                                .addComponent(txtCrn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCrn, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDocenteIcon)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDocente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDocente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jspListadoAsistencia))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDocenteIcon))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDocente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDocente2)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCrnIcon))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCrn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCrn))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jspListadoAsistencia, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUnidIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUnidIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jmOpciones.setText("Opciones");
        jmOpciones.setEnabled(false);

        jmiDocentes.setText("Docentes");
        jmOpciones.add(jmiDocentes);

        jmiCrn.setText("CRN");
        jmOpciones.add(jmiCrn);
        jmOpciones.add(jSeparator1);

        jmiReporte.setText("Reportes");
        jmOpciones.add(jmiReporte);

        jmiConfig.setText("Configuracion");
        jmOpciones.add(jmiConfig);

        jmbMenuPrincipal.add(jmOpciones);

        jmAdministrador.setText("Admin");

        jmiAdmin.setText("Activar");
        jmAdministrador.add(jmiAdmin);

        jmbMenuPrincipal.add(jmAdministrador);

        setJMenuBar(jmbMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEntrada;
    public javax.swing.JButton btnSalida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JMenu jmAdministrador;
    public javax.swing.JMenu jmOpciones;
    private javax.swing.JMenuBar jmbMenuPrincipal;
    public javax.swing.JMenuItem jmiAdmin;
    public javax.swing.JMenuItem jmiConfig;
    public javax.swing.JMenuItem jmiCrn;
    public javax.swing.JMenuItem jmiDocentes;
    public javax.swing.JMenuItem jmiReporte;
    public javax.swing.JPanel jpPrincipal;
    private javax.swing.JScrollPane jspListadoAsistencia;
    public javax.swing.JTable jtListadoAsistencia;
    public javax.swing.JLabel lblCrn;
    private javax.swing.JLabel lblCrnIcon;
    public javax.swing.JLabel lblDocente;
    public javax.swing.JLabel lblDocente2;
    private javax.swing.JLabel lblDocenteIcon;
    public javax.swing.JLabel lblFecha;
    public javax.swing.JLabel lblReloj;
    private javax.swing.JLabel lblUnidIcon;
    public javax.swing.JTextField txtCrn;
    public javax.swing.JTextField txtIdDocente;
    // End of variables declaration//GEN-END:variables
}