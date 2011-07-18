/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public class ventana extends JFrame{
    
    
    public ventana() throws InterruptedException, IOException{
        this.setTitle("Programa de Gestión de Grupo Escudero Dueso S.L.");
        Panel.createAndShowGUI(this);
    }
    
}
