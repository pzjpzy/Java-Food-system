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
public class customer extends user {
    
     @Override
     public void setOrderID(String ID){
         this.orderID = ID;
     }
     @Override
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
                        case "9" -> {
                            return "No Runner available";
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
         String cusID = "C0";
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
                        if(!customer.checkUser(userIDtem)){
                             //find customer ID and date
                            FileReader fw2 = new FileReader("Order.txt");
                            BufferedReader bw2 = new BufferedReader(fw2);

                            String line2;
                            while ((line2 = bw2.readLine()) != null) {
                                String[] orderl = line2.split(",");


                                if (orderl[1].equals(recor[2])){
                                    cusID = orderl[0];
                                    System.out.println("yes, got cusID");
                                }
                            }
                            fw2.close();
                            bw2.close();
                            customer.refund(cusID, 10.00);
                            customer.changeOrderStatus(recor[2], "9");
                        }
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
     
     public static void refund(String userID, Double amount) throws IOException {
        
        String line = null;
        String balanc;
        String balanced;
        FileReader fr4 = new FileReader("Balance.txt");
        BufferedReader br4  = new BufferedReader(fr4);
            ArrayList<String> table = new ArrayList<>();
            
            while((line = br4.readLine()) != null){
                table.add(line);
            }
            fr4.close();
            br4.close();
        for (int i=0; i < table.size(); i++) {
                String record = table.get(i);
                String balance[] = record.split(",");
                if (balance[0].equals(userID)){
                        balanc = String.format("%.2f",Double.valueOf(balance[1]) + amount);
                        //balance - total
                        balance[1] = balanc;
                        balanced = String.join(",", balance);
                        table.set(i,balanced);
                        System.out.println("refunded for userID");
                       
                }
                else{
                    //do nothing
                }
        }
        
        fr4.close();
        br4.close();
        
        //write every line into file
            FileWriter fw4 = new FileWriter("Balance.txt");
            for (String record : table) {
                if (record != null){
                    fw4.append(record + "\n");
                }
            }
            fw4.close(); 

        

    }
     
     public static boolean checkUser(String userID){
         try{
            //find customer name and date
           FileReader fw3 = new FileReader("users.txt");
           BufferedReader bw3 = new BufferedReader(fw3);

           String line3;
           while ((line3 = bw3.readLine()) != null) {
               String[] user = line3.split(",");

               if (user[3].trim().equals(userID)){
                   System.out.println("found user");
                   return true;
               }
           }
           fw3.close();
           bw3.close();
           
         } catch (IOException e){
             System.out.println("error");
         }
         return false;
     }
}
