package Customer;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * JPanel to allow customers to provide a review for an order.
 */
public class CustReview extends javax.swing.JPanel {
    private List<String> orders; // List of available orders
    private JFrame frame;

    /**
     * Creates new form CustReview
     */
    public CustReview(JFrame frame) {
        initComponents();
        this.frame = frame;
        setBounds(0, 0, 1536, 864); 
        frame.setLayout(null);
        
        orders = new ArrayList<>();
        loadOrders();
        populateOrderDropdown();
    }

    /**
     * Loads available orders from a file or a data source.
     */
    private void loadOrders() {
        // Example: Load orders from a file (you can customize this logic)
        try (BufferedReader br = new BufferedReader(new FileReader("order.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String array [] = line.split(",");
                orders.add(array[1]); // Each line represents an order ID
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading orders: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populates the order dropdown with available orders.
     */
    private void populateOrderDropdown() {
        for (String order : orders) {
            jComboBox1.addItem(order); // Add each order ID to the dropdown
        }
    }
   private void filterOrders() {
        String selectedOrder = (String) jComboBox1.getSelectedItem();


        StringBuilder filteredOrders = new StringBuilder();
        for (String order : orders) {
            if (order.equals(selectedOrder)) {
                filteredOrders.append(order).append("\n");
            }
        }

        // Display the filtered orders in a dialog
        JOptionPane.showMessageDialog(this, "Filtered Order:\n" + filteredOrders, "Filtered Orders", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Saves the review to a file.
     *
     * @param orderID   The ID of the order being reviewed
     * @param review    The review content
     * @param rating    The rating (1-5)
     */
    private void saveReview(String orderID, String review, int rating) {
        String reviewID = "R" + UUID.randomUUID().toString().substring(0, 6).toUpperCase(); // Generate a unique review ID
        String customerID = "C123"; // Example: Replace with actual customer ID
        String reviewEntry = reviewID + "," + orderID + "," + customerID + "," + review + "," + rating;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reviews.txt", true))) {
            writer.write(reviewEntry);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Review submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving review: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to launch the application


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Make a review"));
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(1552, 837));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(1552, 837));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(371, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
     filterOrders();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String selectedOrder = (String) jComboBox1.getSelectedItem();
        String reviewContent = jTextArea1.getText().trim();

        if (selectedOrder == null || selectedOrder.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an order to review.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (reviewContent.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your review.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String ratingInput = JOptionPane.showInputDialog(this, "Enter rating (1-5):", "Rating", JOptionPane.QUESTION_MESSAGE);
        int rating;
        try {
            rating = Integer.parseInt(ratingInput);
            if (rating < 1 || rating > 5) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid rating. Please enter a number between 1 and 5.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save the review
        saveReview(selectedOrder, reviewContent, rating);
        jTextArea1.setText(""); // Clear the text area    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        review_complaint panel = new review_complaint(frame);
        frame.remove(this);
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
