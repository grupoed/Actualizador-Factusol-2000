package interfaz;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdom.JDOMException;
import tratodatos.introducirProductos;

public class Panel implements ItemListener,ActionListener {
    JPanel cards; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Actualizar Factusol";
    final static String TEXTPANEL = "Actualizar Prestashop";
    JTextField DSN;
    JTextField host;
    JButton factusol;
    JButton prestashop;
    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(new FlowLayout()); //use FlowLayout
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        
        //Create the "cards".
        JPanel card1 = new JPanel();
        card1.setLayout(new BoxLayout(card1,BoxLayout.PAGE_AXIS));
        JLabel informa=new JLabel("Introduzca el nombre del archivo DSN y haga click en el botón 'Actualizar Factusol'");
        card1.add(informa);
        this.DSN=new JTextField(20);
        card1.add(this.DSN,BorderLayout.CENTER);
        factusol=new JButton("Actualizar Factusol");
        this.factusol.addActionListener(this);
        card1.add(this.factusol);
        JPanel card2 = new JPanel();
        card2.setLayout(new BoxLayout(card2,BoxLayout.PAGE_AXIS));
        JLabel informa2=new JLabel("Introduzca la url del host del servidor Prestashop y haga click en el botón 'Actualizar Factusol'");
        card2.add(informa2);
        this.host=new JTextField(20);
        card2.add(host);
        prestashop=new JButton("Actualizar Prestashop");
        prestashop.addActionListener(this);
        card2.add(prestashop);
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, BUTTONPANEL);
        cards.add(card2, TEXTPANEL);
        
        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI(JFrame ventana) {
        //Create and set up the window.
        JFrame frame =ventana;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        Panel demo = new Panel();
        demo.addComponentToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.factusol)){
            if(this.DSN.getText() == null || this.DSN.getText().equals("")){
                JOptionPane.showMessageDialog(null,"No ha introducido el nombre de servidor DSN" ,"ERROR",JOptionPane.OK_OPTION);
            }
            else{
                try {
                    new introducirProductos(this.DSN.getText());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JDOMException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if(e.getSource().equals(this.prestashop)){
            if(this.host.getText() == null || this.host.getText().equals("")){
                JOptionPane.showMessageDialog(null,"No ha introducido el nombre de servidor Prestashop" ,"ERROR",JOptionPane.OK_OPTION);
            }
            else{
                try {
                    new introducirProductos(this.DSN.getText());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JDOMException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
