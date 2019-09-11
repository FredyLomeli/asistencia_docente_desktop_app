/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.awt.Color;

/**
 *
 * @author Asesor
 */
public class mDatos {
    public static String Error = "";

    
     //Nombre del Sistema
    //public static String NombreSistema = "FX Punto de Venta - FREXAL";
    //public static String NombreEmpresa = "Soluciones en Desarrollos";
    public static String NombreSistema = "Control de Asistencia Docente";
    public static String NombreEmpresa = "Universidad Interamericana para el Desarrollo";
    //Titulos de las ventanas
    public static String TituloVentanaProductos = "Articulos y Servicios";
    public static String TituloVentanaListarProductos = "Listado de Articulos y Servicios Registrados";
    //FormatoDecimales
    public static String FormatoDecimales = "";
    public static String MascaraCeros = "";
    //decimales a utilizar
    public static int decimales = 2;
    //utilidad por precio
    public static String UtilidadPrecio1 = "5.00";
    public static String UtilidadPrecio2 = "10.00";
    public static String UtilidadPrecio3 = "15.00";
    public static String UtilidadPrecio4 = "20.00";
    public static String UtilidadPrecio5 = "25.00";
    // Mensajes del sistema
    public static String MensajeUnidadProveedor = "Llene estos campos solo \n en el caso de comprar productos \n en una unidad diferente a como \n la vende.";
    public static String MensajeRegistroCorrecto = "Registro exitoso.";
    //Errores
    public static String ErrorConexion = "Hubo un error al intentar conectar con la base de datos, verifique su conexion.";
    public static String ErrorAlAgregar = "Hubo un error al intentar agregar el resgistro, verifique su conexion.";
    public static String ErrorAlEditar = "Hubo un error al intentar editar el registro , verifique su conexion.";
    public static String ErrorAlConsultar = "Hubo un error al intentar consultar los registros , verifique su conexion.";
    public static String ErrorAlEliminar = "Hubo un error al intentar eliminar el registro , verifique su conexion.";
    public static String ErrorDelSistema = "Hubo un error en el proceso, favor cancelar la accion y volver a intentar.";
    public static String ErrorContraseña = "No coincide la contraseña indicada.";
    
    // Colores
    public static final Color VERY_LIGHT_RED = new Color(255,102,102);
    public static final Color LIGHT_RED = new Color(255,51,51);
    public static final Color RED = new Color(255,0,0);
    public static final Color DARK_RED = new Color(204,0,0);
    public static final Color VERY_DARK_RED = new Color(153,0,0);
    public static final Color VERY_LIGHT_BLUE = new Color(51,204,255);
    public static final Color LIGHT_BLUE = new Color(51,153,255);
    public static final Color BLUE = new Color(0,0,255);
    public static final Color DARK_BLUE = new Color(0,0,204);
    public static final Color VERY_DARK_BLUE = new Color(0,0,153);
    public static final Color VERY_LIGHT_GREEN = new Color(102,255,102);
    public static final Color LIGHT_GREEN = new Color(0,255,51);
    public static final Color GREEN = new Color(0,204,0);
    public static final Color DARK_GREEN = new Color(0,153,0);
    public static final Color VERY_DARK_GREEN = new Color(0,102,0);
    public static final Color VERY_LIGHT_YELLOW = new Color(255,255,204);
    public static final Color LIGHT_YELLOW = new Color(255,255,153);
    public static final Color YELLOW = new Color(255,255,0);
    public static final Color DARK_YELLOW = new Color(255,204,0);
    public static final Color LIGHT_ORANGE = new Color(255,153,0);
    public static final Color ORANGE = new Color(255,102,0);
    public static final Color GOLD = new Color(255,204,51);
    public static final Color LIGHT_GREY = new Color(204,204,204);
    public static final Color GREY = new Color(153,153,153);
    public static final Color DARK_GREY = new Color(102,102,102);
    public static final Color VERY_DARK_GREY = new Color(51,51,51);
    public static final Color LIGHT_BROWN = new Color(153,102,0);
    public static final Color BROWN = new Color(102,51,0);
    public static final Color DARK_BROWN = new Color(51,0,0);
    public static final Color PURPLE = new Color(102,0,153);
    public static final Color BLACK = new Color(0,0,0);
    public static final Color WHITE = new Color(255,255,255);
    
