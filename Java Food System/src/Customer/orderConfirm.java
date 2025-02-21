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
import javax.swing.Timer;

/**
 *
 * @author pangz
 */
public class orderConfirm extends javax.swing.JPanel {


    JFrame frame;
    String userID = customer.userID;
    double total = 0;
    protected ArrayList<ItemData> items = new ArrayList<>();
    
    public orderConfirm(JFrame frame, String orderID, boolean reorder) {
        initComponents();
        setBounds(0,0,1536,864);     //this line must exist in every JPanel
        this.frame = frame;  
        frame.setLayout(null);
        
        jLabel3.setText("RM" + String.format("%.2f", total));
        // Create a panel for the scrollable content
        JPanel scrollp = new JPanel();
        scrollp.setLayout(new BoxLayout(scrollp, BoxLayout.Y_AXIS));
        try{
        FileReader fr = new FileReader("Order.txt");
        BufferedReader br = new BufferedReader(fr);
        //show all dishes
        String line = null;
        int height = 200;
        
        while((line = br.readLine())!= null){
 
            String values[] = line.split(",");
            
            if(values[1].equals(orderID)){
                // Create panel
                JPanel subban = new JPanel();
                subban.setLayout(null);
                subban.setBackground(new Color(92, 201, 205));
                subban.setPreferredSize(new Dimension(1100, 140)); // Set preferred size

                //items name
                JLabel label = new JLabel("Item: " + values[2]);
                label.setFont(new Font("Arial", Font.BOLD, 50));
                label.setBounds(50,40,600,70);
                
                //item price
                FileReader fr2 = new FileReader("Menu.txt");
                BufferedReader br2  = new BufferedReader(fr2);
                while((line = br2.readLine()) != null){
                    String menu[] = line.split(",");
                    if (menu[1].equals(values[2])){
                        JLabel price = new JLabel("RM" + menu[2]);
                        price.setFont(new Font("Arial", Font.BOLD, 30));
                        price.setBounds(550,40,200,70);
                        subban.add(price);
                    }
                }
                fr2.close();
                br2.close();
                
                
                //Quantity:
                JLabel quan = new JLabel("Quantity:");
                quan.setFont(new Font("Arial", Font.BOLD, 50));
                quan.setBounds(750,40,300,70);
                
                //quantity textfield
                JTextField quantity = new JTextField("0"); 
                quantity.setBounds(980,20,100,100);
                quantity.setFont(new Font("My Boli",Font.PLAIN,40));
                quantity.setHorizontalAlignment(JTextField.CENTER);
                quantity.setText(values[3]);
                
                items.add(new ItemData(values[2], quantity,values[7]));

                
                subban.add(label);
                subban.add(quan);
                subban.add(quantity);

                // Add sub-panel to the scrollable panel
                scrollp.add(subban);
                
                frame.revalidate();
                frame.repaint();

                height += 200; // Increment the height for the next panel
            }
            
            
        }
        // Set the preferred size for the scrollable panel
        scrollp.setPreferredSize(new Dimension(1100, scrollp.getComponentCount() * 160));

        // Create a JScrollPane with the scrollable panel as its viewport
        JScrollPane scrollPane = new JScrollPane(scrollp);
        scrollPane.setBounds(200, 150, 1100, 550); // Set bounds for JScrollPane
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
        
        // Add ActionListener to JComboBox
        jComboBox1.addActionListener(new ActionListener() {
            String previousOption = "";
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected item
                String selectedOption = (String) jComboBox1.getSelectedItem();
                System.out.println("You selected: " + selectedOption);
                // Trigger custom action
                if ("Delivery".equals(selectedOption)) {
                    JOptionPane.showMessageDialog(frame, "Will add RM10 as delivery charge");
                    total += 10;
                    jLabel3.setText("RM" + String.format("%.2f", total));
                } else {
                    if ("Delivery".equals(previousOption) && !"Delivery".equals(selectedOption)){
                        total -= 10;
                        jLabel3.setText("RM" + String.format("%.2f", total));
                    }
                }
                previousOption = selectedOption;
            }
        });
        
        //refresh total price
        String line = null;
        for (ItemData item : items) {
            String itemName = item.getFoodName();
            String quantity = item.getQuanField().getText();
            //total price counting
            try {
            FileReader fr2 = new FileReader("Menu.txt");
            BufferedReader br2  = new BufferedReader(fr2);
            while((line = br2.readLine()) != null){
                String menu[] = line.split(",");
                if (menu[1].equals(itemName)){
                    total += Double.parseDouble(menu[2]) * Double.parseDouble(quantity);
                    //update total
                    jLabel3.setText("RM" + String.format("%.2f", total));
                }
            }
            fr2.close();
            br2.close();
            }catch (IOException e) {
                System.out.println("Error in updating total");
            }
        }
        
        // Timer to check the total value every second
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (total > 10) {
                    jButton4.setEnabled(true);
                } else {
                    jButton4.setEnabled(false);
                }
            }
        });
        timer.start(); // Start the timer
        
        
        //reorder part
        if (reorder){
            try {
                int highestNum = 0;
                //store every line in array
                FileReader fr = new FileReader("Order.txt");
                BufferedReader br  = new BufferedReader(fr);

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
                int newNum = highestNum + 1;
                customer.orderID = "O" + newNum;
                System.out.println("new order ID:" + customer.orderID);
                } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving quantities.", "Error", JOptionPane.ERROR_MESSAGE);
            }

                
            }
    }
    
    // Method to simulate payment processing
    public static void processPayment(String userID, Double total) throws IOException {
        
        String line = null;
        String balanc;
        String balanced;
        FileReader fr4 = new FileReader("Balance.txt");
        BufferedReader br4  = new BufferedReader(fr4);
            ArrayList<String> table = new ArrayList<>();
            
            while((line = br4.readLine()) != null){
                table.add(line);
            }
            fr4.close();
            br4.close();
        for (int i=0; i < table.size(); i++) {
                String record = table.get(i);
                String balance[] = record.split(",");
                if (balance[0].equals(userID)){
                    if (total < Float.valueOf(balance[1])){
                        balanc = String.format("%.2f",Double.valueOf(balance[1]) - total);
                        //balance - total
                        balance[1] = balanc;
                        balanced = String.join(",", balance);
                        table.set(i,balanced);
                        
                    }                                 
                }
                else{
                    //do nothing
                }
        }
        
        fr4.close();
        br4.close();
        
        //write every line into file
            FileWriter fw4 = new FileWriter("Balance.txt");
            for (String record : table) {
                if (record != null){
                    fw4.append(record + "\n");
                }
            }
            fw4.close(); 

        throw new IOException("Payment failed, insufficient amount.");

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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

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
        jLabel1.setText("Order");
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dine in", "Take away", "Delivery"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel2.setText("Total:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel3.setText("RM here");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setText("Address:");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 942, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    user.logout(frame);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    frame.getContentPane().removeAll();
    selectVendor panel = new selectVendor(frame);   //the panel you want to switch to
    frame.add(panel);
    frame.revalidate();
    frame.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        total = 0;
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

            
            //loop through each item for file writing
            for (ItemData item : items) {
                String itemName = item.getFoodName();
                String quantity = item.getQuanField().getText();
                String orderID = item.getVendorID();
                //order line
                order = userID + "," + customer.orderID + "," + itemName + "," + quantity + "," + jComboBox1.getSelectedItem() + "," + date + "," + "0" + "," + orderID;
                

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
                
                //total price counting
                FileReader fr2 = new FileReader("Menu.txt");
                BufferedReader br2  = new BufferedReader(fr2);
                while((line = br2.readLine()) != null){
                    String menu[] = line.split(",");
                    if (menu[1].equals(itemName)){
                        total += Double.parseDouble(menu[2]) * Double.parseDouble(quantity);
                        jLabel3.setText("RM" + String.format("%.2f", total));
                    }
                }
                fr2.close();
                br2.close();
                
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
        
        //update total
        if (jComboBox1.getSelectedItem().equals("Delivery")){
            total += 10;
            jLabel3.setText("RM" + String.format("%.2f", total));
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String order;
        String balance = null;
        String task = "error";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        
        try {
            
            processPayment(userID,total);
            
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
            
            //loop through each item for file writing
            for (ItemData item : items) {
                String itemName = item.getFoodName();
                String quantity = item.getQuanField().getText();
                String orderID = item.getVendorID();
                //order line
                order = userID + "," + customer.orderID + "," + itemName + "," + quantity + "," + jComboBox1.getSelectedItem() + "," + date + "," + "1" + "," + orderID;
                

                // Loop through existing orders to check if the record already exists
                boolean exists = false;
                for (int i=0; i < table.size(); i++) {
                    String record = table.get(i);
                    String recor[] = record.split(",");
                    if (record != null && recor[0].equals(userID) && recor[1].equals(customer.orderID) && recor[2].equals(itemName)) {   //if record different, then overwrite it
                        table.set(i,order);
                        exists = true;
                        break;
                    }
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
            
            //if it's delivery, generate new task line
            if (jComboBox1.getSelectedItem().equals("Delivery")){
                //store every line in array
                FileReader fr3 = new FileReader("Task.txt");
                BufferedReader br3  = new BufferedReader(fr3);
                String line2 = null;
                ArrayList<String> table2 = new ArrayList<>();

                while((line2 = br3.readLine()) != null){
                    table.add(line2);
                }
                fr3.close();
                br3.close();

                //get new deliveryID
                int largestNum = 0;
                for (int i=0; i < table2.size(); i++) {
                    String record = table2.get(i);
                    String recor[] = record.split(":");

                    try{
                        largestNum = Integer.parseInt(recor[1].substring(1));
                    }catch (ArrayIndexOutOfBoundsException e){
                        largestNum = 0;
                    }
                    
                }
                
                System.out.println(largestNum);
                String newID = "D" + String.valueOf(largestNum + 1);
                
                
                
                //task line
                task = newID + ":" + "R1" + ":" + customer.orderID + ":" + jTextField1.getText() + ":" + "0";
                
                //write every line into file
                FileWriter fw2 = new FileWriter("Task.txt",true);
                fw2.append(task + "\n");
                fw2.close();
            }
            

            fw.close(); 
            
            JOptionPane.showMessageDialog(this, "Payment successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Payment failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
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
