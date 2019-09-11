/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.mConfiguracion;
import Modelos.mCrn;
import Modelos.mDateTime;
import Modelos.mDatos;
import Modelos.mDocentes;
import Modelos.mRegistroDocente;
import Modelos.mValidaciones;
import clases.cSonidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import vistas.frmPrincipal;


/**
 *
 * @author Frexal
 */
public class ctrRegistroAsistencia implements ActionListener, FocusListener, KeyListener{
    
    final private frmPrincipal vistaPrincipal;
    final private mDocentes Docente;
    final private mCrn Crn;
    final private mDateTime Fecha;
    final private mRegistroDocente dRegistro;
    final private mConfiguracion Config;
    final private cSonidos Sonido;
    
    public ctrRegistroAsistencia(frmPrincipal fPrincipal){
        
        // Asigna los valores a la vista principal
        this.vistaPrincipal = fPrincipal;
        
        // Inicializa Modelos
        this.Docente = new mDocentes();
        this.Crn = new mCrn();
        this.Fecha = new mDateTime();
        this.dRegistro = new mRegistroDocente();
        this.Config = new mConfiguracion();
        this.Sonido = new cSonidos();
        
        // Eventos de los botones
        this.vistaPrincipal.btnEntrada.addActionListener(this);
        this.vistaPrincipal.btnSalida.addActionListener(this);
        
        // Evento de los Textbox
        this.vistaPrincipal.txtIdDocente.addFocusListener(this);
        this.vistaPrincipal.txtIdDocente.addKeyListener(this);
        this.vistaPrincipal.txtCrn.addFocusListener(this);
        this.vistaPrincipal.txtCrn.addKeyListener(this);
        
        // Consulra de registros
        consultarRegistros();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.btnEntrada){
            registrarAsistencia("Entrada");
        }
        else if (e.getSource() == vistaPrincipal.btnSalida){
            registrarAsistencia("Salida");
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == vistaPrincipal.txtIdDocente && !vistaPrincipal.txtIdDocente.isFocusOwner()){
            int idBanner = vistaPrincipal.txtIdDocente.getText().length();
            if(idBanner > 4 && idBanner < 9){
                formatearIdBanner();
                if(Docente.consultarDocenteByIdBanner(vistaPrincipal.txtIdDocente.getText().trim())){
                vistaPrincipal.lblDocente.setText(Docente.getNombre());
                vistaPrincipal.lblDocente2.setText(Docente.getApellido_paterno() + 
                        " " + Docente.getApellido_materno());
                vistaPrincipal.lblDocente.setForeground(mDatos.DARK_GREEN);
                vistaPrincipal.lblDocente2.setForeground(mDatos.DARK_GREEN);
                }else{
                    Docente.limpiar();
                    vistaPrincipal.lblDocente.setText("ID DOCENTE INCORRECTO");
                    vistaPrincipal.lblDocente2.setText("");
                    vistaPrincipal.lblDocente.setForeground(mDatos.RED);
                    vistaPrincipal.lblDocente2.setForeground(mDatos.RED);
                }
            }else{
                reiniciarIdBanner();
            }
        }
        else if (e.getSource() == vistaPrincipal.txtCrn && !vistaPrincipal.txtCrn.isFocusOwner()){
            buscarCRN();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vistaPrincipal.txtIdDocente && vistaPrincipal.txtIdDocente.isFocusOwner()){
            if(vistaPrincipal.txtIdDocente.getText().length() == 8)
                e.consume();
        }
        else if (e.getSource() == vistaPrincipal.txtCrn && vistaPrincipal.txtCrn.isFocusOwner()){
            if(vistaPrincipal.txtCrn.getText().length() == 6)
                e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vistaPrincipal.txtCrn && vistaPrincipal.txtCrn.isFocusOwner()){
            int crn = vistaPrincipal.txtCrn.getText().length();
            if(crn > 3 && crn < 7){
                buscarCRN();
            }else if(crn == 3){
                vistaPrincipal.lblCrn.setText("CRN MATERIA");
                vistaPrincipal.lblCrn.setForeground(mDatos.BLACK);
            }
        }
            
    }
    
    private void formatearIdBanner(){
        String id_banner = "000" + vistaPrincipal.txtIdDocente.getText().trim();
        id_banner = id_banner.substring(id_banner.length() - 8);
        vistaPrincipal.txtIdDocente.setText(id_banner);
    }
    
    private void reiniciarCRN(){
        Crn.reiniciarAtributos();
        vistaPrincipal.txtCrn.setText("");
        vistaPrincipal.lblCrn.setText("CRN MATERIA");
        vistaPrincipal.lblCrn.setForeground(mDatos.BLACK);
    }
    
    private void reiniciarIdBanner(){
        Docente.limpiar();
        vistaPrincipal.txtIdDocente.setText("");
        vistaPrincipal.lblDocente.setText("ID DOCENTE");
        vistaPrincipal.lblDocente2.setText("");
        vistaPrincipal.lblDocente.setForeground(mDatos.BLACK);
    }
    
    private void buscarCRN(){
        int crn = vistaPrincipal.txtCrn.getText().length();
        if(crn > 3 && crn < 7){
            if(Crn.consultarRegistroByCrn(vistaPrincipal.txtCrn.getText().trim())){
                vistaPrincipal.lblCrn.setText(Crn.getNombre());
                vistaPrincipal.lblCrn.setForeground(mDatos.DARK_GREEN);
            }else{
                Crn.reiniciarAtributos();
                vistaPrincipal.lblCrn.setText("CRN MATERIA INCORRECTO");
                vistaPrincipal.lblCrn.setForeground(mDatos.RED);
            }
        }else{
            reiniciarCRN();
        }
    }
    
    public void registrarAsistencia(String tipo){
        dRegistro.limpiar();
        dRegistro.setDocente_banner(Docente.getId_banner());
        dRegistro.setDocente_nombre(Docente.getNombre() + " " + 
                Docente.getApellido_paterno() + " " + Docente.getApellido_materno());
//        dRegistro.setCrn(Crn.getCrn());
        dRegistro.setCrn(vistaPrincipal.txtCrn.getText());
//        dRegistro.setCrn_descripcion(Crn.getNombre());
        dRegistro.setCrn_descripcion(vistaPrincipal.lblCrn.getText());
        dRegistro.setTipo_registro(tipo);
        String[] arreglo = vistaPrincipal.lblFecha.getText().trim().split(" ");
        dRegistro.setDia(mDatos.DayToInt(arreglo[0]));
        dRegistro.setFecha_hora_reg(Fecha.getFechaHoraMysqlActual());
        dRegistro.setGrupo(null);
        dRegistro.setFecha_modificacion(Fecha.getFechaHoraMysqlActual());
        dRegistro.setAdicional1(null);
        dRegistro.setAdicional2(null);
        dRegistro.setAdicional3(null);
        dRegistro.setAdicional4(null);
        if(validacionRegistroDocente()){
            dRegistro.agregar();
            reiniciarCRN();
            reiniciarIdBanner();
            consultarRegistros();
            Sonido.correcto();
            JOptionPane.showMessageDialog(vistaPrincipal,  mDatos.MensajeRegistroCorrecto, 
                    "Operacion realizada correctamente", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void consultarRegistros(){
        vistaPrincipal.dtmRegistroAsistencia = llenarTablaRegistros(
                vistaPrincipal.dtmRegistroAsistencia, "NombreCamposTablaRegistroDocente");
        reSizeTableRegistroDocente();
    }
    
    public DefaultTableModel llenarTablaRegistros(DefaultTableModel model, String campos){
        int tamaño = model.getRowCount();
        if(tamaño != 0)
            for (int i = 0;i < tamaño; i++) 
                model.removeRow(0);
        ArrayList<String[]> lista = dRegistro.consultar();
        String[] arreglo = Config.consultarConfiguracion(campos).split(",");
        Vector<String> v = new Vector<String>( Arrays.asList( arreglo ));
        model.setColumnCount(v.size());
        model.setColumnIdentifiers(v);
        for(String[] s : lista){
            model.addRow(s);
        }
        return model;
    }
    
    public void reSizeTableRegistroDocente(){
        TableColumnModel columnModel = vistaPrincipal.jtListadoAsistencia.getColumnModel();
        String[] arreglo = Config.consultarConfiguracion("SizeCamposTablaRegistroDocente").split(",");
        for (int i = 0; i < arreglo.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(Integer.parseInt(arreglo[i]));
        }
    }
    
        // Validaciones para editar o crear registros
    public boolean validacionRegistroDocente(){
        boolean error = true;
        mValidaciones validar = new mValidaciones();
        //Validacion de Calendario
        if(!validar.cadenaNoVacia(vistaPrincipal.txtIdDocente.getText().trim(), "ID DOCENTE")) 
            error = false;
        else if(!validar.cadenaNoMayor(vistaPrincipal.txtIdDocente.getText().trim(),8,"ID DOCENTE")) //Nombre no mayor a 8 caracteres
            error = false;
        else if(!validar.cadenaNoMenor(vistaPrincipal.txtIdDocente.getText().trim(),8,"ID DOCENTE")) //Nombre no mayor a 8 caracteres
            error = false;
        else if(!validar.existeRegistroDB(Docente.contarDocentes(vistaPrincipal.txtIdDocente.getText().trim()), 
                vistaPrincipal.txtIdDocente.getText().trim(), "ID DOCENTE", "DOCENTE"))
            error = false;
        
        if(!error){
            Sonido.incorrecto();
            JOptionPane.showMessageDialog(vistaPrincipal,  mDatos.Error, "Validacion Error", JOptionPane.ERROR_MESSAGE);
        }
            
        return error;
    }
    
}
