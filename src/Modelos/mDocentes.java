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
 * @author Asesor
 */
public class mDocentes {
    
    final private mConexiones con;
    int id;
    String id_banner, nombre,apellido_paterno, apellido_materno;
    int estatus;
    String comentario;
    
    public mDocentes(){
        con = new mConexiones();
    }
    
    public mDocentes(int id, String id_banner, String nombre, String apellido_paterno,
            String apellido_materno, int estatus, String comentario){
        this.con = new mConexiones();
        this.id = id;
        this.id_banner = id_banner;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.estatus = estatus;
        this.comentario = comentario;
    }
    
     public mDocentes(String id_banner, String nombre, String apellido_paterno,
            String apellido_materno, int estatus, String comentario){
        this.con = new mConexiones();
        this.id_banner = id_banner;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.estatus = estatus;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_banner() {
        return id_banner;
    }

    public void setId_banner(String id_banner) {
        this.id_banner = id_banner;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public boolean agregar(){
        String sql = "INSERT INTO docentes(id_banner,nombre,apellido_paterno,apellido_materno,"
                + "estatus, comentario) VALUES(?,?,?,?,?,?)";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getId_banner());
                ps.setString(2, getNombre());
                ps.setString(3, getApellido_paterno());
                ps.setString(4, getApellido_materno());
                ps.setInt(5, getEstatus());
                ps.setString(6, getComentario());
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
                String[] arreglo = Config.consultarConfiguracion("CamposTablaDocente").split(",");
                
                String sql = "SELECT * FROM docentes";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] o = new String[arreglo.length];
                    for (int i = 0; i < arreglo.length; i++) {
                        if(arreglo[i].equals("estatus"))
                            o[i] = mDatos.IntToStringBan(Integer.parseInt(rs.getString(arreglo[i])));
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
    
    public int consultarExistente(String id_banner, int id){
        int contador = 0;
        try{
            if(con.conexion()){
                String sql = "SELECT * FROM docentes WHERE id_banner = ? AND ID <> ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, id_banner);
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
        String sql = "UPDATE docentes SET id_banner = ?, nombre = ?, "
                + "apellido_paterno = ?, apellido_materno = ?,"
                + "estatus = ?, comentario = ? WHERE id = ?";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getId_banner());
                ps.setString(2, getNombre());
                ps.setString(3, getApellido_paterno());
                ps.setString(4, getApellido_materno());
                ps.setInt(5, getEstatus());
                ps.setString(6, getComentario());
                ps.setInt(7, getId());
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
        String sql = "DELETE FROM docentes WHERE id = ?";
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
        String dato = "";
        try {
            if(con.conexion()){
                mConfiguracion Config = new mConfiguracion();
                String[] arreglo = Config.consultarConfiguracion("CamposTablaDocente").split(",");
                String sql;
                if(filtro.equals("estatus")){
                    sql = "SELECT * FROM materias WHERE " + filtro + " = ?";
                    if(valor.equals("Activo"))
                        valor = "1";
                    else if(valor.equals("Inactivo")){
                        valor = "0";
                    }
                }
                else
                    sql = "SELECT * FROM docentes WHERE " + filtro + " LIKE ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, valor);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] o = new String[arreglo.length];
                    for (int i = 0; i < arreglo.length; i++) {
                        if(arreglo[i].equals("estatus"))
                            o[i] = mDatos.IntToStringBan(Integer.parseInt(rs.getString(arreglo[i])));
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
    
    public String[] consultarDocente(int id){
        String[] lista = new String[7];
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM docentes WHERE id = ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    lista[0] = rs.getString("id");
                    lista[1] = rs.getString("id_banner");
                    lista[2] = rs.getString("nombre");
                    lista[3] = rs.getString("apellido_paterno");
                    lista[4] = rs.getString("apellido_materno");
                    lista[5] = rs.getString("estatus");
                    lista[6] = rs.getString("comentario");
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
    
    public ArrayList<mDocentes> consultarDocentes(){
        ArrayList<mDocentes> lista = new ArrayList<>();
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM docentes";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    mDocentes Docente = new mDocentes(rs.getInt("id"),rs.getString("id_banner"),
                    rs.getString("nombre"),rs.getString("apellido_paterno"),
                    rs.getString("apellido_materno"),rs.getInt("estatus"),
                    rs.getString("comentario"));
                    lista.add(Docente);
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
    
    //Metodos de consulta para el registro de asistencia.
    public boolean consultarDocenteByIdBanner(String id_banner){
        boolean ban = false;
        try {
            if(contarDocentes(id_banner) == 1){
                if(con.conexion()){
                    String sql = "SELECT * FROM docentes WHERE id_banner = ? AND estatus = 1";
                    PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                    ps.setString(1, id_banner);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        setId(rs.getInt("id"));
                        setId_banner(rs.getString("id_banner"));
                        setNombre(rs.getString("nombre"));
                        setApellido_paterno(rs.getString("apellido_paterno"));
                        setApellido_materno(rs.getString("apellido_materno"));
                        setEstatus(rs.getInt("estatus"));
                        setComentario(rs.getString("comentario"));
                    }
                    ps.close();
                    ps = null;
                    con.Desconectar();
                    ban = true;
                }else{
                    JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar, "Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar + " : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
        }
    return ban;
    }
    
    public int contarDocentes(String id_banner){
        int count = 0;
        try {
            if(con.conexion()){
                String sql = "SELECT count(*) FROM docentes WHERE id_banner = ? AND estatus = 1";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, id_banner);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    count = rs.getInt(1);
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
    return count;
    }
    
    public void limpiar(){
        setId(0);
        setId_banner("");
        setNombre("");
        setApellido_paterno("");
        setApellido_materno("");
        setEstatus(0);
        setComentario("");
    }
}
