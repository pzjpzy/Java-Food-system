/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Customer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author pangz
 */
public class orderStatus extends javax.swing.JPanel {


    JFrame frame;
    String userID = customer.userID;
    String orderID = customer.orderID;
    String line = null;
    String vendorID = "V0";
    Double total = 0.00;
        
    public orderStatus(JFrame frame) {
        initComponents();
        setBounds(0,0,1536,864);     //this line must exist in every JPanel
        this.frame = frame;  
        frame.setLayout(null);
        String cline = null;
        String content = "";
        
        try{
            FileReader fr = new FileReader("Order.txt");
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine())!= null){
                String values[] = line.split(",");
                
                        
                //show status
                if (values[1].equals(orderID)){
                    //calculate total
                    FileReader fr2 = new FileReader("Menu.txt");
                    BufferedReader br2  = new BufferedReader(fr2);
                    while((line = br2.readLine()) != null){
                        String menu[] = line.split(",");
                        if (menu[1].equals(values[2])){
                            total += Double.parseDouble(menu[2]) * Double.parseDouble(values[3]);
                            System.out.println(menu[2] + " x " +  values[3]);
                            System.out.println(total);
                        }
                    }
                    fr2.close();
                    br2.close();
                    cline = values[2] + ": " + values[3] + "\n";
                    content += cline;
                    switch(values[6]){
                        case "1":
                            jLabel3.setText("Paid");
                            break;
                        case "2":
                            jLabel3.setText("Delivery in progress...");
                            break;
                        case "3":
                            jLabel3.setText("Completed");
                            break;
                        case "4":
                            jLabel3.setText("Order cancelled");
                            break;
                        case "9":
                            jLabel3.setText("No Runner available");
                            break;
                    }
                }
                
            }
            fr.close();
            br.close();
        }catch(IOException e){
            System.out.println("error occured");
        }
        System.out.println("total:" + total);
        //show ordered items in text area
        jTextArea1.setText(content);
        
        // Timer to refresh status
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton4.setVisible(false);
                jButton5.setVisible(false);
                jLabel3.setText(customer.getOrderStatus(orderID));
                if (customer.getOrderStatus(orderID).equals("Paid")){
                    jButton3.setVisible(true);
                }//no runner
                else if (customer.getOrderStatus(orderID).equals("No Runner available")){
                    jButton3.setVisible(false);
                    jButton4.setVisible(true);
                    jButton5.setVisible(true);
                }
                else{
                    jButton3.setVisible(false);
                }
                
                
            }
        });
        timer.start(); // Start the timer
        
    }
    
    //buffer overloading (come from order history)
    public orderStatus(JFrame frame, String orderID) {
        initComponents();
        setBounds(0,0,1536,864);     //this line must exist in every JPanel
        this.frame = frame;  
        frame.setLayout(null);
        String cline = null;
        String content = "";
        this.orderID = orderID;
        
        try{
            FileReader fr = new FileReader("Order.txt");
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine())!= null){
                String values[] = line.split(",");
                if (values[1].equals(orderID)){
                    //calculate total
                    FileReader fr2 = new FileReader("Menu.txt");
                    BufferedReader br2  = new BufferedReader(fr2);
                    while((line = br2.readLine()) != null){
                        String menu[] = line.split(",");
                        if (menu[1].equals(values[2])){
                            total += Double.parseDouble(menu[2]) * Double.parseDouble(values[3]);
                            System.out.println(menu[2] + " x " +  values[3]);
                            System.out.println(total);
                        }
                    }
                    cline = values[2] + ": " + values[3] + "\n";
                    content += cline;
                    switch(values[6]){
                        case "1":
                            jLabel3.setText("Paid");
                            break;
                        case "2":
                            jLabel3.setText("Delivery in progress...");
                            break;
                        case "3":
                            jLabel3.setText("Completed");
                            break;
                        case "4":
                            jLabel3.setText("Order cancelled");
                            break;
                        case "9":
                            jLabel3.setText("No Runner available");
                            break;
                    }
                }
                
            }
            fr.close();
            br.close();
        }catch(IOException e){
            System.out.println("error occured");
        }
        
        if (!customer.getOrderStatus(orderID).equals("No Runner available")){
            // Reorder button
            JButton Reorder = new JButton("Reorder");
            Reorder.setBounds(580, 720, 400, 100);
            Reorder.setFocusable(false);
            Reorder.setFont(new Font("My Boli", Font.PLAIN, 50));
            Reorder.setBackground(new Color(209, 232, 238));
            Reorder.addActionListener((ActionEvent e) -> {
                frame.getContentPane().removeAll();
                orderConfirm panel = new orderConfirm(frame, orderID, true);
                frame.add(panel);
                frame.revalidate();
                frame.repaint();
            });
            frame.add(Reorder);
        }
        
                        
                        
        //show ordered items in text area
        jTextArea1.setText(content);
        
        // Timer to refresh status
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton4.setVisible(false);
                jButton5.setVisible(false);
                jLabel3.setText(customer.getOrderStatus(orderID));
                if (customer.getOrderStatus(orderID).equals("Paid")){
                    jButton3.setVisible(true);
                }//no runner
                else if (customer.getOrderStatus(orderID).equals("No Runner available")){
                    jButton3.setVisible(false);
                    jButton4.setVisible(true);
                    jButton5.setVisible(true);
                }
                else{
                    jButton3.setVisible(false);
                }
                
                
            }
        });
        timer.start(); // Start the timer
        
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

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
        jLabel1.setText("Order status");
        jLabel1.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 700, Short.MAX_VALUE)
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

        jPanel2.setBackground(new java.awt.Color(120, 139, 179));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Status of current order:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Status");

        jButton3.setBackground(new java.awt.Color(209, 232, 238));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton3.setText("Cancel Order");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(209, 232, 238));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton4.setText("Dine in");
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(209, 232, 238));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton5.setText("Take away");
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jLabel3.setHorizontalAlignment(SwingConstants.CENTER); // Center horizontally
        jLabel3.setVerticalAlignment(SwingConstants.CENTER);   // Center vertically

        jPanel3.setBackground(new java.awt.Color(120, 139, 179));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(120, 139, 179));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jTextArea1.setForeground(java.awt.Color.white);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Hello");
        jTextArea1.setFocusable(false);
        jTextArea1.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane1.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String order;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        System.out.println(userID + orderID);

        
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
            

                

            // Loop through existing orders to check if the record already exists
            boolean exists = false;
            for (int i=0; i < table.size(); i++) {
                String record = table.get(i);
                String recor[] = record.split(",");
                if (record != null && recor[0].equals(userID) && recor[1].equals(orderID)) {   //if record same, then overwrite it
                    //order line
                    System.out.println("yes");
                    recor[6] = "4";
                    order = String.join(",", recor);
                    table.set(i,order);
                    vendorID = recor[7];
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
            
            customer.refund(userID, total);
            Notify.setNotification(vendorID, "A customer has cancelled your order");
            JOptionPane.showMessageDialog(this, "Order cancelled!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Order cancellation failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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
                    recor[4] = "Dine in";
                    recor[6] = "1";
                    order = String.join(",", recor);
                    table.set(i,order);
                    vendorID = recor[7];
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
            
            
            Notify.setNotification(vendorID, "A customer has changed order type");
            System.out.println("status changed successfully");
        } catch (IOException e) {
            System.out.println("error");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
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
                    recor[4] = "Take away";
                    recor[6] = "1";
                    order = String.join(",", recor);
                    table.set(i,order);
                    vendorID = recor[7];
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
            
            
            Notify.setNotification(vendorID, "A customer has changed order type");
            System.out.println("status changed successfully");
        } catch (IOException e) {
            System.out.println("error");
        }
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    
}