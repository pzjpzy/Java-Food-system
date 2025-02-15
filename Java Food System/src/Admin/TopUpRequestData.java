/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nour
 */
public class TopUpRequestData {
    private static final String FILE_NAME = "Topup.txt";

    // Save top-up requests to file
    public static void saveRequests(List<TopUpRequest> requests) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (TopUpRequest request : requests) {
                writer.write(request.getCustomerID() + "," + request.getAmount() + "," + request.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load top-up requests from file
    public static List<TopUpRequest> loadRequests() {
        List<TopUpRequest> requests = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        if (!file.exists()) return requests; // If file doesn't exist, return empty list

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    TopUpRequest request = new TopUpRequest(parts[0], Double.parseDouble(parts[1]));
                    request.setStatus(parts[2]);
                    requests.add(request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requests;
    }
}
