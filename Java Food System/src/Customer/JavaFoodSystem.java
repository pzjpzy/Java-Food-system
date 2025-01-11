/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Customer;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author pangz
 */
public class JavaFoodSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("hello");
        JFrame dummy = new JFrame();
        
        LogIn panel  = new LogIn();  //your panel
        
        dummy.setTitle("Clinic Management System"); 
        dummy.setVisible(true);
        dummy.setDefaultCloseOperation(EXIT_ON_CLOSE);
        dummy.setResizable(false);
        dummy.setLayout(null);
        dummy.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dummy.add(panel);                                    //add your panel
        // TODO code application logic here
    }
    
}
