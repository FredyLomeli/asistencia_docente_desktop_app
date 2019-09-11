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
public class mCrn {
    
    final private mConexiones con;
    int id, estado;
    String crn, nombre;

    public mCrn() {
        con = new mConexiones();
    }
    
    public mCrn(int id) {
        con = new mConexiones();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean agregar(){
        String sql = "INSERT INTO crn(crn,nombre,estado) VALUES(?,?,?)";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getCrn());
                ps.setString(2, getNombre());
                ps.setInt(3, getEstado());
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
                String[] arreglo = Config.consultarConfiguracion("CamposTablaCrn").split(",");
                
                String sql = "SELECT * FROM crn";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] o = new String[arreglo.length];
                    for (int i = 0; i < arreglo.length; i++) {
                        if(arreglo[i].equals("estado"))
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
    
    public int consultarExistente(String crn, int id){
        int contador = 0;
        try{
            if(con.conexion()){
                String sql = "SELECT * FROM crn WHERE crn = ? AND ID <> ?";
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
        String sql = "UPDATE crn SET crn = ?, nombre = ?, estado = ? WHERE id = ?";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getCrn());
                ps.setString(2, getNombre());
                ps.setInt(3, getEstado());
                ps.setInt(4, getId());
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
        String sql = "DELETE FROM crn WHERE id = ?";
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
                String[] arreglo = Config.consultarConfiguracion("CamposTablaCrn").split(",");
                String sql;
                if (!valor.equals(""))
                    sql = "SELECT * FROM crn WHERE " + filtro + " LIKE ?";
                else
                    sql = "SELECT * FROM crn ";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                if (!valor.equals(""))
                    ps.setString(1, valor);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] o = new String[arreglo.length];
                    for (int i = 0; i < arreglo.length; i++) {
                        if(arreglo[i].equals("estado"))
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
    
    public boolean consultarRegistro(int id){
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM crn WHERE id = ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    setId(rs.getInt("id"));
                    setCrn(rs.getString("crn"));
                    setNombre(rs.getString("nombre"));
                    setEstado(rs.getInt("estado"));
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
            return false;
        }
    }
    
    public ArrayList<mCrn> consultarCrns(){
        ArrayList<mCrn> lista = new ArrayList<>();
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM crn";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    mCrn CRN = new mCrn();
                    CRN.setId(rs.getInt("id"));
                    CRN.setCrn(rs.getString("crn"));
                    CRN.setNombre(rs.getString("nombre"));
                    CRN.setEstado(rs.getInt("estado"));
                    lista.add(CRN);
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
    
    public void reiniciarAtributos(){
        setId(0);
        setCrn("");
        setNombre("");
        setEstado(0);
    }
    
        //Metodos de consulta para el registro de asistencia.
    public boolean consultarRegistroByCrn(String crn){
        boolean ban = false;
        try {
            if(contarCRN(crn) == 1){
                if(con.conexion()){
                    String sql = "SELECT * FROM crn WHERE crn = ? AND estado = 1";
                    PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                    ps.setString(1, crn);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        setId(rs.getInt("id"));
                        setCrn(rs.getString("crn"));
                        setNombre(rs.getString("nombre"));
                        setEstado(rs.getInt("estado"));
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
    
    public int contarCRN(String crn){
        int count = 0;
        try {
            if(con.conexion()){
                String sql = "SELECT count(*) FROM crn WHERE crn = ? AND estado = 1";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, crn);
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
}
