/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pangz
 */
public class Notify {
    
    public void setNotification(String user_ID,String content){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate currentDate = LocalDate.now();
            String formatDate = currentDate.format(formatter);
            FileWriter fw = new FileWriter("Notification.txt",true);
            fw.append(user_ID + ":" + "0" + ":" + formatDate + ":" + content + "\n");
            fw.close();
            }catch(IOException ex) {
                System.out.println("Some error occured in Method setNotification");
            }

    }
}
