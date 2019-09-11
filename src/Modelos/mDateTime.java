/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Asesor
 */
public class mDateTime {
    Date date;
    
    public Date getDate(){
        date = new Date();
        return date;
    }
    
    // Devuelve la horoa actual en tipo String
    public String getHoraActual(){
        date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(date);
    }
    
    // Devuelve la fecha actual en tipo String
    public String getFechaActual(){
        date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy");
        return hourFormat.format(date);
    }
    
    // Devuelve la fecha y hora actual en tipo String
    public String getHoraFechaActual(){
        date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return hourFormat.format(date);
    }
    
    // Devuelve la fecha y hora actual en tipo String
    public String getFechaHoraMysqlActual(){
        date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return hourFormat.format(date);
    }
    
    // Devuelve la fecha actual en tipo String formato especial mexico
    public String getFechaActualCustomFormat(){
        date = new Date();
        // Fecha en formato largo español México
        SimpleDateFormat formatoEsMX = new SimpleDateFormat(
            "EEEE d 'de' MMMM 'del' yyyy", new Locale("ES", "MX"));
        return formatoEsMX.format(date);
    }
    
    // Convierte una fecha tipo DATE en Tipo String
    public String formatDate(Date date){
        DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return hourFormat.format(date);
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
    // Convierte una fecha tipo DATE en Tipo String
    public String formatDateToMysql(Date date){
        DateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return hourFormat.format(date);
        } catch (NullPointerException ex) {
            return null;
        }
    }
    
    //Convierte una fecha tipo texto en tipo DATE
    public Date convertStringToDate(String fecha){
        DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = hourFormat.parse(fecha);
        } catch (ParseException ex) {
            date = null;
        }
        return date;
    }
    
    //Convierte una fecha tipo texto en tipo DATE
    public Date convertMysqlToDate(String fecha){
        DateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = hourFormat.parse(fecha);
        } catch (ParseException ex) {
            date = null;
        }
        return date;
    }
    
    //Convierte una fecha tipo texto en formato de mysql
    public String fechaStringToMysql(String fecha){
        DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = hourFormat.parse(fecha);
            fecha = hourFormat2.format(date);
        } catch (ParseException ex) {
            date = null;
        }
        return fecha;
    }
    
    //Convierte una fecha en formato de 24horas
    public Date convertHora24(Date fecha){
        DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date;
        try {
            date = hourFormat.format(fecha);
            fecha = hourFormat.parse(date);
        } catch (ParseException ex) {
            date = null;
        }
        return fecha;
    }
    
    //Convierte una hora tipo texto en tipo DATE
    public Date convertStringToHour(String hora){
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Date date;
        try {
            date = hourFormat.parse(hora);
        } catch (ParseException ex) {
            date = null;
        }
        return date;
    }
    
    //Extrae la hora en texto de una fecha y hora tipo texto
    public String extraeHoraStringOfDateString(String hora){
        DateFormat hourFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat hourFormat2 = new SimpleDateFormat("HH:mm:ss");
        Date date;
        try {
            date = hourFormat.parse(hora);
            hora = hourFormat2.format(date);
        } catch (ParseException ex) {
            date = null;
        }
        return hora;
    }
    
}
