/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customer;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import  Vendor.*;

/**
 *
 * @author pangz
 */
public class frame extends JFrame{
    public frame() {
        SignUp panel1  = new SignUp(this);           //put your login panel here, you're putting panel into this frame
        LogIn panel2 = new LogIn(this);
        LogIn panel3 = new LogIn(this);
        
        setTitle("Ordering Food System");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(panel1);
      
        }
   }
