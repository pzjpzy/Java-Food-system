package Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TicketManagerPanel extends javax.swing.JPanel {

    private List<String[]> ticketList; // Stores ticket data
    private DefaultTableModel tableModel;
    private JFrame frame; // Store the reference to the parent frame
    /**
     * Creates new form TicketManagerPanel
     */
public TicketManagerPanel(JFrame frame) {
    initComponents();
    this.frame = frame;
    setBounds(0, 0, 1536, 864); 
    frame.setLayout(null);
    initializeTableModel(); 
    loadTickets(); 
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("TicketManagerPanel"));
        setMinimumSize(new java.awt.Dimension(1552, 837));
        setPreferredSize(new java.awt.Dimension(1552, 837));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

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
        jButton1.setMaximumSize(new java.awt.Dimension(55, 25));
        jButton1.setMinimumSize(new java.awt.Dimension(55, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Resolved");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("In progress");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1138, 1138, 1138)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 55, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    updateTicketStatus("In Progress"); // Calls method to update status
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frame.getContentPane().removeAll();
        ManagerHome panel = new ManagerHome(frame);   //the panel you want to switch to
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    updateTicketStatus("Resolved"); // Calls method to update status
    }//GEN-LAST:event_jButton2ActionPerformed
 
    private void initializeTableModel() {
        String[] columnNames = { "Ticket ID", "Order ID", "Customer ID", "Complaint", "Rating", "Status" };
        tableModel = new DefaultTableModel(columnNames, 0);
        jTable1.setModel(tableModel);
    }

    private void loadTickets() {
        ticketList = new ArrayList<>();
        tableModel.setRowCount(0); // Clear existing rows

        try (BufferedReader br = new BufferedReader(new FileReader("complaints.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    ticketList.add(data);
                    tableModel.addRow(data); // Add row to the table
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading tickets: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTickets() {
        String selectedStatus = (String) jComboBox1.getSelectedItem();
        tableModel.setRowCount(0); // Clear the table

        for (String[] ticket : ticketList) {
            if (selectedStatus.equals("All") || ticket[5].equals(selectedStatus)) {
                tableModel.addRow(ticket); // Add matching rows
            }
        }
    }

    private void updateTicketStatus(String newStatus) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ticket to update.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String ticketID = (String) tableModel.getValueAt(selectedRow, 0);
        for (String[] ticket : ticketList) {
            if (ticket[0].equals(ticketID)) {
                ticket[5] = newStatus; // Update status
                break;
            }
        }

        saveTickets();
        loadTickets(); // Refresh the table
        JOptionPane.showMessageDialog(this, "Ticket status updated to " + newStatus + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveTickets() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("complaints.txt"))) {
            for (String[] ticket : ticketList) {
                writer.write(String.join(",", ticket));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving tickets: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Main method to run the panel as a standalone application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ticket Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TicketManagerPanel(frame));
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
