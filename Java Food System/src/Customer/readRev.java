/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;

public class readRev extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private List<String[]> reviewsList;
    private JFrame frame; // Store the reference to the parent frame
    /**
     * Creates new form readCusRev
     */
    public readRev() {
        initComponents();
        initializeTableModel(); // Initialize table structure
        loadReviews(); // Load reviews from review.txt
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1552, 837));

        jLabel1.setText("Read Reviews");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1428, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(679, 679, 679)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
filterReviews();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frame.remove(this);
        review_complaint panel = new review_complaint(frame);
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

  private void initializeTableModel() {
        String[] columns = { "Review ID", "Order ID", "Customer ID", "Review", "Rating" };
        tableModel = new DefaultTableModel(columns, 0);
        jTable1.setModel(tableModel);
    }

    /**
     * Loads reviews from "review.txt" and populates the table.
     */
    private void loadReviews() {
        reviewsList = new ArrayList<>();
        tableModel.setRowCount(0); // Clear existing rows
        Set<String> orderIds = new HashSet<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("reviews.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    reviewsList.add(data);
                    tableModel.addRow(data); // Add row to table
                    orderIds.add(data[1]); // Store Order IDs for dropdown
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading reviews: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Populate combo box with unique Order IDs
        jComboBox1.removeAllItems();
        jComboBox1.addItem("All Orders");
        for (String orderId : orderIds) {
            jComboBox1.addItem(orderId);
        }
    }

    /**
     * Filters reviews based on selected Order ID.
     */
private void filterReviews() {
    if (jComboBox1.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "No order selected!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String selectedOrder = (String) jComboBox1.getSelectedItem();
    
    if (reviewsList == null || reviewsList.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No reviews available!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    tableModel.setRowCount(0); // Clear table before filtering

    for (String[] review : reviewsList) {
        if (review.length > 1 && ("All Orders".equals(selectedOrder) || review[1].equals(selectedOrder))) {
            tableModel.addRow(review);
        }
    }
}

    /**
     * Main method to run the application.
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            readRev frame = new readRev();
            frame.setVisible(true);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