    //Validaciones 
    public static boolean ErrorValidacionStringVacio(String campo){
        Error = "El campo \"" + campo + "\" no puede estar vacio.";
        return false;
    }
    
    public static boolean ErrorValidacionStringLargo(String campo, String Largo, int tamaño){
        Error = "El campo \"" + campo + "\" no puede ser " + Largo + " a " + tamaño + " caracteres.";
        return false;
    }
    
    public static boolean ErrorValidacionEnteroValor(String campo, String tamaño, int valor){
        Error = "El campo \"" + campo + "\" no puede ser " + tamaño + " a " + valor + ".";
        return false;
    }
    
    public static boolean ErrorValidacionStringToFecha(String campo){
        Error = "El campo \"" + campo + "\" no es una fecha valida, favor de verificar.";
        return false;
    }
    
    public static boolean ErrorValidacionStringToHora(String campo){
        Error = "El campo \"" + campo + "\" no tiene un formato de hora valido, favor de verificar.";
        return false;
    }
    
    public static boolean ErrorValidacionFechaIniMenorFechaFin(String campoIni, String campoFin){
        Error = "La fecha en el campo \"" + campoIni + "\" debe ser menor o igual a la fecha en el campo \"" + campoFin + "\" .";
        return false;
    }
    
    public static boolean ErrorValidacionHoraIniMenorHoraFin(String campoIni, String campoFin){
        Error = "La Hora en el campo \"" + campoIni + "\" debe ser menor a la hora en el campo \"" + campoFin + "\" .";
        return false;
    }
    
    public static boolean ErrorValidacionCampoUnicoDB(String valor, String campo, String tabla){
        Error = "Ya se encuentra registrado el " + tabla + " con el " + campo + " " + valor + ", utilize otro " + campo + " para este registro.";
        return false;
    }
    
    public static boolean ErrorValidacionCampoExisteDB(String valor, String campo, String tabla){
        Error = "No existe el " + tabla + " con el " + campo + ": " + valor + ", ingrese un " + campo + " correcto.";
        return false;
    }
    
    public static boolean ErrorValidacionStringMach(String campoUno, String campoDos){
        Error = "El campo \"" + campoUno + "\" no coincide con el campo \"" + campoDos + "\" .";
        return false;
    }
    
    public static boolean StringToBoolean(String valor){
        if(valor.equals("Activo"))
            return true;
        else
            return false;
    }
    
    public static String BooleanToString(boolean valor){
        if(valor)
            return "Activo";
        else
            return "Inactivo";
    }
    
    public static boolean IntToBoolean(int valor){
        if(valor == 1)
            return true;
        else
            return false;
    }

    
    public static int BooleanToInt(boolean valor){
        if(valor)
            return 1;
        else
            return 0;
    }
    
    public static int StringToIntBan(String valor){
        if(valor.equals("Activo"))
            return 1;
        else
            return 0;
    }
    
    public static String IntToStringBan(int valor){
        if(valor == 1)
            return "Activo";
        else
            return "Inactivo";
    }
    
    public static String IntToDay(int valor){
        String dia = "";
        switch(valor){
            case 0: 
                dia = "Domingo";
                break;
            case 1: 
                dia =  "Lunes";
                break;
            case 2: 
                dia =  "Martes";
                break;
            case 3: 
                dia =  "Miercoles";
                break;
            case 4: 
                dia =  "Jueves";
                break;
            case 5: 
                dia =  "Viernes";
                break;
            case 6: 
                dia =  "Sabado";
                break;
        }
        return dia;
    }
    
    public static int DayToInt(String valor){
        int dia = -1;
        switch(valor){
            case "domingo": 
                dia = 0;
                break;
            case "lunes": 
                dia = 1;
                break;
            case "martes": 
                dia =  2;
                break;
            case "miércoles": 
                dia =  3;
                break;
            case "jueves": 
                dia =  4;
                break;
            case "viernes": 
                dia =  5;
                break;
            case "sábado": 
                dia =  6;
                break;
        }
        return dia;
    }

}
