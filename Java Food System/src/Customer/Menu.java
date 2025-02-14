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

/**
 *
 * @author pangz
 */
public class Menu extends javax.swing.JPanel {


    JFrame frame;
    String userID = customer.userID;
    protected ArrayList<ItemData> items = new ArrayList<>();
    // Create a panel for the scrollable content
    JPanel scrollp = new JPanel();
    int highestNum = 0;
        
    public Menu(JFrame frame, String vendorID, String vendorName) {
        initComponents();
        setBounds(0,0,1536,864);     //this line must exist in every JPanel
        this.frame = frame;  
        frame.setLayout(null);
        jLabel1.setText(vendorName + "'s Menu");
        // Create a panel for the scrollable content
        JPanel scrollp = new JPanel();
        scrollp.setLayout(new BoxLayout(scrollp, BoxLayout.Y_AXIS));
        
        try{
        FileReader fr = new FileReader("Menu.txt");
        BufferedReader br = new BufferedReader(fr);
        //show all dishes
        String line = null;
        int height = 200;
        
        while((line = br.readLine())!= null){
 
            String values[] = line.split(",");
            
            if(values[0].equals(vendorID)){
                // Create panel
                JPanel subban = new JPanel();
                subban.setLayout(null);
                subban.setBackground(new Color(92, 201, 205));
                subban.setPreferredSize(new Dimension(1100, 140)); // Set preferred size

                //items name
                JLabel label = new JLabel("Item: " + values[1]);
                label.setFont(new Font("Arial", Font.BOLD, 50));
                label.setBounds(50,40,600,70);
                
                //item price
                JLabel price = new JLabel("RM" + values[2]);
                price.setFont(new Font("Arial", Font.BOLD, 30));
                price.setBounds(550,40,200,70);
                

                
                //Quantity:
                JLabel quan = new JLabel("Quantity:");
                quan.setFont(new Font("Arial", Font.BOLD, 50));
                quan.setBounds(750,40,300,70);
                
                //quantity textfield
                JTextField quantity = new JTextField("0"); 
                quantity.setBounds(980,20,100,100);
                quantity.setFont(new Font("My Boli",Font.PLAIN,40));
                quantity.setHorizontalAlignment(JTextField.CENTER);
                
                items.add(new ItemData(values[1], quantity,vendorID));

                subban.add(price);
                subban.add(label);
                subban.add(quan);
                subban.add(quantity);

                // Add sub-panel to the scrollable panel
                scrollp.add(subban);
                
                frame.revalidate();
                frame.repaint();

                height += 200; // Increment the height for the next panel
            }
            
            
            //generate new orderID
            
            
        }
        
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

        br.close();
        fr.close();      
        
        }catch(IOException e){
            System.out.println("error");
        }
        
        
        //generate orderID

        if (customer.orderID == null){
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
            
            int newNum = highestNum + 1;
            customer.orderID = "O" + newNum;
            System.out.println("new order ID:" + customer.orderID);
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
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

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
        jLabel1.setText("Menu");
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

        jButton3.setBackground(new java.awt.Color(209, 232, 238));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton3.setText("Save order");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(209, 232, 238));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jButton4.setText("Checkout");
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        test2 panel = new test2(frame);   //the panel you want to switch to
//        frame.remove(this);
//        frame.add(panel);
//        frame.revalidate();
//        frame.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    frame.getContentPane().removeAll();
    selectVendor panel = new selectVendor(frame);   //the panel you want to switch to
    frame.add(panel);
    frame.revalidate();
    frame.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String order;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        
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
            


//            FileReader fr = new FileReader("Order.txt");
//            BufferedReader br  = new BufferedReader(fr);

            
            //loop through each item
            for (ItemData item : items) {
                String itemName = item.getFoodName();
                String quantity = item.getQuanField().getText();
                String orderID = item.getVendorID();
                //order line
                order = userID + "," + customer.orderID + "," + itemName + "," + quantity + "," + "nothing" + "," + date + "," + "0" + "," + orderID;
                

                // Loop through existing orders to check if the record already exists
                boolean exists = false;
                for (int i=0; i < table.size(); i++) {
                    String record = table.get(i);
                    String recor[] = record.split(",");
                    if (record != null && record.equals(order)) { // if got record then do nothing
                        System.out.println("Record exists: " + record);
                        exists = true;
                        break;
                    }
                    else if (record != null && recor[0].equals(userID) && recor[1].equals(customer.orderID) && recor[2].equals(itemName)) {   //if record different, then overwrite it
                        table.set(i,order);
                        exists = true;
                        break;
                    }
                }
                
                if (!exists){
                    table.add(order);
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


                


            
            
            JOptionPane.showMessageDialog(this, "Quantities saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving quantities.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        frame.getContentPane().removeAll();
        orderConfirm panel = new orderConfirm(frame,customer.orderID, false);   //the panel you want to switch to
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private static class ItemData {
        private String foodName;
        private JTextField quanField;
        private String vendorID;
        public ItemData(String foodName, JTextField quanField, String vendorID) {
            this.foodName = foodName;
            this.quanField = quanField;
            this.vendorID = vendorID;
        }
        
        public String getFoodName() {
            return foodName;
        }

        public JTextField getQuanField() {
            return quanField;
        }
        
        public String getVendorID() {
            return vendorID;
        }
    }
}
