/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DeliveryRunner;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author pangz
 */
public class zaijieFrame extends JFrame{
    public zaijieFrame() {
        mainPage panelz = new mainPage(this);
        
        setTitle("Ordering Food System");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(panelz);
      
        }
}
