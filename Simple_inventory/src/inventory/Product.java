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
public class Product extends javax.swing.JFrame {
 int x,y;
 PreparedStatement pst=null;
 ResultSet rs=null;
 Connection con;
 //variable for storing the data
 String id,name,code,category,categoryId;
    /**
     * Creates new form Customer
     */
    public Product() {
        initComponents();
        populateComboBox();
        autoId();
        loadTable();
    }

    
   
   //For getting the category ID 
    String getCategoryId(String category){
       
       try{
            con=database_connect.connect();
            String sq="SELECT * FROM `product_category` WHERE `Name`='"+category+"'";
            pst=(PreparedStatement) con.prepareStatement(sq);
            
            rs=pst.executeQuery();
            if(rs.next()){
            category= rs.getString("id");
        
            }
           
                  rs.close();
                  pst.close();
                  con.close();
            }
            catch(Exception ex){
                
            }
       return category;
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
        
        if(!c_code.getText().trim().equals("")){
            code=c_code.getText();
           
                check=true;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Code field is empty");
            check=false;
        }
        
         if(c_category.getSelectedIndex()!=0){
            category=c_category.getSelectedItem().toString();
            categoryId=getCategoryId(category);
          
            check=true;     
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please Select the category");
            check=false;
        }
         
        return check;
    
    }
    
    //clearing the textfield
    public void clear(){
   
        c_name.setText("");
        c_code.setText("");
        c_category.setSelectedIndex(0);
        autoId();
    }
    
    //loading the data in a table
    public void loadTable(){
        try{
            con=database_connect.connect();
            String sq="SELECT * FROM `product`";
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
          String sql="Select id from product Order By id DESC LIMIT 1";
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
              c_Id.setText("PRO1000");
          }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    
    //Populate the combobox with data
        public void populateComboBox(){
        try{
        con=database_connect.connect();
        String sq="Select *From product_category";
        pst=(PreparedStatement) con.prepareStatement(sq);
        rs=pst.executeQuery();
        while(rs.next()){
            c_category.addItem(rs.getString("name"));
        }
              rs.close();
              pst.close();
              con.close();
        }catch(Exception ex){
            
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
        c_Id = new javax.swing.JTextField();
        c_name = new javax.swing.JTextField();
        c_code = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        itmTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        c_category = new javax.swing.JComboBox<>();

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

        jLabel4.setText("Barcode");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, 20));

        jLabel5.setText("Category");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 20));

        c_Id.setEditable(false);
        jPanel1.add(c_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 220, -1));
        jPanel1.add(c_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 220, -1));
        jPanel1.add(c_code, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 220, -1));

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
        jButton1.setText("Add");
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

        c_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the Category" }));
        jPanel1.add(c_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 220, -1));

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

    
    //For adding the product
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(getData()){
        
           try{
        
       
            con=database_connect.connect();
            String sql="INSERT INTO `product`(`id`, `Barcode`, `Name`, `Category_id`)VALUES (?,?,?,?)";
            pst=(PreparedStatement) con.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2,code);
            pst.setString(3,name);
            pst.setString(4,categoryId);
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

    
    //Updating the product
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(getData()){
       int result = JOptionPane.showConfirmDialog(rootPane,"Sure? You want to Update?", "Alert!!",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                 try{ 
         
                     con=database_connect.connect();
                     String squpdate="UPDATE `product` SET `Barcode`=?,`name`=?,`category_id`=? WHERE `id`='"+c_Id.getText()+"'";
                     pst=(PreparedStatement) con.prepareStatement(squpdate);
                     pst.setString(1,code);
                     pst.setString(2,name);
                     pst.setString(3,categoryId);
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

    
    //Deleting the product
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       int result = JOptionPane.showConfirmDialog(rootPane,"Sure? You want to delete?", "Alert!!",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                try{
                    con=database_connect.connect();
                    String delsql="Delete From product WHERE `id`='"+c_Id.getText()+"'";
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

    
    //for getting the item from table
    private void itmTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itmTableMouseClicked
       String item;
            DefaultTableModel tModel=(DefaultTableModel) itmTable.getModel();
            int selectRowIndex=itmTable.getSelectedRow();
            item=(tModel.getValueAt(selectRowIndex,0).toString());
            try{
                con=database_connect.connect();
                 String sq="Select * From product where id='"+item+"'";
                 pst=(PreparedStatement) con.prepareStatement(sq);
                 rs=pst.executeQuery();
                 if(rs.next()){
                     c_Id.setText(rs.getString("id"));
                     c_name.setText(rs.getString("name"));
                     c_code.setText(rs.getString("Barcode"));
                  
                    
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
           
            String sq="Select * From product where name LIKE'%"+searchItem+"%' ";
             
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
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField c_Id;
    private javax.swing.JComboBox<String> c_category;
    private javax.swing.JTextField c_code;
    private javax.swing.JTextField c_name;
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
