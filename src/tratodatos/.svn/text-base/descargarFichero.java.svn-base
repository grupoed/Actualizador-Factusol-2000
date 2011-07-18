/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tratodatos;

/**
 *
 * @author Victor
 */

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
abstract public class descargarFichero {
    
    static public void descargarFichero(String url1,String path){
        try{
            URL url=new URL(url1);
            URLConnection urlCon=url.openConnection();
            // acceso al contenido web
            InputStream is = urlCon.getInputStream();
            // Fichero en el que queremos guardar el contenido
            FileOutputStream fos = new FileOutputStream(path);
            // buffer para ir leyendo.
            byte [] array = new byte[1000];
            // Primera lectura y bucle hasta el final
            int leido = is.read(array);
            while (leido > 0) {
                fos.write(array,0,leido);
                leido=is.read(array);
            }
            // Cierre de conexion y fichero.
            is.close();
            fos.close();
        }catch(Exception e){
            System.out.println("Ha ocurrido el siguiente error:"+e.getMessage());
        }
    }
}
