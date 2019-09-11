/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import java.util.Arrays;
import Modelos.mConfiguracion;
import Modelos.mDateTime;
import Modelos.mDatos;
import Modelos.mDocentes;
import Modelos.mValidaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import vistas.jfdDocentes;
/**
 *
 * @author Frexal
 */
public class ctrDocentes implements ActionListener, MouseListener{
    
    int idDocente = 0, idMaterias = 0, banProces = 0;
    final private jfdDocentes vistaDocentes;
    mDocentes Docente = new mDocentes();
    mConfiguracion Config = new mConfiguracion();
    
    public ctrDocentes(jfdDocentes vistaDocentes){
        
        this.vistaDocentes = vistaDocentes;
        habilitarComponentesGuardarCancelar(true, true);
        
        this.vistaDocentes.btnAgregar.addActionListener(this);
        this.vistaDocentes.btnEditar.addActionListener(this);
        this.vistaDocentes.btnGuardar.addActionListener(this);
        this.vistaDocentes.btnEliminar.addActionListener(this);
        this.vistaDocentes.btnCancelar.addActionListener(this);
        this.vistaDocentes.btnBusqueda.addActionListener(this);
        this.vistaDocentes.btnDocentes.addActionListener(this);
        this.vistaDocentes.btnMaterias.addActionListener(this);
        this.vistaDocentes.tblListadoDocentes.addMouseListener(this);
        this.vistaDocentes.tblListadoMaterias.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaDocentes.btnAgregar){
            //Accion Boton Agregar
            limpiarControles(vistaDocentes.btnDocentes.isSelected(),
                    vistaDocentes.btnMaterias.isSelected());
            habilitarComponentesAgregarEditar(vistaDocentes.btnDocentes.isSelected(),
                    vistaDocentes.btnMaterias.isSelected());
        }else if (e.getSource() == vistaDocentes.btnEditar){
            //Accion Boton Editar
            habilitarComponentesAgregarEditar(vistaDocentes.btnDocentes.isSelected(),
                    vistaDocentes.btnMaterias.isSelected());
        }else if (e.getSource() == vistaDocentes.btnGuardar){
            //Accion Boton Guardar
            if(vistaDocentes.btnDocentes.isSelected()){
                //Acciones dirijidas a Docentes
                if(idDocente > 0){
                    //Validar Campos
                    if(validacionCamposDocente(idDocente, vistaDocentes.txtIdDocente.getText(), vistaDocentes.txtNombre.getText(),
                        vistaDocentes.txtApPaterno.getText(), vistaDocentes.txtApMaterno.getText(),
                        vistaDocentes.txtComentarioDocente.getText(),"Editar")){
                        // Editar Docente
                        editar(idDocente, vistaDocentes.txtIdDocente.getText().trim(), vistaDocentes.txtNombre.getText().trim(),
                            vistaDocentes.txtApPaterno.getText().trim(), vistaDocentes.txtApMaterno.getText().trim(),
                            mDatos.StringToIntBan(vistaDocentes.cmbEstado.getSelectedItem().toString().trim()), 
                            vistaDocentes.txtComentarioDocente.getText().trim());
                        habilitarComponentesGuardarCancelar(vistaDocentes.btnDocentes.isSelected(),
                            vistaDocentes.btnMaterias.isSelected());
                    }
                }else{
                    //Validar Campos
                    if(validacionCamposDocente(idDocente, vistaDocentes.txtIdDocente.getText(), vistaDocentes.txtNombre.getText(),
                        vistaDocentes.txtApPaterno.getText(), vistaDocentes.txtApMaterno.getText(),
                        vistaDocentes.txtComentarioDocente.getText(),"Agregar")){
                        //Crear Docente
                        crear(vistaDocentes.txtIdDocente.getText().trim(), vistaDocentes.txtNombre.getText().trim(),
                            vistaDocentes.txtApPaterno.getText().trim(), vistaDocentes.txtApMaterno.getText().trim(),
                            mDatos.StringToIntBan(vistaDocentes.cmbEstado.getSelectedItem().toString().trim()), 
                            vistaDocentes.txtComentarioDocente.getText().trim());
                        habilitarComponentesGuardarCancelar(vistaDocentes.btnDocentes.isSelected(),
                            vistaDocentes.btnMaterias.isSelected());
                    }
                }
            }else if(vistaDocentes.btnMaterias.isSelected()){
                // Agregar Materias
                agregarMateria(vistaDocentes.txtIdDocente.getText());
                habilitarComponentesGuardarCancelar(vistaDocentes.btnDocentes.isSelected(),
                            vistaDocentes.btnMaterias.isSelected());
            }
        }else if (e.getSource() == vistaDocentes.btnEliminar){
            //Accion Boton Eliminar
            if(vistaDocentes.btnDocentes.isSelected()){
                int resp = JOptionPane.showConfirmDialog(vistaDocentes, 
                    "Se eliminara el Docente :\"" + vistaDocentes.txtNombre.getText() + 
                    "\"¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                if(resp == 0)
                    if(eliminar(idDocente))
                        habilitarComponentesGuardarCancelar(vistaDocentes.btnDocentes.isSelected(),
                            vistaDocentes.btnMaterias.isSelected());
            }
        }else if (e.getSource() == vistaDocentes.btnCancelar){
            //Accion Boton Cancelar
            vistaDocentes.btnDocentes.setSelected(true);
            vistaDocentes.btnMaterias.setSelected(false);
            habilitarComponentesGuardarCancelar(true,true);
        }else if (e.getSource() == vistaDocentes.btnBusqueda){
            vistaDocentes.tblListadoDocentes.setModel(consultarBusqueda(vistaDocentes.dtmDocentes, 
                "NombreCamposTablaDocente", asignarFiltro(vistaDocentes.cmbBusqueda.getSelectedIndex(), 
                "CamposFiltroDocentes"), vistaDocentes.txtBusqueda.getText()));
        }else if (e.getSource() == vistaDocentes.btnDocentes){
            //Accion Boton Docentes
            if(vistaDocentes.btnDocentes.isSelected())
                vistaDocentes.btnMaterias.setSelected(false);
            else if(!vistaDocentes.btnDocentes.isSelected() && vistaDocentes.btnMaterias.isEnabled()){
                vistaDocentes.btnMaterias.setSelected(true);
            }
            else
                vistaDocentes.btnDocentes.setSelected(true);
            habilitarBtnEditarEliminar();
        }else if (e.getSource() == vistaDocentes.btnMaterias){ 
            //Accion Boton Materias
            if(vistaDocentes.btnMaterias.isSelected())
                vistaDocentes.btnDocentes.setSelected(false);
            else if(!vistaDocentes.btnMaterias.isSelected())
                vistaDocentes.btnDocentes.setSelected(true);
            habilitarBtnEditarEliminar();
        }
    }
    
