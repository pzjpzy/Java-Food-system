/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author pangz
 */
public class customer {
    public static String userID = "C1";
     public static String orderID = null;
     
     public void setOrderID(String ID){
         this.orderID = ID;
     }
     public String getOrderID(){
         return orderID;
     }
     
     public static String getOrderStatus(String orderID){
         String line = null;
         try{
            FileReader fr = new FileReader("Order.txt");
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine())!= null){
                String values[] = line.split(",");
                if (values[1].equals(orderID)){
                    switch(values[6]){
                        case "0" -> {
                            return "Not Paid";
                        }
                        case "1" -> {
                            return "Paid";
                        }
                        case "2" -> {
                            return "Delivery in progress...";
                        }
                        case "3" -> {
                            return "Completed";
                        }
                        case "4" -> {
                            return "Order cancelled";
                        }
                    }
                }

            }
            fr.close();
            br.close();
        }catch(IOException ea){
            System.out.println("error occured");
        }
        return null;
     }
}
