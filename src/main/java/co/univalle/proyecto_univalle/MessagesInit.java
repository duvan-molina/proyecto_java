/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.univalle.proyecto_univalle;

import static co.univalle.proyecto_univalle.CatsService.showCats;
import javax.swing.JOptionPane;

/**
 *
 * @author duvan
 */
public class MessagesInit {
       public static void messagesInit() {
           
            String[] buttons = { "Crear mensaje", "Listar mensaje", "Editar mensaje", "Salir" };
                      

            
            String opcion = (String) JOptionPane.showInputDialog(null, "Funciones", "Mensajes", JOptionPane.INFORMATION_MESSAGE, null, buttons,buttons[0]);
           
            int seleccion = -1;
            //validamos que opcion selecciona el usuario
            for(int i=0;i<buttons.length;i++){
                if(opcion.equals(buttons[i])){
                    seleccion = i;
                }
            }
            
            switch (seleccion){
                case 0:
                    MessagesService.crearMensaje();
                    break;
                case 1:
                    MessagesService.listarMensajes();
                    break;
                case 2:
                    MessagesService.editarMensaje();
                    break;                  
                default:
                    break;
            }
       }
}
