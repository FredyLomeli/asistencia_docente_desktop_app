/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.mConfiguracion;
import Modelos.mHorarioMateriaDocente;
import java.awt.event.ActionEvent;

import Modelos.mCrn;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import vistas.jfdEditMaterias;
import Modelos.mDateTime;
import Modelos.mDatos;
import Modelos.mDocentes;
import Modelos.mValidaciones;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Asesor
 */
public class ctrHorarioMateriaDocente implements ActionListener, ChangeListener, MouseListener {
    
    final private jfdEditMaterias vistaMaterias;
    mCrn CRN = new mCrn();
    mHorarioMateriaDocente Materia = new mHorarioMateriaDocente();
    mConfiguracion Config = new mConfiguracion();
    mDateTime mFechasHoras = new mDateTime();
    int id_materia = 0, id_materia_docente = 0, banProces = 0;
    String id_docente = "";
    int banDocente = 0;
    
    //Constructor principal
    public ctrHorarioMateriaDocente(jfdEditMaterias vistaMaterias){
        this.vistaMaterias = vistaMaterias;
        // Eventos de botones principales
        this.vistaMaterias.btnNuevo.addActionListener(this);
        this.vistaMaterias.btnEditar.addActionListener(this);
        this.vistaMaterias.btnGuardar.addActionListener(this);
        this.vistaMaterias.btnEliminar.addActionListener(this);
        this.vistaMaterias.btnCancelar.addActionListener(this);
        // Eventos de botones busqueda
        this.vistaMaterias.btnBusquedaMateria.addActionListener(this);
        this.vistaMaterias.btnBuscarMateriasDocente.addActionListener(this);
        // Eventos de selecion del raton jtable
        this.vistaMaterias.tblListadoMateriaDocente.addMouseListener(this);
        this.vistaMaterias.tblListadoMaterias.addMouseListener(this);
        // Eventos de cambio de pestaña
        this.vistaMaterias.jtpMaterias.addChangeListener(this);
        // Evento Combo de calendarios
        this.vistaMaterias.cmbCalendario.addActionListener(this);
        
        habilitarComponentesCancelarGuardar(0);
        habilitarComponentesCancelarGuardar(1);
        limpiezaComponentes(0);
        limpiezaComponentes(1);
    }
    //Constructor para controlador Docente
    public ctrHorarioMateriaDocente(){
        this.vistaMaterias = new jfdEditMaterias(null, true);
    }
    
