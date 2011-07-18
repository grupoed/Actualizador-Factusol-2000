/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tratodatos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Dell
 */
class escribirFichero {
    
    static void escribirFichero(String path,Set<String> claves) throws IOException{
        File fichero=new File(path);
        if(!fichero.exists()){
            fichero.createNewFile();
        }
        BufferedWriter bw=new BufferedWriter(new FileWriter(path));
        Iterator it=claves.iterator();
        bw.write("clave\n");
        while(it.hasNext()){
            bw.write((String)it.next()+"\n");
        }
        bw.close();
    }
    
}
