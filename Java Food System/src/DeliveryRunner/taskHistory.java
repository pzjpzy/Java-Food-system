/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DeliveryRunner;


import Customer.customer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
public class taskHistory extends javax.swing.JPanel {


    JFrame frame;
    String date;
    String userID = runner.userID;
    String cusIDtem = null;
    String username;
    String food;
    public taskHistory(JFrame frame) {
        initComponents();
        setBounds(0, 0, 1536, 864); // This line must exist in every JPanel
        this.frame = frame;
        frame.setLayout(null);

        // Create a panel for the scrollable content
        JPanel scrollp = new JPanel();
        scrollp.setLayout(new BoxLayout(scrollp, BoxLayout.Y_AXIS));

        try {
            FileReader fr = new FileReader("Task.txt");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(":");
                System.out.println(values[0]);
                
                if (values[1].trim().equals(userID) && values[4].equals("2") || values[4].equals("3")){
                    // Create a sub-panel for each vendor
                    JPanel subban = new JPanel();
                    subban.setLayout(null);
                    subban.setBackground(new Color(162,148,249));
                    subban.setPreferredSize(new Dimension(1100, 140)); // Set preferred size
                    
                    //find customer ID and date
                    FileReader fw2 = new FileReader("Order.txt");
                    BufferedReader bw2 = new BufferedReader(fw2);

                    String line2;
                    while ((line2 = bw2.readLine()) != null) {
                        String[] order = line2.split(",");


                        if (order[1].equals(values[2])){
                            date = order[5];
                            cusIDtem = order[0];
                            food = food + order[2] + ":" + order[3] + "   ";

                            
                        }
                    }
                    fw2.close();
                    bw2.close();
                    
                    //find username
                    if (cusIDtem != null){
                        //find customer name and date
                        FileReader fw3 = new FileReader("users.txt");
                        BufferedReader bw3 = new BufferedReader(fw3);

                        String line3;
                        while ((line3 = bw3.readLine()) != null) {
                            String[] user = line3.split(",");
                            
                            if (user[3].trim().equals(cusIDtem)){
                                System.out.println("found user");
                                username = user[0].trim();
                            }
                        }
                        fw2.close();
                        bw2.close();
                    }

                    // customer name label
                    JLabel label = new JLabel(username);
                    label.setFont(new Font("Arial", Font.BOLD, 45));
                    label.setBounds(50, 20, 600, 35);
                    subban.add(label);
                    
                    // address label
                    JLabel label2 = new JLabel(values[3]);
                    label2.setFont(new Font("Arial", Font.BOLD, 25));
                    label2.setBounds(50, 40, 650, 100);
                    subban.add(label2);
                    
                    // food label
                    food = food.replace("null", "");
                    JLabel label4 = new JLabel(food);
                    label4.setFont(new Font("Arial", Font.BOLD, 25));
                    label4.setBounds(700, 40, 400, 100);
                    subban.add(label4);
                    
                    // date label
                    JLabel label3 = new JLabel(date);
                    label3.setFont(new Font("Arial", Font.BOLD, 35));
                    label3.setBounds(850, 20, 400, 50);
                    subban.add(label3);


                    // Add sub-panel to the scrollable panel
                    scrollp.add(subban);
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
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            // Add the JScrollPane to the frame
            frame.add(scrollPane);
            frame.revalidate();
            frame.repaint();
        }catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error viewing tasks.", "Error", JOptionPane.ERROR_MESSAGE);
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

        setBackground(new java.awt.Color(245, 239, 255));
        setMinimumSize(new java.awt.Dimension(1552, 837));
        setPreferredSize(new java.awt.Dimension(1552, 837));

        jPanel1.setBackground(new java.awt.Color(162, 148, 249));

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
        jLabel1.setText("Task history");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 369, Short.MAX_VALUE)
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

        jButton2.setBackground(new java.awt.Color(162, 148, 249));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 581, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
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
        mainPage panel = new mainPage(frame);   //the panel you want to switch to
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