    //Inicio de formulario
    public void showForm(){
        this.vistaMaterias.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Accion del Boton Nuevo
        if (e.getSource() == vistaMaterias.btnNuevo){
            if(vistaMaterias.jtpMaterias.getSelectedIndex() == 1)
                banDocente = 2;
//            vistaMaterias.lblBanDocente.setText(" La bandera de docente esta = " + banDocente);
            habilitarComponentesAgregarEditar(vistaMaterias.jtpMaterias.getSelectedIndex());
            limpiezaComponentes(vistaMaterias.jtpMaterias.getSelectedIndex());
        } //Accion del Boton Editar
        else if(e.getSource() == vistaMaterias.btnEditar){
            if(vistaMaterias.jtpMaterias.getSelectedIndex() == 1)
                banDocente = 2;
//            vistaMaterias.lblBanDocente.setText(" La bandera de docente esta = " + banDocente);
            habilitarComponentesAgregarEditar(vistaMaterias.jtpMaterias.getSelectedIndex());
        } // Accion del boton Guardar
        else if(e.getSource() == vistaMaterias.btnGuardar){
            // focus en pestaña Materias
            boolean banCorrecto = false;
            if(vistaMaterias.jtpMaterias.getSelectedIndex() == 0){
                if(id_materia != 0){
                    banCorrecto = editarCRN(id_materia);
                }else{
                    banCorrecto = crearCRN();
                }
            }// focus en pestaña Materias Docente
            else if(vistaMaterias.jtpMaterias.getSelectedIndex() == 1){
                if(id_materia_docente != 0){
                    banCorrecto = editarMateria(id_materia_docente);
                }else{
                    banCorrecto = crearMateria();
                }
            }
            // si se guardo sin problemas limpia y habilita componentes
            if(banCorrecto){
                ctrBanDocente();
                habilitarComponentesCancelarGuardar(vistaMaterias.jtpMaterias.getSelectedIndex());
                limpiezaComponentes(vistaMaterias.jtpMaterias.getSelectedIndex());
            }
        } // Accion del boton Eliminar
        else if(e.getSource() == vistaMaterias.btnEliminar){
            // focus en pestaña Materias
            if(vistaMaterias.jtpMaterias.getSelectedIndex() == 0){
                int resp = JOptionPane.showConfirmDialog(vistaMaterias, 
                    "Se eliminara el registro :\"" + vistaMaterias.txtCRN.getText() + " " + 
                    vistaMaterias.txtNombreMateria.getText() + "\"¿Esta seguro?", "Alerta!", 
                    JOptionPane.YES_NO_OPTION);
                if(resp == 0)
                    if(eliminarCRN(id_materia)){
                        habilitarComponentesCancelarGuardar(vistaMaterias.jtpMaterias.getSelectedIndex());
                        limpiezaComponentes(vistaMaterias.jtpMaterias.getSelectedIndex());
                    }
            }// focus en pestaña Materias Docente
            else if(vistaMaterias.jtpMaterias.getSelectedIndex() == 1){
                int resp = JOptionPane.showConfirmDialog(vistaMaterias, 
                    "Se eliminara la materia :\n\"" + vistaMaterias.cmbCRNMateriaDocente.getSelectedItem().toString() +
                            "\"  del docente con ID: \"" + id_docente + "\"\n¿Esta seguro?", "Alerta!", 
                    JOptionPane.YES_NO_OPTION);
                if(resp == 0)
                    if(eliminarMateria(id_materia_docente)){
                        habilitarComponentesCancelarGuardar(vistaMaterias.jtpMaterias.getSelectedIndex());
                        limpiezaComponentes(vistaMaterias.jtpMaterias.getSelectedIndex());
                    }
            }
            
        } // Accion del boton Cancelar
        else if(e.getSource() == vistaMaterias.btnCancelar){
            ctrBanDocente();
            habilitarComponentesCancelarGuardar(vistaMaterias.jtpMaterias.getSelectedIndex());
            limpiezaComponentes(vistaMaterias.jtpMaterias.getSelectedIndex());
        } // Accion del boton Buscar CRN
        else if(e.getSource() == vistaMaterias.btnBusquedaMateria){
             cargarCRN();
        } // Accion del botomn Buscar materias Docente
        else if(e.getSource() == vistaMaterias.btnBuscarMateriasDocente){
             seleccionarDocente();
        } // Accion del combo calendario al seleccionar un calendario
        else if(e.getSource() == vistaMaterias.cmbCalendario){
            if(vistaMaterias.cmbCalendario.isEnabled())
                seleccionarCalendario();
        }
    }
    
