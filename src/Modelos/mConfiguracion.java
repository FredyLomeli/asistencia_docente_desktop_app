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
public class mConfiguracion {
    
    final private mConexiones con;
    
    int id;
    String nombre,datos;
    int tipo;
    
    public mConfiguracion(){
        this.con =  new mConexiones();
    }
    
    public mConfiguracion(int id, String nombre, String datos, int tipo){
        this.con =  new mConexiones();
        this.id = id;
        this.nombre = nombre;
        this.datos = datos;
        this.tipo = tipo;
    }
    public mConfiguracion(String nombre, String datos, int tipo){
        this.con =  new mConexiones();
        this.nombre = nombre;
        this.datos = datos;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String Datos) {
        this.datos = Datos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public boolean agregar(){
        String sql = "INSERT INTO configuracion(nombre,datos,tipo) VALUES(?,?,?)";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getNombre());
                ps.setString(2, getDatos());
                ps.setInt(3, getTipo());
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
    
    public ArrayList consultar(int tipo){
        ArrayList<mConfiguracion> lista = new ArrayList<mConfiguracion>();
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM configuracion WHERE tipo = ? ORDER BY id DESC";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setInt(1, tipo);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    mConfiguracion mConfig = new mConfiguracion(rs.getInt("id"),
                            rs.getString("nombre"),rs.getString("datos"),rs.getInt("tipo"));
                    lista.add(mConfig);
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
    
    public int consultarExistente(String nombre){
        int contador = 0;
        try{
            if(con.conexion()){
                String sql = "SELECT * FROM configuracion WHERE nombre = ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, nombre);
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
    
    public String consultarConfiguracion(String campo){
        String dato = "";
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM configuracion WHERE nombre = ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, campo);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    dato = rs.getString("datos");
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
    return dato;
    }
    
    public boolean editar(){
        String sql = "UPDATE configuracion SET nombre = ?, datos = ? WHERE id = ?";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getNombre());
                ps.setString(2, getDatos());
                ps.setInt(3, getId());
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
        String sql = "DELETE FROM configuracion WHERE id = ?";
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
    
    public String consultarDato(int tipo){
        String dato = "";
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM configuracion WHERE tipo = ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setInt(1, tipo);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    dato = rs.getString("datos");
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
    return dato;
    }
    
    public String[] consultarConfiguracion(int id){
        String[] lista = new String[4];
        try {
            if(con.conexion()){
                String sql = "SELECT * FROM configuracion WHERE id = ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    lista[0] = rs.getString("id");
                    lista[1] = rs.getString("nombre");
                    lista[2] = rs.getString("dato");
                    lista[3] = rs.getString("tipo");
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


    @Override
    public String toString() {
        return getNombre() + " | " + getDatos() + " | " + getId();
        //return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
