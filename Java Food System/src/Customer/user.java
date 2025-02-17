/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customer;

import javax.swing.JFrame;

/**
 *
 * @author pangz
 */
public abstract class user {
    public static String userID = "R1";
     public static String orderID = null;
     
     public String getUserID(){
         return userID;
     }
     
     public void setUserID(String userID){
         this.userID = userID;
     }
     
     public abstract void setOrderID(String ID);
     public abstract String getOrderID();
     
     public static void logout(JFrame frame){
        frame.getContentPane().removeAll();
        LogIn panel = new LogIn(frame);   //the panel you want to switch to
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
     }
}
