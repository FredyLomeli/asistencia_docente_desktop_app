package Modelos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Asesor
 */
public class mConexiones {
    public Connection conn = null;
//     Librería de MySQL
    public String driver = "com.mysql.jdbc.Driver";
//     Nombre de la base de datos
    public String database = "check_asistencia";
//    public String database = "asistencias";
//     Host
//    public String hostname = "localhost";
    public String hostname = "10.10.50.199";
//     Puerto
    public String port = "3306";
//     Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
//     Nombre de usuario
//    public String username = "root";
    public String username = "check";
//     Clave de usuario
//    public String password = "";
    public String password = "Rcb5azmLeh83QA9W";
    
    public Connection conectarMySQL() {
        try {
            Class.forName(driver);
            conn = (Connection)DriverManager.getConnection(url, username, password);
//            JOptionPane.showMessageDialog(null, "Correcto" , "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No se conecto a la base de datos : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
        }
        return conn;
    }
    
    public void Desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problema al cerrar la coneccion :" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
    
    public boolean conexion(){
        try {
            Class.forName(driver);
            conn = (Connection)DriverManager.getConnection(url, username, password);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }
    
}
