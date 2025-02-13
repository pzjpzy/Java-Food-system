/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customer;
import javax.swing.*;

public class ChooseBalanceTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Choose Balance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set full screen
        //frame.setUndecorated(true);
        
        ChooseBalance panel = new ChooseBalance(frame);
        frame.add(panel);
        
        frame.setVisible(true);
    }
}
