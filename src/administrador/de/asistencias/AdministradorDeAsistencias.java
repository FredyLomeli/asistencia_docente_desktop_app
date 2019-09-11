/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrador.de.asistencias;

import vistas.frmPrincipal;
import Controlador.ctrPrincipal;

/**
 *
 * @author Asesor
 */
public class AdministradorDeAsistencias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Cargar Formularios
        frmPrincipal fIncio = new frmPrincipal();
        
        //Controladores
        ctrPrincipal controladorPrincipal = new ctrPrincipal(fIncio);
        
        //Inicio del sistema
        controladorPrincipal.showForm();
        //frmIncio.setVisible(true);
        //frmIncio.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
}
