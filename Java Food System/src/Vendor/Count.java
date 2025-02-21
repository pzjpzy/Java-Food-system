/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vendor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pangz
 */
public class Count {
    public double getTotal(String location,String location1, String vendorID){
        // Map to store vendor_id as key and menu details (array[1] and array[2]) as value
        Map<String, String[]> vendorMenuMap = new HashMap<>();

        // Read menu.txt and populate the vendorMenuMap
        try {
            FileReader fr = new FileReader(location);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(","); // Assuming the file is comma-separated
                if (array.length >= 3 && array[0].equals(vendorID)) {
                    String vendorId = array[0].trim();
                    String menuItem = array[1].trim();
                    String price = array[2].trim();

                    // Debug: Print parsed values
//                    System.out.println("Menu - Vendor ID: " + vendorId + ", Item: " + menuItem + ", Price: " + price);

                    vendorMenuMap.put(vendorId, new String[]{menuItem, price});
                } else {
                    System.err.println("Invalid line in menu.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Debug: Print the vendor menu map
//        System.out.println("Vendor Menu Map: " + vendorMenuMap);

        // Variables to store the total and count
        double total = 0.0;

        // Read order.txt and process the data
        try {
            FileReader fr = new FileReader(location1);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] array1 = line.split(","); 
                if (array1.length >= 8) {
                    String orderItem = array1[2].trim();
                    String orderStatus = array1[6].trim();
                    String vendorId = array1[7].trim();

                    // Debug: Print parsed values
//                    System.out.println("Order - Vendor ID: " + vendorId + ", Item: " + orderItem + ", Status: " + orderStatus);

                    if (vendorMenuMap.containsKey(vendorID)) {
                        String[] menuDetails = vendorMenuMap.get(vendorID);
                        String menuItem = menuDetails[0];
                        double price = Double.parseDouble(menuDetails[1]);

                        // Check if the conditions are met
                        if (orderItem.equals(menuItem) && orderStatus.equals("2") || orderStatus.equals("3")) {
                            total += price; // Add the price to the total

                            // Debug: Print matched order
//                            System.out.println("Matched Order - Vendor ID: " + vendorId + ", Item: " + orderItem + ", Price: " + price);
                        }
                    }
                } else {
                    System.err.println("Invalid line in order.txt: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }
    
    public double getTotal(String txt,String runnerID){
        int count = 0;
        try {
            FileReader fr = new FileReader(txt);
            BufferedReader br = new BufferedReader(fr);
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] array = line.split(":"); // Assuming the file is comma-separated
                if (array.length >= 5 && array[1].equals(runnerID) && array[4].equals("2")) {
                    count++;
                } 
//                else {
//                    System.err.println("Invalid line in menu.txt: " + line);
//                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
