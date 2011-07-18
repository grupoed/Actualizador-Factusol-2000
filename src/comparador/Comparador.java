/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparador;

import interfaz.ventana;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jdom.JDOMException;

/**
 *
 * @author Victor
 */
public class Comparador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, JDOMException, InterruptedException {
        new ventana();
    }
}
