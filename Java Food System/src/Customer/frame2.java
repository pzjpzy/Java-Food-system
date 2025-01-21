/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customer;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author pangz
 */
public class frame2 extends JFrame{
    public frame2() {
//        selectVendor panelz = new selectVendor(this);       //put your login panel here, you're putting panel into this frame
//        orderStatus panelz = new orderStatus(this);
        orderHistory panelz = new orderHistory(this);
        
        setTitle("Ordering Food System");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(panelz);
      
        }
}
