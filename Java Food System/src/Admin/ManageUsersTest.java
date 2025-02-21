/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;
import javax.swing.JFrame;

/**
 *
 * @author Nour
 */
public class ManageUsersTest {
     public static void main(String[] args) {
        JFrame frame = new JFrame("Manage Users");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        frame.setUndecorated(false); // Keep window decorations (title bar, etc.)
        frame.add(new ManageUsers()); // Add your ManageUsers panel
        frame.setVisible(true); // Show the window
    }
}
