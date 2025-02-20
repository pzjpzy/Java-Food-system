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
public class frame1 extends JFrame{
    public frame1() {
        Notification panel  = new Notification(this);           //put your login panel here
        
        setTitle("Food Management System");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(panel);
        }
   }
