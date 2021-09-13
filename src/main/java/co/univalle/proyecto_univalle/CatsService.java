/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.univalle.proyecto_univalle;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author duvan
 */
public class CatsService {
    
    private static BufferedImage transformImage(URL url) {
        try {
//            URL url = new URL(cat.getUrl());
  
            HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
            httpcon.addRequestProperty("User-Agent", "");
            BufferedImage bufferedImage = ImageIO.read(httpcon.getInputStream());
            
            return bufferedImage;
            
        }catch(IOException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static void showCats() throws IOException{
        //1. vamos a traer los datos de la API
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();

        Response response = client.newCall(request).execute();
        
        String elJson = response.body().string();
        
        //cortar los corchetes
        elJson = elJson.substring(1, elJson.length());
        elJson = elJson.substring(0, elJson.length()-1);
              
        //crear u objeto de la clase Gson
        Gson gson = new Gson();
        Cats cats = gson.fromJson(elJson, Cats.class);
        
        //redimensionar en caso de necesitar
        try{
            URL url = new URL(cats.getUrl());
            
            ImageIcon fondoGato = new ImageIcon(transformImage(url));  
            
            if(fondoGato.getIconWidth() > 800){
                //redimensionamos
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 300, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }
            
            String menu = "Opciones: \n"
                    + " 1. ver otra imagen \n"
                    + " 2. Añadir a favoritos \n"
                    + " 3. Ver favoritos"
                    + " 4. Volver \n";
            
            String[] buttons = { "ver otra imagen", "Añadir a favoritos", "Ver favoritos", "volver" };
            String id_cat = cats.getId();
            String opcion = (String) JOptionPane.showInputDialog(null,menu, id_cat, JOptionPane.INFORMATION_MESSAGE, fondoGato, buttons,buttons[0]);
            
            int seleccion = -1;
            //validamos que opcion selecciona el usuario
            for(int i=0;i<buttons.length;i++){
                if(opcion.equals(buttons[i])){
                    seleccion = i;
                }
            }
            
            switch (seleccion){
                case 0:
                    showCats();
                    break;
                case 1:
                    handleFavorite(cats);
                    break;
                case 2:
                    showFavorite(cats.getApikey());
                    break;
                default:
                    break;
            }
            
        }catch(IOException e){
               System.out.println(e);
        }

    }

    public static void handleFavorite(Cats cat) {
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\""+cat.getId()+"\"\n}");
            Request request = new Request.Builder()
              .url("https://api.thecatapi.com/v1/favourites")
              .post(body)
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key", cat.getApikey())
              .build();
            
            client.newCall(request).execute(); 
            
            showCats();
                  
        }catch(IOException e){
            System.out.println(e);
        }
  
    }

    public static void showFavorite(String apikey) throws IOException {
    
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
          .url("https://api.thecatapi.com/v1/favourites")
          .method("GET", null)
          .addHeader("Content-Type", "application/json")
          .addHeader("x-api-key", apikey)
          .build();
        Response response = client.newCall(request).execute();
        // guardamos el string con la respuesta
        String elJson = response.body().string();
        
        //creamos el objeto gson
        Gson gson = new Gson();
        
        CatsFav[] catsArr = gson.fromJson(elJson,CatsFav[].class);
        
        if(catsArr.length > 0){
            int min = 1;
            int max  = catsArr.length;
            int aleatorio = (int) (Math.random() * ((max-min)+1)) + min;
            int indice = aleatorio-1;
            
            CatsFav gatofav = catsArr[indice];
            
                try{
                    URL url = new URL(gatofav.image.getUrl());
                    ImageIcon backgroundImg = new ImageIcon(transformImage(url));

                    if(backgroundImg.getIconWidth() > 800){
                        //redimensionamos
                        Image fondo = backgroundImg.getImage();
                        Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                        backgroundImg = new ImageIcon(modificada);
                    }

                    String menu = "Opciones: \n"
                            + " 1. ver otra imagen \n"
                            + " 2. Eliminar Favorito \n"
                            + " 3. Volver \n";

                    String[] botones = { "ver otro favorito", "eliminar favorito", "volver" };
                    String id_gato = gatofav.getId();
                    String opcion = (String) JOptionPane.showInputDialog(null,menu,id_gato, JOptionPane.INFORMATION_MESSAGE, backgroundImg, botones,botones[0]);

                    int select = -1;
                    //validamos que opcion selecciona el usuario
                    for(int i=0;i<botones.length;i++){
                        if(opcion.equals(botones[i])){
                            select = i;
                        }
                    }

                    switch (select){
                        case 0:
                            showFavorite(apikey);
                            break;
                        case 1:
                            deleteFvorite(gatofav);
                            break;
                        default:
                            break;
                    }

                }catch(IOException e){
                       System.out.println(e);
                }
            
        }
        
    }

    public static void deleteFvorite(CatsFav catFav){
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
              .url("https://api.thecatapi.com/v1/favourites/"+catFav.getId()+"")
              .delete(null)
              .addHeader("Content-Type", "application/json")
              .addHeader("x-api-key", catFav.getApikey())
              .build();

              client.newCall(request).execute();
            
            showCats();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
