/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Scanner;

public class TableManager {
    private JTable table;
    private DefaultTableModel model;
    private String filePath = "users.txt";

    // Constructor: Takes JTable as input and manages it
    public TableManager(JTable table) {
        this.table = table;
        this.model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(new String[]{"UserID", "Name", "Role"}); // Set column headers

        loadUsers(); // Load data when initialized
    }

    // Load data from users.txt into JTable
    public void loadUsers() {
        File file = new File(filePath);
        model.setRowCount(0); // Clear existing data

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] userData = line.split(",");

                if (userData.length == 3) { // Ensure correct format
                    model.addRow(userData);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Add a new user to the table and save it to users.txt
    public void addUser(String userid, String name, String role) {
        model.addRow(new String[]{userid, name, role});
        saveToFile();
    }

    // Remove selected row
    public void removeSelectedUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            saveToFile();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a user to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Save table data back to users.txt
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.println(
                        model.getValueAt(i, 0) + "," +  // UserID
                        model.getValueAt(i, 1) + "," +  // Name
                        model.getValueAt(i, 2)          // Role
                );
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
