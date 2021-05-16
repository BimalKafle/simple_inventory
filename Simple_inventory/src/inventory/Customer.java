/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bimal Kafle
 */
public class Customer extends javax.swing.JFrame {
 int x,y;
 PreparedStatement pst=null;
 ResultSet rs=null;
 Connection con;
 //variable for storing the data
 String id,name,email,phone;
    /**
     * Creates new form Customer
     */
    public Customer() {
        initComponents();
        autoId();
        loadTable();
    }

    
    //Email validation function
    boolean isValid(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
    
    
    
    
    //getting the form data and validatig
    private boolean getData(){
        boolean check=true;
        if(!c_Id.getText().trim().equals("")){
            id=c_Id.getText();
            check=true;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Id field is empty");
            check=false;
        }
        
        if(!c_name.getText().trim().equals("")){
            name=c_name.getText();
            check=true;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Name field is empty");
            check=false;
        }
        
        if(!c_email.getText().trim().equals("")){
            email=c_email.getText();
            if(isValid(email)){
                check=true;
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Invalid Email");
                check=false;
            }
            
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Email field is empty");
            check=false;
        }
        
         if(!c_phone.getText().trim().equals("")){
            phone=c_phone.getText();
            if(phone.length()==10){
                check=true;
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Invalid Phone");
                check=false;
            }
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Phone field is empty");
            check=false;
        }
         
        return check;
    
    }
    
    //clearing the textfield
    public void clear(){
   
        c_name.setText("");
        c_email.setText("");
        c_phone.setText("");
        autoId();
    }
    
    //loading the data in a table
    public void loadTable(){
        try{
            con=database_connect.connect();
            String sq="SELECT * FROM `pro_customer`";
            pst=(PreparedStatement) con.prepareStatement(sq);
            rs=pst.executeQuery();
            itmTable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
                  rs.close();
                  pst.close();
                  con.close();
        }catch(Exception ex){

        }
    }
    
    
    //Id generator function
        public void autoId(){
        try{
          con=database_connect.connect();
          String sql="Select id from pro_customer Order By id DESC LIMIT 1";
          pst=(PreparedStatement)con.prepareStatement(sql);
          rs=pst.executeQuery();
          if(rs.next()){
              String rnno=rs.getString("id");
              int co=rnno.length();
              String txt=rnno.substring(0,3);
              String num=rnno.substring(3,co);
              int n=Integer.parseInt(num);
              n++;
              String snum=Integer.toString(n);
              String ftxt=txt+snum;
              c_Id.setText(ftxt);
              rs.close();
              pst.close();
              con.close();
          }else{
              c_Id.setText("CUS1000");
          }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        c_phone = new javax.swing.JTextField();
        c_Id = new javax.swing.JTextField();
        c_name = new javax.swing.JTextField();
        c_email = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        itmTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_close_window_30px_1.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 700, 20));

        jLabel2.setText("Id");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 20, 20));

        jLabel3.setText("Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 20));

        jLabel4.setText("Email");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        jLabel5.setText("Phone");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 20));
        jPanel1.add(c_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 220, -1));

        c_Id.setEditable(false);
        jPanel1.add(c_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 220, -1));
        jPanel1.add(c_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 220, -1));
        jPanel1.add(c_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 220, -1));

        itmTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        itmTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itmTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(itmTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 370, 280));

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setText("Add Customer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 140, -1));

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 120, -1));

        jButton3.setBackground(new java.awt.Color(51, 153, 255));
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 140, -1));

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        jPanel1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 310, -1));

        jLabel6.setText("Search");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 70, 20));

        jButton4.setBackground(new java.awt.Color(51, 153, 255));
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //For adding the customer
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(getData()){
            try {
            con=database_connect.connect();
            
            String sql="INSERT INTO `pro_customer`(`id`, `name`, `email`, `phone`)VALUES (?,?,?,?)";
            pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2,name);
            pst.setString(3,email);
            pst.setString(4,phone);
            pst.executeUpdate();
            
            pst.close();
            con.close();
            clear();
            loadTable();
            JOptionPane.showMessageDialog(rootPane, "Data Added Successfully");
            } catch (SQLException ex) {
           JOptionPane.showMessageDialog(rootPane,"Error adding Data");
            }
            
            
            
            
            
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
            x=this.getX();
            y=this.getY();
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
              int xx=evt.getXOnScreen();
              int yy=evt.getYOnScreen();
              this.setLocation(xx-x,yy-y);
    }//GEN-LAST:event_jPanel2MouseDragged

    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    
   //for updating the data
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(getData()){
       int result = JOptionPane.showConfirmDialog(rootPane,"Sure? You want to Update?", "Alert!!",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                 try{ 
         
                     con=database_connect.connect();
                     String squpdate="UPDATE `pro_customer` SET `name`=?,`email`=?,`phone`=? WHERE `id`='"+c_Id.getText()+"'";
                     pst=(PreparedStatement) con.prepareStatement(squpdate);
                     pst.setString(1,name);
                     pst.setString(2,email);
                     pst.setString(3,phone);
                     pst.execute();
                     pst.close();
                     con.close();
                     clear();
                     loadTable();
                     }catch(Exception ex){
                        JOptionPane.showMessageDialog(rootPane,ex);
                     }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    
    //for deleting the data
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       int result = JOptionPane.showConfirmDialog(rootPane,"Sure? You want to delete?", "Alert!!",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                try{
                    con=database_connect.connect();
                    String delsql="Delete From pro_customer WHERE `id`='"+c_Id.getText()+"'";
                    pst=(PreparedStatement) con.prepareStatement(delsql);
                    pst.execute();
                    pst.close();
                    con.close();
                    clear();
                    loadTable();
              }catch(Exception ex){
                   JOptionPane.showMessageDialog(rootPane,"Unable to perform the action");
               }
            }
                
    }//GEN-LAST:event_jButton4ActionPerformed

    
    //for getting the table data
    private void itmTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itmTableMouseClicked
       String item;
            DefaultTableModel tModel=(DefaultTableModel) itmTable.getModel();
            int selectRowIndex=itmTable.getSelectedRow();
            item=(tModel.getValueAt(selectRowIndex,0).toString());
            try{
                con=database_connect.connect();
                 String sq="Select * From pro_customer where id='"+item+"'";
                 pst=(PreparedStatement) con.prepareStatement(sq);
                 rs=pst.executeQuery();
                 if(rs.next()){
                     c_Id.setText(rs.getString("id"));
                     c_name.setText(rs.getString("name"));
                     c_email.setText(rs.getString("email"));
                     c_phone.setText(String.valueOf(rs.getString("phone")));
                    
                    rs.close();
                    pst.close();
                     con.close();
                    
                 }
            }catch(Exception ex){
                
            }
    }//GEN-LAST:event_itmTableMouseClicked

    
    //for searching the item on a table
    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        String searchItem=search.getText();
       
        try{
            con=database_connect.connect();
           
            String sq="Select * From pro_customer where name LIKE'%"+searchItem+"%' ";
             
            pst=(PreparedStatement) con.prepareStatement(sq);
            
             
            rs=pst.executeQuery();
            itmTable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
             
            rs.close();
            pst.close();
            con.close();
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_searchKeyReleased

    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField c_Id;
    private javax.swing.JTextField c_email;
    private javax.swing.JTextField c_name;
    private javax.swing.JTextField c_phone;
    private javax.swing.JTable itmTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
