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
public class ApprovalListTest {
    public static void main(String[] args) {
        // Create a JFrame to hold the ApprovalList panel
        JFrame frame = new JFrame("Approval List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(600, 400); // Set the window size
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set full-screen mode
        frame.setLocationRelativeTo(null); // Center the window

        // Create an instance of ApprovalList and add it to the frame
        ApprovalList approvalPanel = new ApprovalList(frame);
        frame.add(approvalPanel);

        frame.setVisible(true); // Show the window
    }
}
