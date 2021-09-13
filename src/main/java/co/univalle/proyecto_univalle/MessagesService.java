/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.univalle.proyecto_univalle;

import static co.univalle.proyecto_univalle.MessagesInit.messagesInit;
import java.util.Scanner;

/**
 *
 * @author duvan
 */
public class MessagesService {
    public static void crearMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu mensaje");
        String mensaje = sc.nextLine();
        
        System.out.println("Tu nombre");
        String nombre = sc.nextLine();
        
        Messages register = new Messages();
        
        register.setMensaje(mensaje);
        register.setAutor_mensaje(nombre);
        
        MessagesDAO.crearMensajeDB(register);
        
        messagesInit();
    }
    
    public static void listarMensajes(){
    
       MessagesDAO.leerMensajeDB();
        
       messagesInit();
    }
    
    public static void borrarMensaje(){
     Scanner sc = new Scanner(System.in);
     System.out.println("id del mensaje para borrar");
     int id_mensaje = sc.nextInt();
     MessagesDAO.borrarMensajeDB(id_mensaje);
     
     messagesInit();
    }
    
    public static void editarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe tu nuevo mensaje");
        String nuevoMensaje = sc.nextLine();
        
        System.out.println("id mensaje");
        int id_mensaje = sc.nextInt();
        
        Messages actualizacion = new Messages();
        
        actualizacion.setId_mensajes(id_mensaje);
        actualizacion.setMensaje(nuevoMensaje);
        
        MessagesDAO.actualizarMensajeDB(actualizacion);
        
        messagesInit();
    
    }
}
