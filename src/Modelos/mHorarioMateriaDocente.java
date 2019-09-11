/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Frexal
 */
public class mHorarioMateriaDocente {
    
    final mConexiones con;
    int id;
    String crn, descripcion;
    int dia;
    String fecha_vig_ini, fecha_vig_fin, id_docente, hora_ini, hora_fin, grupo;
    String calendario, comentario;
    
    public mHorarioMateriaDocente(){
        this.con = new mConexiones();
    }
    
    public mHorarioMateriaDocente(int id, String crn, String descripcion, String id_docente,
            int dia, String fecha_vig_ini, String fecha_vig_fin, String hora_ini, 
            String hora_fin, String grupo, String calendario, String comentario){
        
        this.con = new mConexiones();
        this.id = id;
        this.crn = crn;
        this.descripcion = descripcion;
        this.id_docente = id_docente;
        this.dia = dia;
        this.fecha_vig_ini = fecha_vig_ini;
        this.fecha_vig_fin = fecha_vig_fin;
        this.hora_ini = hora_ini;
        this.hora_fin = hora_fin;
        this.grupo = grupo;
        this.calendario = calendario;
        this.comentario = comentario;
    }
    
    public mHorarioMateriaDocente(String crn, String descripcion, String id_docente,
            int dia, String fecha_vig_ini, String fecha_vig_fin, String hora_ini, 
            String hora_fin, String grupo, String calendario, String comentario){
        
        this.con = new mConexiones();
        this.crn = crn;
        this.descripcion = descripcion;
        this.id_docente = id_docente;
        this.dia = dia;
        this.fecha_vig_ini = fecha_vig_ini;
        this.fecha_vig_fin = fecha_vig_fin;
        this.hora_ini = hora_ini;
        this.hora_fin = hora_fin;
        this.grupo = grupo;
        this.calendario = calendario;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public int getDia() {
        return dia;
    }

    public String getFecha_vig_ini() {
        return fecha_vig_ini;
    }

    public void setFecha_vig_ini(String fecha_vig_ini) {
        this.fecha_vig_ini = fecha_vig_ini;
    }

    public String getFecha_vig_fin() {
        return fecha_vig_fin;
    }

    public void setFecha_vig_fin(String fecha_vig_fin) {
        this.fecha_vig_fin = fecha_vig_fin;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(String hora_ini) {
        this.hora_ini = hora_ini;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCalendario() {
        return calendario;
    }

    public void setCalendario(String calendario) {
        this.calendario = calendario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
    
    public boolean agregar(){
        String sql = "INSERT INTO horario_materia_docente(crn,descripcion,id_docente,dia,"
                + "fecha_vig_ini,fecha_vig_fin,hora_ini,hora_fin,grupo,calendario,"
                + "comentario) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getCrn());
                ps.setString(2, getDescripcion());
                ps.setString(3, getId_docente());
                ps.setInt(4, getDia());
                ps.setString(5, getFecha_vig_ini());
                ps.setString(6, getFecha_vig_fin());
                ps.setString(7, getHora_ini());
                ps.setString(8, getHora_fin());
                ps.setString(9, getGrupo());
                ps.setString(10, getCalendario());
                ps.setString(11, getComentario());
                ps.execute();
                ps.close();
                ps = null;
                con.Desconectar();
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public ArrayList consultar(){
        ArrayList<String[]> lista = new ArrayList<>();
        try {
            if(con.conexion()){
                mConfiguracion Config = new mConfiguracion();
                String[] arreglo = Config.consultarConfiguracion("CamposTablaMaterias").split(",");
                
                String sql = "SELECT * FROM horario_materia_docente";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] o = new String[arreglo.length];
                    for (int i = 0; i < arreglo.length; i++) {
                        if(arreglo[i].equals("dia"))
                            o[i] = mDatos.IntToDay(rs.getInt(arreglo[i]));
                        else
                            o[i] = rs.getString(arreglo[i]);
                    }
                    lista.add(o);
                }
                ps.close();
                ps = null;
                con.Desconectar();
            }else{
                JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar, "Error", JOptionPane.ERROR_MESSAGE );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar + " : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
        }
    return lista;
    }
    
    public int consultarExistente(String crn, int id){
        int contador = 0;
        try{
            if(con.conexion()){
                String sql = "SELECT * FROM horario_materia_docente WHERE crn = ? AND ID <> ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, crn);
                ps.setInt(2, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    contador++;
                }
                ps.close();
                ps = null;
                con.Desconectar();
            }else{
                contador = -1;
            }
        } catch (SQLException e) {
            contador = -1;
        }
        return contador;
    }
    
    public boolean editar(){
        String sql = "UPDATE horario_materia_docente SET crn = ?,descripcion = ?,id_docente = ?,"
                + "dia = ?,fecha_vig_ini = ?,fecha_vig_fin = ?,hora_ini = ?,"
                + "hora_fin = ?,grupo = ?,calendario = ?,comentario = ? WHERE id = ?";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getCrn());
                ps.setString(2, getDescripcion());
                ps.setString(3, getId_docente());
                ps.setInt(4, getDia());
                ps.setString(5, getFecha_vig_ini());
                ps.setString(6, getFecha_vig_fin());
                ps.setString(7, getHora_ini());
                ps.setString(8, getHora_fin());
                ps.setString(9, getGrupo());
                ps.setString(10, getCalendario());
                ps.setString(11, getComentario());
                ps.setInt(12, getId());
                ps.execute();
                ps.close();
                ps = null;
                con.Desconectar();
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public boolean eliminar(){
        String sql = "DELETE FROM horario_materia_docente WHERE id = ?";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setInt(1, getId());
                ps.execute();
                ps.close();
                ps = null;
                con.Desconectar();
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public ArrayList consultarFiltrado(String filtro, String valor){
        ArrayList<String[]> lista = new ArrayList<>();
        try {
            if(con.conexion()){
                mConfiguracion Config = new mConfiguracion();
                String[] arreglo = Config.consultarConfiguracion("CamposTablaMaterias").split(",");
                
                String sql = "SELECT * FROM horario_materia_docente WHERE " + filtro + " = ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, valor);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] o = new String[arreglo.length];
                    for (int i = 0; i < arreglo.length; i++) {
                        if(arreglo[i].equals("dia"))
                            o[i] = mDatos.IntToDay(rs.getInt(arreglo[i]));
                        else
                            o[i] = rs.getString(arreglo[i]);
                    }
                    lista.add(o);
                }
                ps.close();
                ps = null;
                con.Desconectar();
            }else{
                JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar, "Error", JOptionPane.ERROR_MESSAGE );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar + " : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
        }
    return lista;
    }
    
    public boolean consultarRegistro(int id){
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM horario_materia_docente WHERE id = ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    setId(rs.getInt("id"));
                    setCrn(rs.getString("crn"));
                    setDescripcion(rs.getString("descripcion"));
                    setId_docente(rs.getString("id_docente"));
                    setDia(rs.getInt("dia"));
                    setFecha_vig_ini(rs.getString("fecha_vig_ini"));
                    setFecha_vig_fin(rs.getString("fecha_vig_fin"));
                    setHora_ini(rs.getString("hora_ini"));
                    setHora_fin(rs.getString("hora_fin"));
                    setGrupo(rs.getString("grupo"));
                    setCalendario(rs.getString("calendario"));
                    setComentario(rs.getString("comentario"));
                }
                ps.close();
                ps = null;
                con.Desconectar();
                return true;
            }else{
                JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar, "Error", JOptionPane.ERROR_MESSAGE );
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar + " : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
            return true;
        }
    }
    
    public void reiniciarAtributos(){
        setId(0);
        setCrn("");
        setDescripcion("");
        setId_docente("");
        setDia(0);
        setFecha_vig_ini("");
        setFecha_vig_fin("");
        setHora_ini("");
        setHora_fin("");
        setGrupo("");
        setCalendario("");
        setComentario("");
    }
        
}
