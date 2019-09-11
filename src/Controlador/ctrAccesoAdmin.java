/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.mConfiguracion;
import Modelos.mDatos;
import Modelos.mValidaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vistas.frmPrincipal;
import vistas.jfdPassword;
 /*
 * @author Frexal
 */
public class ctrAccesoAdmin implements ActionListener{
    
    final private mConfiguracion Config;
    final private jfdPassword vistaPassword;
    final private frmPrincipal vistaPrincipal;
    
    ctrAccesoAdmin(frmPrincipal vistaPrincipal, jfdPassword vistaPassword){
        this.Config = new mConfiguracion();
        this.vistaPrincipal = vistaPrincipal;
        this.vistaPassword = vistaPassword;
        
        this.vistaPassword.btnAcceder.addActionListener(this);
    }
    
    public boolean bloquearAdmin(){
        return Config.consultarConfiguracion("BloqueoPass").equals("Activo");
    }
    
    public boolean validarContraseña(String password){
        String passDB = Config.consultarConfiguracion("Pass");
        return passDB.equals(password);
    }
    
     //Inicio de formulario
    public void showForm(){
        this.vistaPassword.setVisible(true);
        this.vistaPassword.setLocationRelativeTo(null);
    }
    
    private void closeForm(){
        this.vistaPassword.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPassword.btnAcceder){
            verificarContraseña();
        }
    }

    private void verificarContraseña() {
        String password = vistaPassword.txtPassword.getText().trim();
        if(validacionContraseña(password)){
            vistaPrincipal.jmOpciones.setEnabled(true);
            vistaPrincipal.jmiAdmin.setText("Desactivar");
            closeForm();
        }
            
    }
    
        // Validaciones para editar o crear registros
    public boolean validacionContraseña(String password){
        boolean error = true;
        mValidaciones validar = new mValidaciones();
        //Validacion de Calendario
        if(!validar.cadenaNoVacia(password, "Contraseña")) // Nombre no puede ser vacio
            error = false;
        else if(!validarContraseña(password)){
            mDatos.Error = mDatos.ErrorContraseña;
            error = false;
        }
        if(!error)
            JOptionPane.showMessageDialog(vistaPassword,  mDatos.Error, "Validacion Error", JOptionPane.ERROR_MESSAGE);
        return error;
    }
}