     @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vistaDocentes.tblListadoDocentes && vistaDocentes.btnDocentes.isEnabled() && banProces == 0){
            banProces = 1;
            int id = Integer.parseInt(vistaDocentes.tblListadoDocentes.
                    getValueAt(vistaDocentes.tblListadoDocentes.getSelectedRow(), 
                            vistaDocentes.tblListadoDocentes.getColumnCount()-1).toString());
           
            if(idDocente != id){
                llenarCamposDocente(id);
                reSizeTableMaterias();
                vistaDocentes.btnEditar.setEnabled(true);
                vistaDocentes.btnEliminar.setEnabled(true);
            }
            banProces = 0;
        }else if(e.getSource() == vistaDocentes.tblListadoMaterias && vistaDocentes.btnMaterias.isEnabled() 
                && vistaDocentes.tblListadoMaterias.isEnabled() && (banProces == 0)){
            banProces = 1;
            int id = Integer.parseInt(vistaDocentes.tblListadoMaterias.
                    getValueAt(vistaDocentes.tblListadoMaterias.getSelectedRow(), 
                            vistaDocentes.tblListadoMaterias.getColumnCount()-1).toString());
            if(idMaterias != id){
                llenarCamposMateria(id);
                vistaDocentes.btnEditar.setEnabled(true);
                vistaDocentes.btnEliminar.setEnabled(true);
            }
            banProces = 0;
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
        this.vistaDocentes.setVisible(true);
    }
    
    private void habilitarComponentesAgregarEditar(boolean docentes, boolean materias){
        vistaDocentes.btnAgregar.setEnabled(false);
        vistaDocentes.btnEditar.setEnabled(false);
        vistaDocentes.btnGuardar.setEnabled(true);
        vistaDocentes.btnEliminar.setEnabled(false);
        vistaDocentes.btnCancelar.setEnabled(true);
        vistaDocentes.btnBusqueda.setEnabled(false);
        vistaDocentes.txtBusqueda.setEnabled(false);
        vistaDocentes.cmbBusqueda.setEnabled(false);
        
        if(docentes){
            vistaDocentes.txtIdDocente.setEnabled(true);
            vistaDocentes.txtNombre.setEnabled(true);
            vistaDocentes.txtApPaterno.setEnabled(true);
            vistaDocentes.txtApMaterno.setEnabled(true);
            vistaDocentes.cmbEstado.setEnabled(true);
            vistaDocentes.txtComentarioDocente.setEnabled(true);
            vistaDocentes.tblListadoDocentes.setEnabled(false);
        }
        if(materias){
            vistaDocentes.txtCRN.setEnabled(true);
            vistaDocentes.txtDescripcion.setEnabled(true);
            vistaDocentes.cmbDia.setEnabled(true);
            vistaDocentes.cmbCalendario.setEnabled(true);
            vistaDocentes.txtGrupo.setEnabled(true);
            vistaDocentes.spiHoraIni.setEnabled(true);
            vistaDocentes.spiHoraFin.setEnabled(true);
            vistaDocentes.txtComentarioDocente.setEnabled(true);
            vistaDocentes.tblListadoMaterias.setEnabled(false);
        }
    }
    
    private void habilitarComponentesGuardarCancelar(boolean docentes, boolean materias){
        vistaDocentes.btnAgregar.setEnabled(true);
        vistaDocentes.btnEditar.setEnabled(false);
        vistaDocentes.btnGuardar.setEnabled(false);
        vistaDocentes.btnEliminar.setEnabled(false);
        vistaDocentes.btnCancelar.setEnabled(true);
        vistaDocentes.btnDocentes.setEnabled(true);
        vistaDocentes.btnMaterias.setEnabled(false);
        vistaDocentes.btnBusqueda.setEnabled(true);
        vistaDocentes.txtBusqueda.setEnabled(true);
        vistaDocentes.cmbBusqueda.setEnabled(true);
        cargarComboCalendario();
        limpiarControles(docentes,materias);

        if(docentes){
            vistaDocentes.txtIdDocente.setEnabled(false);
            vistaDocentes.txtNombre.setEnabled(false);
            vistaDocentes.txtApPaterno.setEnabled(false);
            vistaDocentes.txtApMaterno.setEnabled(false);
            vistaDocentes.cmbEstado.setEnabled(false);
            vistaDocentes.txtComentarioDocente.setEnabled(false);
            vistaDocentes.tblListadoDocentes.setEnabled(true);
            vistaDocentes.cmbBusqueda.setModel(llenarCombo(vistaDocentes.dcmBusqueda, "NombreCamposFiltroDocentes"));
            vistaDocentes.tblListadoDocentes.setModel(consultar(vistaDocentes.dtmDocentes,"NombreCamposTablaDocente"));
            vistaDocentes.jspListadoDocentes.setViewportView(vistaDocentes.tblListadoDocentes);
            vistaDocentes.tblListadoDocentes.getTableHeader().setReorderingAllowed(false);
            reSizeTable();
        }
        if(materias){
            vistaDocentes.txtCRN.setEnabled(false);
            vistaDocentes.txtDescripcion.setEnabled(false);
            vistaDocentes.cmbDia.setEnabled(false);
            vistaDocentes.cmbCalendario.setEnabled(false);
            vistaDocentes.txtGrupo.setEnabled(false);
            vistaDocentes.spiHoraIni.setEnabled(false);
            vistaDocentes.spiHoraFin.setEnabled(false);
            vistaDocentes.txtComentarioDocente.setEnabled(false);
            vistaDocentes.tblListadoMaterias.setEnabled(true);
            consultarMaterias(vistaDocentes.txtIdDocente.getText());
            reSizeTableMaterias();
        }
    }
    
    public void limpiarControles(boolean docente, boolean materia){
       
        if(docente){
             //Docente
            vistaDocentes.txtIdDocente.setText("");
            vistaDocentes.txtNombre.setText("");
            vistaDocentes.txtApPaterno.setText("");
            vistaDocentes.txtApMaterno.setText("");
            vistaDocentes.cmbEstado.setSelectedIndex(0);
            vistaDocentes.txtComentarioDocente.setText("");
            idDocente = 0;
        }else if(materia){
            //Materias
            vistaDocentes.txtCRN.setText("");
            vistaDocentes.txtDescripcion.setText("");
            vistaDocentes.cmbDia.setSelectedIndex(0);
            vistaDocentes.cmbCalendario.setSelectedIndex(0);
            vistaDocentes.txtGrupo.setText("");
            reiniciarSpinFecha();
            vistaDocentes.txtComentarioMateria.setText("");
            //busqueda
            idMaterias = 0;
        }
        //vistaDocentes.cmbBusqueda.setSelectedIndex(0);
        vistaDocentes.txtBusqueda.setText("");
        //ID variables
    }
    
    public void habilitarBtnEditarEliminar(){
        if(vistaDocentes.btnDocentes.isSelected() && (idDocente > 0)){
            vistaDocentes.btnEditar.setEnabled(true);
            vistaDocentes.btnEliminar.setEnabled(true);
        }else if (vistaDocentes.btnMaterias.isSelected() && (idMaterias > 0)){
            vistaDocentes.btnEditar.setEnabled(true);
            vistaDocentes.btnEliminar.setEnabled(true);
        }else{
            vistaDocentes.btnEditar.setEnabled(false);
            vistaDocentes.btnEliminar.setEnabled(false);
        }
    }
    
    public void reSizeTable(){
        TableColumnModel columnModel = vistaDocentes.tblListadoDocentes.getColumnModel();
        String[] arreglo = Config.consultarConfiguracion("SizeCamposTablaDocentes").split(",");
        for (int i = 0; i < arreglo.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(Integer.parseInt(arreglo[i]));
        }
    }
    
    public boolean crear(String id_banner, String nombre, String apellido_paterno,
                 String apellido_materno, int estado, String comentario ){
         mDocentes mDocente = new mDocentes(id_banner, nombre, apellido_paterno,
                 apellido_materno, estado, comentario);
        return mDocente.agregar();
    }
    
    public boolean editar(int id, String id_banner, String nombre, 
            String apellido_paterno, String apellido_materno, int estado, String comentario ){
        mDocentes mDocente = new mDocentes(id, id_banner, nombre, apellido_paterno,
                 apellido_materno, estado, comentario);
        return mDocente.editar();
    }
    
    public DefaultTableModel consultar(DefaultTableModel model, String campos ){
        int tamaño = model.getRowCount();
        if(tamaño != 0)
            for (int i = 0;i < tamaño; i++) 
                model.removeRow(0);
        
        ArrayList<String[]> lista = Docente.consultar();
        String[] arreglo = Config.consultarConfiguracion(campos).split(",");
        Vector<String> v = new Vector<String>( Arrays.asList( arreglo ));
        model.setColumnCount(v.size());
        model.setColumnIdentifiers(v);
        
        for(String[] s : lista){
            model.addRow(s);
        }
        return model;
    }
    
    public DefaultTableModel consultarBusqueda(DefaultTableModel model, String campos, String filtros, String valores ){
        int tamaño = model.getRowCount();
        if(tamaño != 0)
            for (int i = 0;i < tamaño; i++) 
                model.removeRow(0);
        ArrayList<String[]> lista = Docente.consultarFiltrado(filtros, valores);
        String[] arreglo = Config.consultarConfiguracion(campos).split(",");
        Vector<String> v = new Vector<String>( Arrays.asList( arreglo ));
        model.setColumnCount(v.size());
        model.setColumnIdentifiers(v);
        for(String[] s : lista){
            model.addRow(s);
        }
        return model;
    }
    
    public DefaultComboBoxModel<String> llenarCombo(DefaultComboBoxModel<String> model, String campos){
        if(model!= null){
            model.removeAllElements();
        }
        String[] lista = Config.consultarConfiguracion(campos).split(",");
        
        for (String c : lista) {
            model.addElement(c.toString());
        }
        return model;
    } 
    
    public boolean eliminar(int id){
        mDocentes mDocente = new mDocentes();
        mDocente.setId(id);
        return mDocente.eliminar();
    }
    
    public String asignarFiltro(int id, String campos){
        String[] arreglo = Config.consultarConfiguracion(campos).split(",");
        return arreglo[id];
    }
    
    public void llenarCamposDocente(int id){
        String[] lista = Docente.consultarDocente(id);
        this.idDocente = Integer.parseInt(lista[0]);
        vistaDocentes.txtIdDocente.setText(lista[1]);
        vistaDocentes.txtNombre.setText(lista[2]);
        vistaDocentes.txtApPaterno.setText(lista[3]);
        vistaDocentes.txtApMaterno.setText(lista[4]);
        vistaDocentes.cmbEstado.setSelectedIndex(mDatos.StringToIntBan(lista[5]));
        vistaDocentes.txtComentarioDocente.setText(lista[6]);
        consultarMaterias(lista[1]);
        cargarComboCalendario();
        
        if(vistaDocentes.txtIdDocente.getText().trim().length() > 0)
                vistaDocentes.btnMaterias.setEnabled(true);
            else{
                vistaDocentes.btnMaterias.setEnabled(false);
                vistaDocentes.btnMaterias.setSelected(false);
                vistaDocentes.btnDocentes.setSelected(true);
            }
    }
    
    public void llenarCamposMateria(int id){
        String[] lista = consultarMateria(id);
        this.idMaterias = Integer.parseInt(lista[0]);
        vistaDocentes.txtCRN.setText(lista[1]);
        vistaDocentes.txtDescripcion.setText(lista[2]);
        vistaDocentes.cmbDia.setSelectedIndex(mDatos.StringToIntBan(lista[4]));
        vistaDocentes.cmbCalendario.setSelectedItem(lista[10]);
        vistaDocentes.txtGrupo.setText(lista[9]);
        mDateTime d = new mDateTime();
        vistaDocentes.spiHoraFin.setValue(d.convertStringToHour(lista[7]));
        vistaDocentes.spiHoraIni.setValue(d.convertStringToHour(lista[8]));
        vistaDocentes.txtComentarioMateria.setText(lista[11]);
        vistaDocentes.cmbEstado.setSelectedIndex(mDatos.StringToIntBan(lista[5]));
        
        if(vistaDocentes.txtIdDocente.getText().trim().length() > 0)
                vistaDocentes.btnMaterias.setEnabled(true);
            else{
                vistaDocentes.btnMaterias.setEnabled(false);
                vistaDocentes.btnMaterias.setSelected(false);
                vistaDocentes.btnDocentes.setSelected(true);
            }
    }
    
    public boolean validacionCamposDocente(int id, String id_banner, String nombre, 
            String apPaterno, String apMaterno, String comentario, String evento){
        mValidaciones validar = new mValidaciones();
        boolean error = true;
        
        //Validacion de Calendario
        if(!validar.cadenaNoVacia(id_banner, "ID Docente")) // Nombre no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(id_banner,8,"ID Docente")) //Nombre no mayor a 200 caracteres
            error = false;
        else if(!validar.unicoRegistroDB(Docente.consultarExistente(id_banner, id), id_banner, "ID Docente", "Docentes"))
            error = false;
        else if(!validar.cadenaNoVacia(nombre, "Nombre")) // Nombre no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(nombre,200,"Nombre")) //Nombre no mayor a 200 caracteres
            error = false;
        else if(!validar.cadenaNoVacia(apPaterno, "Apellido Paterno")) // Apellido Paterno no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(apPaterno,200,"Apellido Paterno")) //Apellido Paterno no mayor a 200 caracteres
            error = false;
        else if(!validar.cadenaNoVacia(apMaterno, "Apellido Materno")) // Apellido Materno no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(apMaterno,200,"Apellido Materno")) //Apellido Materno no mayor a 200 caracteres
            error = false;
        else if(!validar.cadenaNoMayor(comentario,500,"Comentario")) //Comentario no mayor a 500 caracteres
            error = false;
        else if(evento.equals("Editar") && !validar.enteroNoMenor("ID", id, 1)){
            mDatos.Error = mDatos.ErrorDelSistema;
            error = false;
        }
        
        if(!error)
            JOptionPane.showMessageDialog(vistaDocentes,  mDatos.Error, "Validacion Error", JOptionPane.ERROR_MESSAGE);
        return error;
    }
    
    //metodos materias
    
    public String[] consultarMateria(int id){
        String[] arreglo = {"","","","","","",""};
        ctrHorarioMateriaDocente cMaterias = new ctrHorarioMateriaDocente();
        return arreglo;
    }
    
    public void consultarMaterias(String id_banner){
        ctrHorarioMateriaDocente cMaterias = new ctrHorarioMateriaDocente();
        vistaDocentes.dtmMaterias = cMaterias.consultarBusqueda(vistaDocentes.dtmMaterias, 
                "NombreCamposTablaMaterias", "id_docente", id_banner);
        vistaDocentes.jspListadoMaterias.setViewportView(vistaDocentes.tblListadoMaterias);
        vistaDocentes.tblListadoMaterias.getTableHeader().setReorderingAllowed(false);
    }
    
    public void agregarMateria(String id_banner){
        ctrHorarioMateriaDocente cMaterias = new ctrHorarioMateriaDocente();
        String calendario = vistaDocentes.cmbCalendario.getSelectedItem().toString();
        String[] arreglo = Config.consultarConfiguracion(calendario).split("\\|");
        mDateTime d = new mDateTime();
        arreglo[0] = d.fechaStringToMysql(arreglo[0]);
        arreglo[1] = d.fechaStringToMysql(arreglo[1]);
        String horaIni = vistaDocentes.spiHoraIni.getValue().toString();
        String horaFin = vistaDocentes.spiHoraFin.getValue().toString();
        horaIni = horaIni.substring(horaIni.length()-17, horaIni.length()-9);
        horaFin = horaFin.substring(horaFin.length()-17, horaFin.length()-9);
        
        cMaterias.crear(vistaDocentes.txtCRN.getText().trim(), vistaDocentes.txtDescripcion.getText().trim(),
                id_banner, vistaDocentes.cmbDia.getSelectedIndex(), arreglo[0], arreglo[1], 
                horaIni.trim(), horaFin.trim(),vistaDocentes.txtGrupo.getText().trim(), calendario.trim(),
                vistaDocentes.txtComentarioMateria.getText().trim());
    }
    
    public void cargarComboCalendario(){
        ctrHorarioMateriaDocente cMaterias = new ctrHorarioMateriaDocente();
        vistaDocentes.cmbCalendario.setModel(cMaterias.llenarComboCalendario(vistaDocentes.dcmCalendario, 4));
    }
    
    public void reiniciarSpinFecha(){
        mDateTime d = new mDateTime();
        vistaDocentes.spiHoraFin.setValue(d.convertStringToHour("00:00:00"));
        vistaDocentes.spiHoraIni.setValue(d.convertStringToHour("00:00:00"));
    }
    
    public void reSizeTableMaterias(){
        TableColumnModel columnModel = vistaDocentes.tblListadoMaterias.getColumnModel();
        String[] arreglo = Config.consultarConfiguracion("SizeCamposTablaMaterias").split(",");
        for (int i = 0; i < arreglo.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(Integer.parseInt(arreglo[i]));
        }
    }
   
}
