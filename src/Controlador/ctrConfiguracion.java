// Tipo 0 Bloqueo Docentes
// Tipo 1 Bloqueo CRN
// Tipo 2 Bloqueo Contraseña
// Tipo 3 Contraseña
// Tipo 4 Calendario
// Tipo 5 Asueto
// Tipo 6 NombreCampos de Tablas
// Tipo 7 Campos de Tablas
// Tipo 8 TamañoCampos de Tablas
// Tipo 9 NombreCamposFiltro de Tablas
// Tipo 10 CamposFiltro de Tablas
// Tipo 11 Limite de registros por tabla

package Controlador;

import Modelos.mConfiguracion;
import Modelos.mDateTime;
import Modelos.mDatos;
import Modelos.mValidaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import vistas.jfdConfiguracion;

/**
 *
 * @author Asesor
 */
public class ctrConfiguracion implements ActionListener, ChangeListener, MouseListener{
    
    final private jfdConfiguracion vistaConfiguracion;
    final private mDateTime modelDate;
    mConfiguracion Config = new mConfiguracion();
    
    int idCalendario = 0, idAsueto = 0, idMateria = 0;
    String status = "";
    
    public ctrConfiguracion(jfdConfiguracion fConfiguracion ){
        
        //Inicializa la vista y el modelo
        this.vistaConfiguracion = fConfiguracion;
        this.modelDate = new mDateTime();
        //atributos
        this.idCalendario = 0;
        //activacion de componentesss
        habilitarComponentsGuardarCancelar(0);
        habilitarComponentsGuardarCancelar(1);
        habilitarComponentsGuardarCancelar(2);
        
        //Asignacion de eventos
        //Barra de tareas
        this.vistaConfiguracion.btnAgregar.addActionListener(this);
        this.vistaConfiguracion.btnEditar.addActionListener(this);
        this.vistaConfiguracion.btnGuardar.addActionListener(this);
        this.vistaConfiguracion.btnEliminar.addActionListener(this);
        this.vistaConfiguracion.btnCancelar.addActionListener(this);
        // Taps
        this.vistaConfiguracion.jtpConfiguracion.addChangeListener(this);
        //Lista Calendario 
        this.vistaConfiguracion.lstCalendario.addMouseListener(this);
        //Lista Asueto
        this.vistaConfiguracion.lstAsueto.addMouseListener(this);
        //Bloqueo
        this.vistaConfiguracion.chkPass.addChangeListener(this);
        
    }
    
