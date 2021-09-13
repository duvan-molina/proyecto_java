/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.univalle.proyecto_univalle;

/**
 *
 * @author duvan
 */
public class Messages {
     int id_mensajes;
    String mensaje;
    String autor_mensaje;
    String fecha;

    public Messages(){}

    public Messages(String mensaje, String autor_mensaje, String fecha) {
        this.mensaje = mensaje;
        this.autor_mensaje = autor_mensaje;
        this.fecha = fecha;
    }
    
    //    son funciones que nos permiten guardar y obtener datos del atributo

    public int getId_mensajes() {
        return id_mensajes;
    }

    public void setId_mensajes(int id_mensajes) {
        this.id_mensajes = id_mensajes;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAutor_mensaje() {
        return autor_mensaje;
    }

    public void setAutor_mensaje(String autor_mensaje) {
        this.autor_mensaje = autor_mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
