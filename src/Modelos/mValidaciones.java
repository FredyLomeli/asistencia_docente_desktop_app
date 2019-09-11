/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Asesor
 */
public class mValidaciones {
    mDateTime d = new mDateTime();
    // Validacion de cadenas
    public boolean cadenaNoVacia(String cadena, String campo){
        if(cadena.trim().isEmpty())
            return mDatos.ErrorValidacionStringVacio(campo);
        else
            return true;
    }
    
    public boolean cadenaNoMayor(String cadena, int cantidad, String campo){
        if(cadena.trim().length() > cantidad)
            return mDatos.ErrorValidacionStringLargo(campo, "mayor", cantidad);
        else
            return true;
    }
    
    public boolean cadenaMach(String campoUno, String cadenaUno, String campoDos, String cadenaDos){
        if(!cadenaUno.trim().equals(cadenaDos.trim()))
            return mDatos.ErrorValidacionStringMach(campoUno, campoDos);
        else
            return true;
    }
    
    public boolean cadenaNoMenor(String cadena, int cantidad, String campo){
        if(cadena.trim().length() < cantidad)
            return mDatos.ErrorValidacionStringLargo(campo, "menor", cantidad);
        else
            return true;
    }
    
    // Validacion de Enteros
    public boolean enteroNoMayor(String campo, int valor, int cantidad){
        if(valor > cantidad)
            return mDatos.ErrorValidacionEnteroValor(campo, "mayor", cantidad);
        else
            return true;
    }
    
    public boolean enteroNoMenor(String campo, int valor, int cantidad){
        if(valor < cantidad)
            return mDatos.ErrorValidacionEnteroValor(campo, "menor", cantidad);
        else
            return true;
    }
    
    //Validar fechas con formato dd/mm/yyyy
    public boolean fechaDesdeCadena(String campo, String date){
        if(date != null){
            if(d.convertStringToDate(date) == null)
                return mDatos.ErrorValidacionStringToFecha(campo);
            else
                return true;
        }else
            return mDatos.ErrorValidacionStringToFecha(campo);
    }
    
    public boolean fechaIniMenorIgualFechaFin(String campoIni, String dateIni, String campoFin, String dateFin ){
        if(!(d.convertStringToDate(dateIni).compareTo(d.convertStringToDate(dateFin)) <= 0))
            return mDatos.ErrorValidacionFechaIniMenorFechaFin(campoIni, campoFin);
        else
            return true;
    }
    
    //Validar fechas con formato MYSQL
    public boolean fechaDesdeCadenaMysql(String campo, String date){
        if(date != null){
            if(d.convertMysqlToDate(date) == null)
                return mDatos.ErrorValidacionStringToFecha(campo);
            else
                return true;
        }else
            return mDatos.ErrorValidacionStringToFecha(campo);
    }
    
    public boolean fechaMysqlIniMenorIgualFechaMysqlFin(String campoIni, String dateIni, String campoFin, String dateFin ){
        if(!(d.convertMysqlToDate(dateIni).compareTo(d.convertMysqlToDate(dateFin)) <= 0))
            return mDatos.ErrorValidacionFechaIniMenorFechaFin(campoIni, campoFin);
        else
            return true;
    }
    
    //Validacion de horas
    public boolean horaDesdeCadena(String campo, String date){
        if(date != null){
            if(d.convertStringToHour(date) == null)
                return mDatos.ErrorValidacionStringToHora(campo);
            else
                return true;
        }else
            return mDatos.ErrorValidacionStringToHora(campo);
    }
    
    public boolean horaIniMenorHoraFin(String campoIni, String horaIni, String campoFin, String horaFin ){
        if(!(d.convertStringToHour(horaIni).compareTo(d.convertStringToHour(horaFin)) < 0))
            return mDatos.ErrorValidacionHoraIniMenorHoraFin(campoIni, campoFin);
        else
            return true;
    }
    
    //registros en DB
    public boolean unicoRegistroDB(int cantidad, String valor, String campo, String tabla){
        if(cantidad > 0)
            return mDatos.ErrorValidacionCampoUnicoDB(valor, campo, tabla);
        else if(cantidad < 0){
            mDatos.Error = mDatos.ErrorConexion;
            return false;
        }
        else
            return true;
    }
    
    public boolean existeRegistroDB(int cantidad, String valor, String campo, String tabla){
        if(cantidad != 1)
            return mDatos.ErrorValidacionCampoExisteDB(valor, campo, tabla);
        else if(cantidad < 0){
            mDatos.Error = mDatos.ErrorConexion;
            return false;
        }
        else
            return true;
    }
    
}
