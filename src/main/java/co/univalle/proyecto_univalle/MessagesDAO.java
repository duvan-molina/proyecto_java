/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.univalle.proyecto_univalle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author duvan
 */
public class MessagesDAO {
     public static void crearMensajeDB(Messages mensaje){
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_connection()){
            
           PreparedStatement ps = null;
            
            try {
                String query = "INSERT INTO `mensajes` (mensaje, autor_mensaje) VALUES (?,?)";
                ps = conexion.prepareStatement(query);
                ps.setString(1, mensaje.getMensaje());
                ps.setString(2, mensaje.getAutor_mensaje());
                
                ps.executeUpdate();
                
                System.out.println("Mensaje creado");
                
            }catch(SQLException ex){
               System.out.println(ex);
            }
            
            
        }catch(SQLException e){
           System.out.println(e);
        }    
    }
    
    public static void leerMensajeDB(){
    
        Conexion db_connect = new Conexion();
            PreparedStatement ps = null;
            ResultSet rs = null;
        try(Connection conexion = db_connect.get_connection()){
            String query  = "SELECT * FROM mensajes";
            
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println("ID: "+rs.getInt("id_mensajes"));
                System.out.println("Mensaje: "+rs.getString("mensaje"));
                System.out.println("Autor: "+rs.getString("autor_mensaje"));
                System.out.println("Fecha: "+rs.getString("fecha"));
            }
            
        }catch(SQLException ex){
               System.out.println(ex);
            }
    }
    
    public static void borrarMensajeDB(int id_mensaje){
        
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_connection()){
            
           PreparedStatement ps = null;
            
            try {
                String query = "DELETE FROM mensajes WHERE  id_mensajes = ?";
                
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id_mensaje);
                ps.executeUpdate();
                System.out.println("Mensaje borrado");
            }catch(SQLException ex){
               System.out.println(ex);
            }
            
            
        }catch(SQLException e){
           System.out.println(e);
        }  
    }
    
    public static void actualizarMensajeDB(Messages mensaje) {
        
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_connection()){
//            prepara sentencia
           PreparedStatement ps = null;
            
            try {
                String query = "UPDATE mensajes SET mensaje = ? WHERE id_mensajes = ?";
                ps = conexion.prepareStatement(query);
                ps.setString(1, mensaje.getMensaje());
                ps.setInt(2, mensaje.getId_mensajes());
                ps.executeUpdate();
                System.out.println("Mensaje Actualizado");
             
            }catch(SQLException ex){
               System.out.println(ex);
            }
            
            
        }catch(SQLException e){
           System.out.println(e);
        }  
    }
}
