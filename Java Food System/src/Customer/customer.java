/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pangz
 */
public class customer {
//    public static String userID = "C1";
    public static String userID = "R1";
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
     
     public static void changeOrderStatus(String orderID, String status){
         String order;
        
        try {
            //store every line in array
            FileReader fr = new FileReader("Order.txt");
            BufferedReader br  = new BufferedReader(fr);
            String line = null;
            ArrayList<String> table = new ArrayList<>();
            
            while((line = br.readLine()) != null){
                table.add(line);
            }
            fr.close();
            br.close();
               
            for (int i=0; i < table.size(); i++) {
                String record = table.get(i);
                String recor[] = record.split(",");
                
                if (record != null && recor[1].equals(orderID)) {   //if record same, then overwrite it
                    //order line
                    recor[6] = status;
                    order = String.join(",", recor);
                    table.set(i,order);
                }
            }
            
            
            //write every line into file
            FileWriter fw = new FileWriter("Order.txt");
            for (String record : table) {
                if (record != null){
                    fw.append(record + "\n");
                }
            }
            fw.close(); 
            
            
            
            System.out.println("status changed successfully");
        } catch (IOException e) {
            System.out.println("error");
        }
     }
     
     public static void changeTaskStatus(String orderID, String status){
         String order;
         String userIDtem;
        try {
            //store every line in array
            FileReader fr = new FileReader("Task.txt");
            BufferedReader br  = new BufferedReader(fr);
            String line = null;
            ArrayList<String> table = new ArrayList<>();
            
            while((line = br.readLine()) != null){
                table.add(line);
            }
            fr.close();
            br.close();
               
            //update status and runner
            for (int i=0; i < table.size(); i++) {
                String record = table.get(i);
                String recor[] = record.split(":");
                
                if (record != null && recor[2].equals(orderID)) {   //if record same, then overwrite it
                    //order line
                    if (status.equals("3")){
                        userIDtem = "R" +  String.valueOf(Integer.parseInt(userID.substring(1)) + 1);
                        status = "0";
                    } else userIDtem = userID;
                    recor[1] = userIDtem;
                    recor[4] = status;
                    order = String.join(":", recor);
                    table.set(i,order);
                }
            }
            
            
            //write every line into file
            FileWriter fw = new FileWriter("Task.txt");
            for (String record : table) {
                if (record != null){
                    fw.append(record + "\n");
                }
            }
            fw.close(); 
            
            
            
            System.out.println("status changed successfully");
        } catch (IOException e) {
            System.out.println("error");
        }
     }
}
