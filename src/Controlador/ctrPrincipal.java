/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.frmPrincipal;
import Modelos.mDatos;
import vistas.jfdConfiguracion;
import vistas.jfdDocentes;
import vistas.jfdEditMaterias;
import Modelos.mDateTime;
import vistas.jfdPassword;

/**
 *
 * @author Asesor
 */
public class ctrPrincipal implements ActionListener, Runnable{

    final private frmPrincipal vistaPrincipal;
    final private jfdDocentes vistaDocentes;
    final private jfdConfiguracion vistaConfiguracion;
    final private jfdEditMaterias vistaMateria;
    final private jfdPassword vistaPassword;
    final private ctrDocentes controlDocentes;
    final private ctrConfiguracion controlConfiguracion;
    final private ctrHorarioMateriaDocente controlMaterias;
    final private ctrRegistroAsistencia controlRegistro;
    final private ctrAccesoAdmin controlAdmin;
    mDateTime modulDateTime = new mDateTime();
    final private Thread h1;
    String horaReloj;
    
        public ctrPrincipal(frmPrincipal fPrincipal){
        
        // Asigna los valores a la vista principal
        this.vistaPrincipal = fPrincipal;
        this.vistaPrincipal.setTitle(mDatos.NombreEmpresa + " | " + mDatos.NombreSistema);
        
        // Define las vitas
        this.vistaDocentes = new jfdDocentes(fPrincipal, true);
        this.vistaConfiguracion = new jfdConfiguracion(fPrincipal, true);
        this.vistaMateria = new jfdEditMaterias(fPrincipal, true);
        this.vistaPassword = new jfdPassword(fPrincipal, true);
        // Define los controladores
        this.controlRegistro = new ctrRegistroAsistencia(fPrincipal);
        this.controlDocentes = new ctrDocentes(vistaDocentes);
        this.controlConfiguracion= new ctrConfiguracion(vistaConfiguracion);
        this.controlMaterias= new ctrHorarioMateriaDocente(vistaMateria);
        this.controlAdmin = new ctrAccesoAdmin(vistaPrincipal, vistaPassword);
        //Botones de barra de herramientas
        this.vistaPrincipal.jmiDocentes.addActionListener(this);
        this.vistaPrincipal.jmiConfig.addActionListener(this);
        this.vistaPrincipal.jmiCrn.addActionListener(this);
        this.vistaPrincipal.jmiAdmin.addActionListener(this);
        
                
        //Hilo del reloj
        h1 = new Thread(this);
        h1.start();
        
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.jmiDocentes){
            this.controlDocentes.showForm();
        }
        
        else if (e.getSource() == vistaPrincipal.jmiConfig){
            this.controlConfiguracion.showForm();
        }
        
        else if (e.getSource() == vistaPrincipal.jmiCrn){
            this.controlMaterias.showForm();
        }
        
        else if (e.getSource() == vistaPrincipal.jmiAdmin){
            if(vistaPrincipal.jmOpciones.isEnabled()){
                vistaPrincipal.jmOpciones.setEnabled(false);
                vistaPrincipal.jmiAdmin.setText("Activar");
            }
            else {
                if(controlAdmin.bloquearAdmin()){
                    controlAdmin.showForm();
                }else{
                    vistaPrincipal.jmOpciones.setEnabled(true);
                    vistaPrincipal.jmiAdmin.setText("Desactivar");
                }
            }
                
        }
    }
    
    //Inicio de formulario
    public void showForm(){
        this.vistaPrincipal.setVisible(true);
        this.vistaPrincipal.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while(ct==h1){
            vistaPrincipal.lblFecha.setText("  "+modulDateTime.getFechaActualCustomFormat());
            vistaPrincipal.lblReloj.setText("  "+modulDateTime.getHoraActual());
            try {
                Thread.sleep(1000);// 1000 es igual a 1 segundo
            } catch (InterruptedException e) {}
        }
    }
    
}
