/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pangz
 */
public class test {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate currentDate = LocalDate.now();
        String formatDate = currentDate.format(formatter);
        System.out.println(formatDate);
    }
    
    
}