    //Eventos cambio
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == vistaConfiguracion.jtpConfiguracion){
            switch (vistaConfiguracion.jtpConfiguracion.getSelectedIndex()){
                case 2:
                    habilitarComponentsBloqueo();
                    break;
                default:
                    habilitarComponentsGuardarCancelar(0);
                    habilitarComponentsGuardarCancelar(1);
                    break;
            }
        }
        else if(e.getSource() == vistaConfiguracion.chkPass){
            if(vistaConfiguracion.chkPass.getSelectedObjects() == null){
                vistaConfiguracion.txtPass.setEnabled(false);
                vistaConfiguracion.txtPassConfirm.setEnabled(false);
            }
            else {
                vistaConfiguracion.txtPass.setEnabled(true);
                vistaConfiguracion.txtPassConfirm.setEnabled(true);
            }
        }
        
    } 
    
    //Eventos Accion
    @Override
    public void actionPerformed(ActionEvent e) {
        //Eventos del boton agregar
        if (e.getSource() == vistaConfiguracion.btnAgregar){
            int estado = this.vistaConfiguracion.jtpConfiguracion.getSelectedIndex();
            habilitarComponentsAgregarEditar(estado,"Agregar");
        }
        //Eventos del boton Editar
        else if (e.getSource() == vistaConfiguracion.btnEditar){
            int estado = this.vistaConfiguracion.jtpConfiguracion.getSelectedIndex();
            habilitarComponentsAgregarEditar(estado,"Editar");
        }
        //Eventos del boton Guardar
        else if (e.getSource() == vistaConfiguracion.btnGuardar){
            int estado = this.vistaConfiguracion.jtpConfiguracion.getSelectedIndex();
            String nombre,datos;
            int tipo;
            switch (estado) {
                case 0:
                    nombre = vistaConfiguracion.txtNombreCalendario.getText();
                    datos = modelDate.formatDate(vistaConfiguracion.jdcFechaInicio.getDate()) +
                                " | " + modelDate.formatDate(vistaConfiguracion.jdcFechaFin.getDate());
                    tipo = 4;
                    
                    if (idCalendario > 0){
                        //Edicion
                        if(validacionCampos(idCalendario, nombre, datos, "Editar", estado)){
                            if(editarConfiguracion(idCalendario, nombre, datos)){
                                habilitarComponentsGuardarCancelar(estado);
                            }else{
                                JOptionPane.showMessageDialog(vistaConfiguracion, mDatos.ErrorAlEditar, "Mensaje", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        
                    }else{
                        //Creacion
                        if(validacionCampos(idCalendario, nombre, datos, "Agregar", estado)){
                            if(nuevaConfiguracion(nombre,datos,tipo)){
                                habilitarComponentsGuardarCancelar(estado);
                            }else{
                                JOptionPane.showMessageDialog(vistaConfiguracion,  mDatos.ErrorAlAgregar, "Mensaje", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    
                    break;
                case 1:
                    nombre = vistaConfiguracion.txtNombreAsueto.getText();
                    datos = modelDate.formatDate(vistaConfiguracion.jdcFechaAsueto.getDate());
                    tipo = 5;
                    if (idAsueto > 0){
                        //Edicion
                        if(validacionCampos(idAsueto, nombre, datos, "Editar", estado)){
                            if(editarConfiguracion(idAsueto, nombre, datos)){
                                habilitarComponentsGuardarCancelar(estado);
                            }else {
                                JOptionPane.showMessageDialog(vistaConfiguracion,  mDatos.ErrorAlEditar, "Mensaje", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }else{
                        //Creacion
                        if(validacionCampos(idAsueto, nombre, datos, "Agregar", estado)){
                           if(nuevaConfiguracion(nombre, datos, tipo)){
                                habilitarComponentsGuardarCancelar(estado);
                            }else {
                                JOptionPane.showMessageDialog(vistaConfiguracion, mDatos.ErrorAlAgregar, "Mensaje", JOptionPane.ERROR_MESSAGE);
                            } 
                        }
                    }
                    break;
                case 2:
                    if(validacionCampos(0, "", "", "", estado)){
                        if(editarConfiguracion(1, "BloqueoDocentes", mDatos.BooleanToString(vistaConfiguracion.chkBloqueDocente.isSelected())) && 
                            editarConfiguracion(2, "BloqueoCRN", mDatos.BooleanToString(vistaConfiguracion.chkBloqueoCRN.isSelected())) && 
                            editarConfiguracion(3, "BloqueoPass", mDatos.BooleanToString(vistaConfiguracion.chkPass.isSelected())) && 
                            editarConfiguracion(4, "Pass", vistaConfiguracion.txtPass.getText()))
                    habilitarComponentsGuardarCancelar(2);
                    }
                    break;
                default:
                    break;
            }
        }
        //Eventos del boton Eliminar
        else if (e.getSource() == vistaConfiguracion.btnEliminar){
            int estado = this.vistaConfiguracion.jtpConfiguracion.getSelectedIndex();
            int resp;
            switch (estado) {
                case 0:
                    resp = JOptionPane.showConfirmDialog(vistaConfiguracion, 
                            "Se eliminara el calendario :\"" + vistaConfiguracion.txtNombreCalendario.getText() + 
                                    "\"¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                    if(resp == 0)
                        if(eliminar(idCalendario))
                            habilitarComponentsGuardarCancelar(estado);
                    break;
                case 1:
                    resp = JOptionPane.showConfirmDialog(vistaConfiguracion, 
                            "Se eliminara el dia Asueto :\"" + vistaConfiguracion.txtNombreAsueto.getText() + 
                                    "\"¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                    if(resp == 0)
                        if(eliminar(idAsueto))
                            habilitarComponentsGuardarCancelar(resp);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Estas en config" , "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    break;
            }
        }
        else if (e.getSource() == vistaConfiguracion.btnCancelar){
            int estado = this.vistaConfiguracion.jtpConfiguracion.getSelectedIndex();
            habilitarComponentsGuardarCancelar(estado);
        }
    }
    
        @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int estado = this.vistaConfiguracion.jtpConfiguracion.getSelectedIndex();
        if(e.getSource() == vistaConfiguracion.lstCalendario && status.equals("")){
            if(!(vistaConfiguracion.lstCalendario.getSelectedIndex() == -1)){
                String[] arreglo = vistaConfiguracion.lstCalendario.getSelectedValue().split("\\|");
                asignarValores(arreglo, estado);
            } 
            habilitarEdicionEliminar();
        }
        else if(e.getSource() == vistaConfiguracion.lstAsueto && status.equals("")){
            if(!(vistaConfiguracion.lstAsueto.getSelectedIndex() == -1)){
                String[] arreglo = vistaConfiguracion.lstAsueto.getSelectedValue().split("\\|");
                asignarValores(arreglo, estado);
            }
            habilitarEdicionEliminar();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    //Inicio de formulario
    public void showForm(){
        this.vistaConfiguracion.setVisible(true);
    }
    
    //Controles al presionar agregar, editar
    private void habilitarComponentsAgregarEditar(int jpanel, String status){
        vistaConfiguracion.btnAgregar.setEnabled(false);
        vistaConfiguracion.btnEditar.setEnabled(false);
        vistaConfiguracion.btnGuardar.setEnabled(true);
        vistaConfiguracion.btnEliminar.setEnabled(false);
        vistaConfiguracion.btnCancelar.setEnabled(true);
        this.status = status;
        
        switch(jpanel){
            case 0:
                vistaConfiguracion.lstCalendario.setEnabled(false);
                vistaConfiguracion.txtNombreCalendario.setEnabled(true);
                vistaConfiguracion.jdcFechaInicio.setEnabled(true);
                vistaConfiguracion.jdcFechaFin.setEnabled(true);
                if(status.equals("Agregar")){
                    idCalendario = 0;
                    vistaConfiguracion.txtNombreCalendario.setText("");
                    vistaConfiguracion.jdcFechaInicio.setDate(modelDate.getDate());
                    vistaConfiguracion.jdcFechaFin.setDate(modelDate.getDate());
                }
                break;
            case 1:
                vistaConfiguracion.lstAsueto.setEnabled(false);
                vistaConfiguracion.txtNombreAsueto.setEnabled(true);
                vistaConfiguracion.jdcFechaAsueto.setEnabled(true);
                if(status.equals("Agregar")){
                    idAsueto = 0;
                    vistaConfiguracion.txtNombreAsueto.setText("");
                    vistaConfiguracion.jdcFechaAsueto.setDate(modelDate.getDate());
                }
                break;
        }
    }
    
    //Controles al presionar Guardar, Cancelar
    private void habilitarComponentsGuardarCancelar(int jpanel){
        vistaConfiguracion.btnAgregar.setEnabled(true);
        vistaConfiguracion.btnEditar.setEnabled(false);
        vistaConfiguracion.btnGuardar.setEnabled(false);
        vistaConfiguracion.btnEliminar.setEnabled(false);
        vistaConfiguracion.btnCancelar.setEnabled(true);
        this.status = "";
        switch(jpanel){
            case 0:
                vistaConfiguracion.lstCalendario.setEnabled(true);
                vistaConfiguracion.txtNombreCalendario.setEnabled(false);
                vistaConfiguracion.txtNombreCalendario.setText("");
                vistaConfiguracion.jdcFechaInicio.setEnabled(false);
                vistaConfiguracion.jdcFechaInicio.setDate(modelDate.getDate());
                vistaConfiguracion.jdcFechaFin.setEnabled(false);
                vistaConfiguracion.jdcFechaFin.setDate(modelDate.getDate());
                //llenado de list
                vistaConfiguracion.dlmCalendar = consultar(vistaConfiguracion.dlmCalendar, 4);
                vistaConfiguracion.lstCalendario.setModel(vistaConfiguracion.dlmCalendar);
                //Reinicia ID
                idCalendario = 0;
                break;
            case 1:
                vistaConfiguracion.lstAsueto.setEnabled(true);
                vistaConfiguracion.txtNombreAsueto.setEnabled(false);
                vistaConfiguracion.txtNombreAsueto.setText("");
                vistaConfiguracion.jdcFechaAsueto.setEnabled(false);
                vistaConfiguracion.jdcFechaAsueto.setDate(modelDate.getDate());
                //llenado de list
                vistaConfiguracion.dlmAsueto = consultar(vistaConfiguracion.dlmAsueto, 5);
                vistaConfiguracion.lstAsueto.setModel(vistaConfiguracion.dlmAsueto);
                //Reinicia ID
                idAsueto = 0;
                break;
            case 2:
                vistaConfiguracion.chkBloqueDocente.setSelected(mDatos.StringToBoolean(Config.consultarDato(0)));
                vistaConfiguracion.chkBloqueoCRN.setSelected(mDatos.StringToBoolean(Config.consultarDato(1)));
                vistaConfiguracion.chkPass.setSelected(mDatos.StringToBoolean(Config.consultarDato(2)));
                vistaConfiguracion.txtPass.setText(Config.consultarDato(3));
                vistaConfiguracion.txtPassConfirm.setText(Config.consultarDato(3));
                break;
        }
    }
    
    public void habilitarEdicionEliminar(){
        if(idCalendario != 0 || idAsueto != 0){
            vistaConfiguracion.btnEditar.setEnabled(true);
            vistaConfiguracion.btnEliminar.setEnabled(true);
        }
        else{
            vistaConfiguracion.btnEditar.setEnabled(false);
            vistaConfiguracion.btnEliminar.setEnabled(false);
        }
    }
    
    public void habilitarComponentsBloqueo(){
        vistaConfiguracion.btnAgregar.setEnabled(false);
        vistaConfiguracion.btnEditar.setEnabled(false);
        vistaConfiguracion.btnGuardar.setEnabled(true);
        vistaConfiguracion.btnEliminar.setEnabled(false);
        vistaConfiguracion.btnCancelar.setEnabled(false);
        
        vistaConfiguracion.chkBloqueDocente.setSelected(mDatos.StringToBoolean(Config.consultarDato(0)));
        vistaConfiguracion.chkBloqueoCRN.setSelected(mDatos.StringToBoolean(Config.consultarDato(1)));
        vistaConfiguracion.chkPass.setSelected(mDatos.StringToBoolean(Config.consultarDato(2)));
    }
    
    public void asignarValores(String[] array, int tipo){
        switch(tipo){
            case 0:
                vistaConfiguracion.txtNombreCalendario.setText(array[0].trim());
                vistaConfiguracion.jdcFechaInicio.setDate(modelDate.convertStringToDate(array[1].trim()));
                vistaConfiguracion.jdcFechaFin.setDate(modelDate.convertStringToDate(array[2].trim()));
                idCalendario = Integer.parseInt(array[3].trim());
                break;
            case 1:
                vistaConfiguracion.txtNombreAsueto.setText(array[0].trim());
                vistaConfiguracion.jdcFechaAsueto.setDate(modelDate.convertStringToDate(array[1].trim()));
                idAsueto = Integer.parseInt(array[2].trim());
                break;
            default:
                break;
        }
    }
    
    public boolean nuevaConfiguracion(String nombre, String datos, int tipo ){
        mConfiguracion mConfig = new mConfiguracion(nombre, datos, tipo);
        return mConfig.agregar();
    }
    
    public boolean editarConfiguracion(int id, String nombre, String datos ){
        mConfiguracion mConfig = new mConfiguracion();
        mConfig.setId(id);
        mConfig.setNombre(nombre);
        mConfig.setDatos(datos);
        return mConfig.editar();
    }
    
    public DefaultListModel<String> consultar(DefaultListModel<String> model, int tipo){
        if(model!= null){
            model.removeAllElements();
        }
        ArrayList<mConfiguracion> lista = Config.consultar(tipo);
        
        for (mConfiguracion c : lista) {
            model.addElement(c.toString());
        }
        return model;
    } 
    
    public boolean eliminar(int id){
        mConfiguracion mConfig = new mConfiguracion();
        mConfig.setId(id);
        return mConfig.eliminar();
    }
    
    public boolean validacionCampos(int id, String nombre, String datos, String evento, int estado){
        mValidaciones validar = new mValidaciones();
        boolean error = true;
        
        switch (estado) {
            case 0:
                //Validacion de Calendario
                String fechaIni,fechaFin;
                String[] arreglo = datos.split("\\|");
                fechaIni = arreglo[0];
                fechaFin = arreglo[1];
                
                if(!validar.cadenaNoVacia(nombre, "Nombre")) // Nombre no puede ser vacio
                    error = false;
                else if(!validar.cadenaNoMayor(nombre,200,"Nombre")) //Nombre no mayor a 200 caracteres
                    error = false;
                else if(!validar.unicoRegistroDB(Config.consultarExistente(nombre), nombre, "Nombre", "Calendario"))
                    error = false;
                else if(!validar.fechaDesdeCadena("Fecha Inicio", fechaIni)) //Valido en fecha
                    error = false;
                else if(!validar.fechaDesdeCadena("Fecha Fin", fechaFin)) //Valido en fecha
                    error = false;
                else if(!validar.fechaIniMenorIgualFechaFin("Fecha Inicio", fechaIni , "Fecha Fin", fechaFin)) //Fecha inicio menor o igual a Fecha Fin
                    error = false;
                
                else if(evento.equals("Editar") && !validar.enteroNoMenor("ID", id, 1)){
                    mDatos.Error = mDatos.ErrorDelSistema;
                    error = false;
                }
                break;
            case 1:
                //Validacion de Asueto
                if(!validar.cadenaNoVacia(nombre, "Nombre")) // Nombre no puede ser vacio
                    error = false;
                else if(!validar.cadenaNoMayor(nombre,200,"Nombre"))
                    error = false;
                else if(!validar.unicoRegistroDB(Config.consultarExistente(nombre), nombre, "Nombre", "Asueto"))
                    error = false;
                else if(evento.equals("Editar") && !validar.enteroNoMenor("ID", id, 1)){
                    mDatos.Error = mDatos.ErrorDelSistema;
                    error = false;
                }else if(!validar.fechaDesdeCadena("Fecha", datos))
                    error = false;
                break;
            case 2:
                //Validacion de Bloqueo
                if(vistaConfiguracion.chkPass.isSelected() && !validar.cadenaMach("Contraseña",
                        vistaConfiguracion.txtPass.getText(), "Repetir Contraseña", vistaConfiguracion.txtPassConfirm.getText()))
                    error = false;
                break;
            default:
                break;
        }
        if(!error){
            JOptionPane.showMessageDialog(vistaConfiguracion,  mDatos.Error, "Validacion Error", JOptionPane.ERROR_MESSAGE);

        }
        return error;
    }
}
