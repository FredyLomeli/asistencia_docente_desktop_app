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
public class mRegistroDocente {
    
    final private mConexiones con;
    int id;
    String docente_banner, docente_nombre, crn, crn_descripcion,tipo_registro;
    int dia;
    String fecha_hora_reg, grupo, fecha_modificacion;
    String adicional1, adicional2, adicional3, adicional4;
    
    public mRegistroDocente(){
        con = new mConexiones();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocente_banner() {
        return docente_banner;
    }

    public void setDocente_banner(String docente_banner) {
        this.docente_banner = docente_banner;
    }

    public String getDocente_nombre() {
        return docente_nombre;
    }

    public void setDocente_nombre(String docente_nombre) {
        this.docente_nombre = docente_nombre;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getCrn_descripcion() {
        return crn_descripcion;
    }

    public void setCrn_descripcion(String crn_descripcion) {
        this.crn_descripcion = crn_descripcion;
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipo_registro) {
        this.tipo_registro = tipo_registro;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getFecha_hora_reg() {
        return fecha_hora_reg;
    }

    public void setFecha_hora_reg(String fecha_hora_reg) {
        this.fecha_hora_reg = fecha_hora_reg;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional2() {
        return adicional2;
    }

    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    public String getAdicional3() {
        return adicional3;
    }

    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    public String getAdicional4() {
        return adicional4;
    }

    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }
    
    public boolean agregar(){
        String sql = "INSERT INTO registro_docente(docente_banner, docente_nombre,"
                + "crn, crn_descripcion, tipo_registro, dia, fecha_hora_reg, grupo,"
                + "adicional1, adicional2, adicional3, adicional4)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            if(con.conexion()){
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setString(1, getDocente_banner());
                ps.setString(2, getDocente_nombre());
                ps.setString(3, getCrn());
                ps.setString(4, getCrn_descripcion());
                ps.setString(5, getTipo_registro());
                ps.setInt(6, getDia());
                ps.setString(7, getFecha_hora_reg());
                ps.setString(8, getGrupo());
                ps.setString(9, getAdicional1());
                ps.setString(10, getAdicional2());
                ps.setString(11, getAdicional3());
                ps.setString(12, getAdicional4());
                ps.execute();
                ps.close();
                ps = null;
                con.Desconectar();
                return true;
            }else{
                JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar, "Error", JOptionPane.ERROR_MESSAGE );
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, mDatos.ErrorAlConsultar + " : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
            return false;
        }
    }
    
    public ArrayList consultar(){
        ArrayList<String[]> lista = new ArrayList<>();
        try {
            if(con.conexion()){
                mConfiguracion Config = new mConfiguracion();
                String[] arreglo = Config.consultarConfiguracion("CamposTablaRegistroDocente").split(",");
                int limit = Integer.parseInt(Config.consultarConfiguracion("LimitTablaRegistroDocente"));
                
                String sql = "SELECT * FROM registro_docente ORDER BY fecha_modificacion DESC LIMIT ?";
                PreparedStatement ps = con.conectarMySQL().prepareCall(sql);
                ps.setInt(1, limit);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String[] o = new String[arreglo.length];
                    for (int i = 0; i < arreglo.length; i++) {
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
    
    public void limpiar(){
        setId(0);
        setDocente_banner("");
        setDocente_nombre("");
        setCrn("");
        setCrn_descripcion("");
        setTipo_registro("");
        setDia(0);
        setFecha_hora_reg("");
        setGrupo(null);
        setFecha_modificacion("");
        setAdicional1(null);
        setAdicional2(null);
        setAdicional3(null);
        setAdicional4(null);
    }
}
