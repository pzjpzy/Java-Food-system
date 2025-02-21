/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Customer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author pangz
 */
public class orderHistory extends javax.swing.JPanel {


    JFrame frame;
    public orderHistory(JFrame frame) {
        initComponents();
        setBounds(0, 0, 1536, 864); // This line must exist in every JPanel
        this.frame = frame;
        frame.setLayout(null);
        int highestNum = 0;
        
        //get highes orderNum
        try {
            //store every line in array
            FileReader fr = new FileReader("Order.txt");
            BufferedReader br  = new BufferedReader(fr);
            String line = null;

            int num = 0;
            ArrayList<String> table = new ArrayList<>();
            
            while((line = br.readLine()) != null){
                String values[] = line.split(",");
                try{
                    num = Integer.parseInt(values[1].substring(1));
                }catch (ArrayIndexOutOfBoundsException e){
                    num = 0;
                }
                
                if (num > highestNum){
                    highestNum = num;
                }
            }
            } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving quantities.", "Error", JOptionPane.ERROR_MESSAGE);
        }
            System.out.println(highestNum);

            
            
        // Create a panel for the scrollable content
        JPanel scrollp = new JPanel();
        scrollp.setLayout(new BoxLayout(scrollp, BoxLayout.Y_AXIS));
        String orderID;

        try {
            FileReader fr = new FileReader("order.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            
            for(int i=1; i <= highestNum; i++){
                orderID = "O" + String.valueOf(i);
                
                System.out.println("loop" + i);
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    
                    System.out.println("a1:" + customer.userID + "b1:" + orderID);
                    System.out.println("a2:" + values[0] + "b3:" + values[1]);
                    if(values[0].equals(customer.userID) && values[1].equalsIgnoreCase(orderID)){
                        System.out.println("a:" + customer.userID + "b:" + orderID);
                        // Create a sub-panel for each vendor
                        JPanel subban = new JPanel();
                        subban.setLayout(null);
                        subban.setBackground(new Color(92, 201, 205));
                        subban.setPreferredSize(new Dimension(1100, 140)); // Set preferred size

                        // Date label
                        JLabel label = new JLabel("Date: " + values[5]);
                        label.setFont(new Font("Arial", Font.BOLD, 40));
                        label.setBounds(10, 40, 400, 50);
                        subban.add(label);
                        
                        // Status label
                        JLabel label2 = new JLabel("Status: " + customer.getOrderStatus(values[1]));
                        label2.setFont(new Font("Arial", Font.BOLD, 30));
                        label2.setBounds(350, 40, 300, 50);
                        subban.add(label2);

                        // Review button
                        JButton review = new JButton("Review");
                        review.setBounds(650, 20, 200, 100);
                        review.setFocusable(false);
                        review.setFont(new Font("My Boli", Font.PLAIN, 25));
                        review.setBackground(new Color(209, 232, 238));
                        subban.add(review);

                        // Details button
                        JButton Details = new JButton("Details");
                        Details.setBounds(880, 20, 200, 100);
                        Details.setFocusable(false);
                        Details.setFont(new Font("My Boli", Font.PLAIN, 25));
                        Details.setBackground(new Color(209, 232, 238));
                        Details.addActionListener((ActionEvent e) -> {
                            frame.getContentPane().removeAll();
                            orderStatus panel = new orderStatus(frame, values[1]);
                            frame.add(panel);
                            frame.revalidate();
                            frame.repaint();
                        });
                        subban.add(Details);

                        // Add sub-panel to the scrollable panel
                        scrollp.add(subban);
                        
                        break;
                    }
                }
            }
            

            br.close();
            fr.close();

            // Set the preferred size for the scrollable panel
            scrollp.setPreferredSize(new Dimension(1100, scrollp.getComponentCount() * 160));

            // Create a JScrollPane with the scrollable panel as its viewport
            JScrollPane scrollPane = new JScrollPane(scrollp);
            scrollPane.setBounds(200, 200, 1100, 550); // Set bounds for JScrollPane
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            // Add the JScrollPane to the frame
            frame.add(scrollPane);
            frame.revalidate();
            frame.repaint();

        } catch (IOException e) {
            System.out.println("Error reading vendor file: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(186, 208, 231));
        setMinimumSize(new java.awt.Dimension(1552, 837));
        setPreferredSize(new java.awt.Dimension(1552, 837));

        jPanel1.setBackground(new java.awt.Color(76, 88, 126));

        jButton1.setBackground(new java.awt.Color(136, 151, 170));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton1.setText("Log out");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setText("Order history");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 723, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jButton2.setBackground(new java.awt.Color(209, 232, 238));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton2.setText("Back");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 568, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        user.logout(frame);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        frame.getContentPane().removeAll();
        orderMainPage panel = new orderMainPage(frame);   //the panel you want to switch to
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
