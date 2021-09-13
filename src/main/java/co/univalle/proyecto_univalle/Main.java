/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.univalle.proyecto_univalle;

import static co.univalle.proyecto_univalle.CatsService.showCats;
import static co.univalle.proyecto_univalle.MessagesInit.messagesInit;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author duvan
 */
public class Main {
  /**
        *
        * @param args
        * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        int opcion_menu = -1;
        String[] botones = {" 1. Aplicacion gaticos", "2. Aplicacion Mensajes", "3. salir"};
        
        do{
            
            //menu principal
            String opcion = (String) JOptionPane.showInputDialog(null, "Aplicaciones", "Menu principal", JOptionPane.INFORMATION_MESSAGE,
                    null, botones,botones[0]);
            
            //validamos que opcion selecciona el usuario
            for(int i=0;i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    opcion_menu = i;
                }
            }
            
            switch(opcion_menu){
                case 0:
                    showCats();
                    break;
                case 1:
                   messagesInit();
                    break;
                default:
                    System.exit(0);
                    break;
            }     
        }while(opcion_menu != 1);
    }
}