     @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == vistaMaterias.jtpMaterias){
            switch(vistaMaterias.jtpMaterias.getSelectedIndex()){
                case 0:
                    banDocente = 0;
//                    vistaMaterias.lblBanDocente.setText(" La bandera de docente esta = " + banDocente);
                    habilitarComponentesCancelarGuardar(1);
                    limpiezaComponentes(1);
                    break;
                case 1:
                    habilitarComponentesCancelarGuardar(0);
                    limpiezaComponentes(0);
                    this.vistaMaterias.btnNuevo.setEnabled(false);
                    this.vistaMaterias.btnGuardar.setEnabled(false);
                    this.vistaMaterias.btnCancelar.setEnabled(false);
                    break;
                default:
                    break;
            }
        }else {
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vistaMaterias.tblListadoMaterias && banProces == 0){
            banProces = 1;
            int id = Integer.parseInt(vistaMaterias.tblListadoMaterias.
                    getValueAt(vistaMaterias.tblListadoMaterias.getSelectedRow(), 
                            vistaMaterias.tblListadoMaterias.getColumnCount()-1).toString());
           
            if(id_materia != id){
                llenarCamposCRN(id);
                vistaMaterias.btnEditar.setEnabled(true);
                vistaMaterias.btnEliminar.setEnabled(true);
            }
            banProces = 0;
        }else if(e.getSource() == vistaMaterias.tblListadoMateriaDocente && banProces == 0){
            banProces = 1;
            int id = Integer.parseInt(vistaMaterias.tblListadoMateriaDocente.
                    getValueAt(vistaMaterias.tblListadoMateriaDocente.getSelectedRow(), 
                            vistaMaterias.tblListadoMateriaDocente.getColumnCount()-1).toString());
            if(id_materia_docente != id){
                llenarCamposMateria(id);
                vistaMaterias.btnEditar.setEnabled(true);
                vistaMaterias.btnEliminar.setEnabled(true);
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
    
    private void habilitarComponentesAgregarEditar(int index){
        this.vistaMaterias.btnNuevo.setEnabled(false);
        this.vistaMaterias.btnEditar.setEnabled(false);
        this.vistaMaterias.btnGuardar.setEnabled(true);
        this.vistaMaterias.btnEliminar.setEnabled(false);
        this.vistaMaterias.btnCancelar.setEnabled(true);
        switch (index){
            case 0:
                //Limpieza Materias
                this.vistaMaterias.txtCRN.setEditable(true);
                this.vistaMaterias.txtNombreMateria.setEditable(true);
                this.vistaMaterias.cmbEstado.setEnabled(true);
                break;
            case 1:
                //Limpieza Materias Docente
                this.vistaMaterias.txtComentarioMateria.setEditable(true);
                this.vistaMaterias.txtGrupo.setEditable(true);
                this.vistaMaterias.cmbCRNMateriaDocente.setEnabled(true);
                this.vistaMaterias.cmbCalendario.setEnabled(true);
                this.vistaMaterias.cmbDia.setEnabled(true);
                this.vistaMaterias.jdcFechaInicio.setEnabled(true);
                this.vistaMaterias.jdcFechaFin.setEnabled(true);
                this.vistaMaterias.spiHoraIni.setEnabled(true);
                this.vistaMaterias.spiHoraFin.setEnabled(true);
                break;
            default:

                break;
        }
    }
    
    private void habilitarComponentesCancelarGuardar(int index){
        this.vistaMaterias.btnNuevo.setEnabled(true);
        this.vistaMaterias.btnEditar.setEnabled(false);
        this.vistaMaterias.btnGuardar.setEnabled(false);
        this.vistaMaterias.btnEliminar.setEnabled(false);
        this.vistaMaterias.btnCancelar.setEnabled(true);
        
       
        switch (index){
            case 0:
                cargarCRN();
                //Limpieza Materias
                this.vistaMaterias.txtCRN.setEditable(false);
                this.vistaMaterias.txtNombreMateria.setEditable(false);
                this.vistaMaterias.cmbEstado.setEnabled(false);
                break;
            case 1:
                //Limpieza Materias Docente
                this.vistaMaterias.cmbCalendario.setEnabled(false);
                 switch(banDocente){
                    case 0:
                        cargarComboCRN();
                        cargarComboCalendario();
                        cargarComboDocentes();
                        consultarMateriasDocente("0");
                        if(vistaMaterias.jtpMaterias.getSelectedIndex() == 1){
                            this.vistaMaterias.btnNuevo.setEnabled(false);
                            this.vistaMaterias.btnGuardar.setEnabled(false);
                            this.vistaMaterias.btnCancelar.setEnabled(false);
                        }
                        this.vistaMaterias.cmbFiltroDocente.setEnabled(true);
                        this.vistaMaterias.btnBuscarMateriasDocente.setEnabled(true);
                        break;
                    case 1:
                        consultarMateriasDocente(id_docente);
                        break;
                    default:
                        break;
                }
                
                this.vistaMaterias.txtComentarioMateria.setEditable(false);
                this.vistaMaterias.txtGrupo.setEditable(false);
                this.vistaMaterias.cmbCRNMateriaDocente.setEnabled(false);
                this.vistaMaterias.cmbDia.setEnabled(false);
                this.vistaMaterias.jdcFechaInicio.setEnabled(false);
                this.vistaMaterias.jdcFechaFin.setEnabled(false);
                this.vistaMaterias.spiHoraIni.setEnabled(false);
                this.vistaMaterias.spiHoraFin.setEnabled(false);
                
                break;
            default:

                break;
        }
    }
    
    private void limpiezaComponentes(int index){
        switch (index){
            case 0:
                //Limpieza Materias
                this.vistaMaterias.txtBusquedaMateria.setText("");
                this.vistaMaterias.txtCRN.setText("");
                this.vistaMaterias.txtNombreMateria.setText("");
                this.vistaMaterias.cmbBusquedaMateria.setSelectedIndex(0);
                this.vistaMaterias.cmbEstado.setSelectedIndex(1);
                id_materia = 0;
                break;
            case 1:
                //Limpieza Materias Docente
                switch(banDocente){
                    case 0:
                        this.vistaMaterias.cmbFiltroDocente.setSelectedIndex(0);
                        id_docente = "";
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                this.vistaMaterias.txtComentarioMateria.setText("");
                this.vistaMaterias.txtDescripcionMateriaDocente.setText("");
                this.vistaMaterias.txtGrupo.setText("");
                this.vistaMaterias.jdcFechaInicio.setDate(mFechasHoras.getDate());
                this.vistaMaterias.jdcFechaFin.setDate(mFechasHoras.getDate());
                this.vistaMaterias.cmbCalendario.setSelectedIndex(0);
                this.vistaMaterias.cmbDia.setSelectedIndex(0);
                this.vistaMaterias.cmbCRNMateriaDocente.setSelectedIndex(0);
                this.vistaMaterias.spiHoraIni.setValue(mFechasHoras.convertStringToHour("12:00:00"));
                this.vistaMaterias.spiHoraFin.setValue(mFechasHoras.convertStringToHour("12:00:00"));
                id_materia_docente = 0;
                
                break;
            default:

                break;
        }
    }
    
    // Materias por CRN

    public void cargarCRN(){
        vistaMaterias.dtmMaterias = consultarMateria(vistaMaterias.dtmMaterias, 
                "NombreCamposTablaCrn", vistaMaterias.cmbBusquedaMateria.getSelectedItem().toString(),
                vistaMaterias.txtBusquedaMateria.getText());
        reSizeTableCRN();
    }
    
    public void reSizeTableCRN(){
        TableColumnModel columnModel = vistaMaterias.tblListadoMaterias.getColumnModel();
        String[] arreglo = Config.consultarConfiguracion("SizeCamposTablaCrn").split(",");
        for (int i = 0; i < arreglo.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(Integer.parseInt(arreglo[i]));
        }
    }
    
    public void llenarCamposCRN(int id){
        CRN.reiniciarAtributos();
        CRN.consultarRegistro(id);
        id_materia = CRN.getId();
        this.vistaMaterias.txtCRN.setText(CRN.getCrn());
        this.vistaMaterias.txtNombreMateria.setText(CRN.getNombre());
        this.vistaMaterias.cmbEstado.setSelectedIndex(CRN.getEstado());
    }
    
    // Modelo CRN
    public DefaultTableModel consultarMateria(DefaultTableModel model, String campos, String filtros, String valores ){
        int tamaño = model.getRowCount();
        if(tamaño != 0)
            for (int i = 0;i < tamaño; i++) 
                model.removeRow(0);
        ArrayList<String[]> lista = CRN.consultarFiltrado(filtros, valores);
        String[] arreglo = Config.consultarConfiguracion(campos).split(",");
        Vector<String> v = new Vector<String>( Arrays.asList( arreglo ));
        model.setColumnCount(v.size());
        model.setColumnIdentifiers(v);
        for(String[] s : lista){
            model.addRow(s);
        }
        return model;
    }
    
    public boolean crearCRN(){
        CRN = new mCrn();
        CRN.setCrn(vistaMaterias.txtCRN.getText().trim());
        CRN.setNombre(vistaMaterias.txtNombreMateria.getText().trim());
        CRN.setEstado(vistaMaterias.cmbEstado.getSelectedIndex());
        if(validacionCamposCRN("Crear")){
            CRN.agregar();
            return true;
        }
        else
            return false;
    }
    public boolean editarCRN(int id){
        CRN.reiniciarAtributos();
        if(CRN.consultarRegistro(id)){
            CRN.setCrn(vistaMaterias.txtCRN.getText().trim());
            CRN.setNombre(vistaMaterias.txtNombreMateria.getText().trim());
            CRN.setEstado(vistaMaterias.cmbEstado.getSelectedIndex());
            if(validacionCamposCRN("Editar")){
                CRN.editar();
                return true;
            }else
                return false;
        }
        return false;
    }
    public boolean eliminarCRN(int id){
        CRN.reiniciarAtributos();
        if(CRN.consultarRegistro(id)){
            CRN.eliminar();
            return true;
        }
        return false;
    }
    
    // Validaciones para editar o crear registros
    public boolean validacionCamposCRN(String estado){
        boolean error = true;
        mValidaciones validar = new mValidaciones();
        //Validacion de Calendario
        if(!validar.cadenaNoVacia(CRN.getCrn(), "CRN")) // Nombre no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(CRN.getCrn(),8,"CRN")) //Nombre no mayor a 8 caracteres
            error = false;
        else if(!validar.unicoRegistroDB(CRN.consultarExistente(CRN.getCrn(), CRN.getId()), CRN.getCrn(), "CRN", "Registro"))
            error = false;
        else if(!validar.cadenaNoVacia(CRN.getNombre(), "Nombre")) // Nombre no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(CRN.getNombre(),250,"Nombre")) //Nombre no mayor a 200 caracteres
            error = false;
        else if(estado.equals("Editar") && !validar.enteroNoMenor("ID", CRN.getId(), 1)){
            mDatos.Error = mDatos.ErrorDelSistema;
            error = false;
        }
        if(!error)
            JOptionPane.showMessageDialog(vistaMaterias,  mDatos.Error, "Validacion Error", JOptionPane.ERROR_MESSAGE);
        return error;
    }
    
    
    // Materias por Docente
    
    public void cargarComboDocentes(){
        vistaMaterias.cmbFiltroDocente.setModel(llenarComboDocente(vistaMaterias.dcmDocentes));
    }
    
    public void cargarComboCRN(){
        vistaMaterias.cmbCRNMateriaDocente.setModel(llenarComboCrn(vistaMaterias.dcmCRN));
    }
    
    public void cargarComboCalendario(){
        vistaMaterias.cmbCalendario.setModel(llenarComboCalendario(vistaMaterias.dcmCalendario, 4));
    }
    
    public void llenarCamposMateriaDocente(int id){
        CRN.reiniciarAtributos();
        CRN.consultarRegistro(id);
        id_materia = CRN.getId();
        this.vistaMaterias.txtCRN.setText(CRN.getCrn());
        this.vistaMaterias.txtNombreMateria.setText(CRN.getNombre());
        this.vistaMaterias.cmbEstado.setSelectedIndex(CRN.getEstado());
    }
    
    public void seleccionarDocente(){
        String[] arreglo = vistaMaterias.cmbFiltroDocente.getSelectedItem().toString().split(" ");
        banDocente = 1;
//        vistaMaterias.lblBanDocente.setText(" La bandera de docente esta = " + banDocente);
        id_docente = arreglo[0];
        vistaMaterias.btnBuscarMateriasDocente.setEnabled(false);
        vistaMaterias.cmbFiltroDocente.setEnabled(false);
        habilitarComponentesCancelarGuardar(vistaMaterias.jtpMaterias.getSelectedIndex());
        consultarMateriasDocente(id_docente);
        reSizeTableMateriasDocente();
    }
    
    public void consultarMateriasDocente(String id_banner){
        vistaMaterias.dtmMateriaDocentes = consultarBusqueda(vistaMaterias.dtmMateriaDocentes, 
                "NombreCamposTablaMaterias", "id_docente", id_banner);
    }
    
    public void llenarCamposMateria(int id){
        Materia.reiniciarAtributos();
        Materia.consultarRegistro(id);
        id_materia_docente = Materia.getId();
        vistaMaterias.cmbCRNMateriaDocente.setSelectedItem(Materia.getCrn() + "-" + Materia.getDescripcion());
        vistaMaterias.txtDescripcionMateriaDocente.setText(Materia.getDescripcion());
        vistaMaterias.cmbDia.setSelectedIndex(Materia.getDia());
        vistaMaterias.cmbCalendario.setSelectedItem(Materia.getCalendario());
        vistaMaterias.jdcFechaInicio.setDate(mFechasHoras.convertMysqlToDate(Materia.getFecha_vig_ini()));
        vistaMaterias.jdcFechaFin.setDate(mFechasHoras.convertMysqlToDate(Materia.getFecha_vig_fin()));
        vistaMaterias.txtGrupo.setText(Materia.getGrupo());
        vistaMaterias.spiHoraIni.setValue(mFechasHoras.convertStringToHour(Materia.getHora_ini()));
        vistaMaterias.spiHoraFin.setValue(mFechasHoras.convertStringToHour(Materia.getHora_fin()));
        vistaMaterias.txtComentarioMateria.setText(Materia.getComentario());
    }
    
    public void seleccionarCalendario(){
        String calendario = vistaMaterias.cmbCalendario.getSelectedItem().toString();
        String[] arreglo = Config.consultarConfiguracion(calendario).split("\\|");
        vistaMaterias.jdcFechaInicio.setDate(mFechasHoras.convertStringToDate(arreglo[0].trim()));
        vistaMaterias.jdcFechaFin.setDate(mFechasHoras.convertStringToDate(arreglo[1].trim()));
    }
    
    public void ctrBanDocente(){
        if(vistaMaterias.jtpMaterias.getSelectedIndex() == 1)
                if(banDocente <= 0)
                    banDocente = 0;
                else
                    banDocente --;
//            vistaMaterias.lblBanDocente.setText(" La bandera de docente esta = " + banDocente);
    }
    
    // Modelo Materia por Docente
    public boolean crear(String crn, String descripcion, String id_docente, int dia,
            String fecha_vig_ini, String fecha_vig_fin, String hora_ini, 
            String hora_fin, String grupo, String calendario,String comentario ){
        mHorarioMateriaDocente mMat = new mHorarioMateriaDocente(crn, descripcion, id_docente, dia, fecha_vig_ini, 
            fecha_vig_fin, hora_ini, hora_fin, grupo, calendario, comentario);
        return mMat.agregar();
    }
    
    public boolean editar(int id, String crn, String descripcion, String id_docente, int dia,
            String fecha_vig_ini, String fecha_vig_fin, String hora_ini, 
            String hora_fin, String grupo, String calendario,String comentario ){
        mHorarioMateriaDocente mMat = new mHorarioMateriaDocente(id, crn, descripcion, id_docente, dia, fecha_vig_ini, 
            fecha_vig_fin, hora_ini, hora_fin, grupo, calendario, comentario);
        return mMat.editar();
    }
    public DefaultTableModel consultarBusqueda(DefaultTableModel model, String campos, String filtros, String valores ){
        int tamaño = model.getRowCount();
        if(tamaño != 0)
            for (int i = 0;i < tamaño; i++) 
                model.removeRow(0);
        ArrayList<String[]> lista = Materia.consultarFiltrado(filtros, valores);
        String[] arreglo = Config.consultarConfiguracion(campos).split(",");
        Vector<String> v = new Vector<String>( Arrays.asList( arreglo ));
        model.setColumnCount(v.size());
        model.setColumnIdentifiers(v);
        for(String[] s : lista){
            model.addRow(s);
        }
        return model;
    }
    
    public void reSizeTableMateriasDocente(){
        TableColumnModel columnModel = vistaMaterias.tblListadoMateriaDocente.getColumnModel();
        String[] arreglo = Config.consultarConfiguracion("SizeCamposTablaMaterias").split(",");
        for (int i = 0; i < arreglo.length; i++) {
            columnModel.getColumn(i).setPreferredWidth(Integer.parseInt(arreglo[i]));
        }
    }
    
    public DefaultComboBoxModel<String> llenarComboCalendario(DefaultComboBoxModel<String> model, int tipo){
        if(model!= null){
            model.removeAllElements();
        }
        ArrayList<mConfiguracion> lista = Config.consultar(4);
        
        for (mConfiguracion c : lista) {
            model.addElement(c.getNombre());
        }
        return model;
    }
    
    public DefaultComboBoxModel<String> llenarComboDocente(DefaultComboBoxModel<String> model){
        if(model!= null){
            model.removeAllElements();
        }
        mDocentes Docentes = new mDocentes();
        ArrayList<mDocentes> lista = Docentes.consultarDocentes();
        
        for (mDocentes d : lista) {
            model.addElement(d.getId_banner() + " " + d.getNombre() + " " +
                    d.getApellido_paterno() + " " + d.getApellido_materno());
        }
        return model;
    }
    
    public DefaultComboBoxModel<String> llenarComboCrn(DefaultComboBoxModel<String> model){
        if(model!= null){
            model.removeAllElements();
        }
        mCrn CRN = new mCrn();
        ArrayList<mCrn> lista = CRN.consultarCrns();
        
        for (mCrn c : lista) {
            model.addElement(c.getCrn() + "-" + c.getNombre());
        }
        return model;
    }
    
    public boolean eliminar(int id){
        mHorarioMateriaDocente mMat = new mHorarioMateriaDocente();
        mMat.setId(id);
        return mMat.eliminar();
    }
    
    public boolean crearMateria(){
        Materia.reiniciarAtributos();
        String[] arreglo = vistaMaterias.cmbCRNMateriaDocente.getSelectedItem().toString().split("-");
        Materia.setCrn(arreglo[0]);
        Materia.setDescripcion(arreglo[1]);
        Materia.setId_docente(id_docente);
        Materia.setDia(vistaMaterias.cmbDia.getSelectedIndex());
        Materia.setFecha_vig_ini(mFechasHoras.formatDateToMysql(vistaMaterias.jdcFechaInicio.getDate()));
        Materia.setFecha_vig_fin(mFechasHoras.formatDateToMysql(vistaMaterias.jdcFechaFin.getDate()));
        String horaIni = vistaMaterias.spiHoraIni.getValue().toString();
        String horaFin = vistaMaterias.spiHoraFin.getValue().toString();
        Materia.setHora_ini(horaIni.substring(horaIni.length()-17, horaIni.length()-9));
        Materia.setHora_fin(horaFin.substring(horaFin.length()-17, horaFin.length()-9));
        Materia.setGrupo(vistaMaterias.txtGrupo.getText());
        Materia.setCalendario(vistaMaterias.cmbCalendario.getSelectedItem().toString());
        Materia.setComentario(vistaMaterias.txtComentarioMateria.getText());
        if(validacionCamposMateriaDocente("Crear")){
            Materia.agregar();
            return true;
        }else
            return false;
    }
    public boolean editarMateria(int id){
        Materia.reiniciarAtributos();
        if(Materia.consultarRegistro(id)){
            String[] arreglo = vistaMaterias.cmbCRNMateriaDocente.getSelectedItem().toString().split("-");
            Materia.setCrn(arreglo[0]);
            Materia.setDescripcion(arreglo[1]);
            Materia.setId_docente(id_docente);
            Materia.setDia(vistaMaterias.cmbDia.getSelectedIndex());
            Materia.setFecha_vig_ini(mFechasHoras.formatDateToMysql(vistaMaterias.jdcFechaInicio.getDate()));
            Materia.setFecha_vig_fin(mFechasHoras.formatDateToMysql(vistaMaterias.jdcFechaFin.getDate()));
            String horaIni = vistaMaterias.spiHoraIni.getValue().toString();
            String horaFin = vistaMaterias.spiHoraFin.getValue().toString();
            Materia.setHora_ini(horaIni.substring(horaIni.length()-17, horaIni.length()-9));
            Materia.setHora_fin(horaFin.substring(horaFin.length()-17, horaFin.length()-9));
            Materia.setGrupo(vistaMaterias.txtGrupo.getText());
            Materia.setCalendario(vistaMaterias.cmbCalendario.getSelectedItem().toString());
            Materia.setComentario(vistaMaterias.txtComentarioMateria.getText());
            if(validacionCamposMateriaDocente("Editar")){
                Materia.editar();
                return true;
            }else
                return false;
        }
        return false;
    }
    public boolean eliminarMateria(int id){
        Materia.reiniciarAtributos();
        if(Materia.consultarRegistro(id)){
            Materia.eliminar();
            return true;
        }
        return false;
    }
    
    // Validaciones para editar o crear registros
    public boolean validacionCamposMateriaDocente(String estado){

        boolean error = true;
        mValidaciones validar = new mValidaciones();
        //Validacion de Calendario
        if(!validar.cadenaNoVacia(Materia.getCrn(), "CRN")) // CRN no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(Materia.getCrn(),8,"CRN")) //CRN no mayor a 8 caracteres
            error = false;
        else if(!validar.cadenaNoVacia(Materia.getDescripcion(), "Descripcion")) // Descripcion no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(Materia.getDescripcion(),250,"Descripcion")) //Descripcion no mayor a 250 caracteres
            error = false;
        else if(!validar.cadenaNoVacia(Materia.getId_docente(), "Id Docente")) // Id Docente no puede ser vacio
            error = false;
        else if(!validar.cadenaNoMayor(Materia.getId_docente(),8,"Id Docente")) //Id Docente no mayor a 8 caracteres
            error = false;
        else if(!validar.enteroNoMayor("Dia",Materia.getDia(),6)) //Dia no Mayor a 6
            error = false;
        else if(!validar.enteroNoMenor("Dia",Materia.getDia(),0)) //Dia no Menor a 0
            error = false;
        else if(!validar.fechaDesdeCadenaMysql("Fecha inicio", Materia.getFecha_vig_ini()))//Formato de fecha valido
            error = false; 
        else if(!validar.fechaDesdeCadenaMysql("Fecha fin", Materia.getFecha_vig_fin()))//Formato de fecha valido
            error = false;
        else if(!validar.fechaMysqlIniMenorIgualFechaMysqlFin("Fecha inicio", Materia.getFecha_vig_ini(), 
                "Fecha fin", Materia.getFecha_vig_fin()))// Fecha inicio menor o igual fecha fin
            error = false;
        else if(!validar.horaDesdeCadena("Hora entrada", Materia.getHora_ini()))
            error = false;
        else if(!validar.horaDesdeCadena("Hora salida", Materia.getHora_fin()))
            error = false;
        else if(!validar.horaIniMenorHoraFin("Hora entrada", Materia.getHora_ini(), "Hora salida", 
                Materia.getHora_fin()))// Hora inicio menor o igual hora fin
            error = false;
        else if(!validar.cadenaNoMayor(Materia.getGrupo(), 10, "Grupo"))
            error = false;
        else if(!validar.cadenaNoVacia(Materia.getCalendario(), "Calendario"))
            error = false;
        else if(!validar.cadenaNoMayor(Materia.getCalendario(), 200, "Calendario"))
            error = false;
        else if(!validar.cadenaNoMayor(Materia.getComentario(), 500, "Comentario"))
            error = false;
        
        else if(estado.equals("Editar") && !validar.enteroNoMenor("ID", CRN.getId(), 1)){
            mDatos.Error = mDatos.ErrorDelSistema;
            error = false;
        }
        if(!error)
            JOptionPane.showMessageDialog(vistaMaterias,  mDatos.Error, "Validacion Error", JOptionPane.ERROR_MESSAGE);
        return error;
    }

}
