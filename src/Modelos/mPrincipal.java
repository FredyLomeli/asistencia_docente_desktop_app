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
public class mPrincipal {
    
    final private mConexiones modelCon;
    int id; 
    String id_crn, id_docente, grupo;
    String hora_ingreso, hora_ingreso_reg, hora_salida, hora_salida_reg;
    String comentarios;
    
    
    public mPrincipal(int id, String id_crn, String id_docente, String grupo,
            String hora_ingreso, String hora_ingreso_reg, String hora_salida,
            String hora_salida_reg, String comentarios){
        this.modelCon = new mConexiones();
        this.id = id; 
        this.id_crn = id_crn;
        this.id_docente = id_docente;
        this.grupo = grupo;
        this.hora_ingreso = hora_ingreso;
        this.hora_ingreso_reg = hora_ingreso_reg;
        this.hora_salida = hora_salida;
        this.hora_salida_reg = hora_salida_reg;
        this.comentarios = comentarios;
    }
    
        public mPrincipal(String id_crn, String id_docente, String grupo,
            String hora_ingreso, String hora_ingreso_reg, String hora_salida,
            String hora_salida_reg, String comentarios){
        this.modelCon = new mConexiones();
        this.id_crn = id_crn;
        this.id_docente = id_docente;
        this.grupo = grupo;
        this.hora_ingreso = hora_ingreso;
        this.hora_ingreso_reg = hora_ingreso_reg;
        this.hora_salida = hora_salida;
        this.hora_salida_reg = hora_salida_reg;
        this.comentarios = comentarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_crn() {
        return id_crn;
    }

    public void setId_crn(String id_crn) {
        this.id_crn = id_crn;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public String getHora_ingreso_reg() {
        return hora_ingreso_reg;
    }

    public void setHora_ingreso_reg(String hora_ingreso_reg) {
        this.hora_ingreso_reg = hora_ingreso_reg;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getHora_salida_reg() {
        return hora_salida_reg;
    }

    public void setHora_salida_reg(String hora_salida_reg) {
        this.hora_salida_reg = hora_salida_reg;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
        
}
