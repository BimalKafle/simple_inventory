/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bimal Kafle
 */
public class Purchase extends javax.swing.JFrame {
 int x,y;
 PreparedStatement pst=null;
 ResultSet rs=null;
 Connection con;
 String id,productId,supplierId,invoice,products,suppliers;
 int quantity;
 double unit,total;
 long millis=System.currentTimeMillis();  
 java.sql.Date date=new java.sql.Date(millis);
    /**
     * Creates new form Customer
     */
    public Purchase() {
        initComponents();
        autoId();
        loadTable();
        productComboBox();
        supplierComboBox();
        
    }

    
    
      //loading the data in a table
    public void loadTable(){
        try{
            con=database_connect.connect();
            String sq="SELECT * FROM `purchase`";
            pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sq);
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
          String sql="Select id from purchase Order By id DESC LIMIT 1";
          pst=(com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
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
              c_id.setText(ftxt);
              rs.close();
              pst.close();
              con.close();
          }else{
              c_id.setText("PUR1000");
          }
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
     //Populate the combobox with data
        public void productComboBox(){
        try{
        con=database_connect.connect();
        String sq="Select *From product";
        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sq);
        rs=pst.executeQuery();
        while(rs.next()){
            c_product.addItem(rs.getString("name"));
        }
              rs.close();
              pst.close();
              con.close();
        }catch(Exception ex){
            
        }
    }
        
        
     //Populate the combobox with data
        public void supplierComboBox(){
        try{
        con=database_connect.connect();
        String sq="Select *From pro_supplier";
        pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sq);
        rs=pst.executeQuery();
        while(rs.next()){
            c_supplier.addItem(rs.getString("name"));
        }
              rs.close();
              pst.close();
              con.close();
        }catch(Exception ex){
            
        }
    }   
    
        
      //For getting the product ID 
    String getProductId(String product){
       
       try{
            con=database_connect.connect();
            String sq="SELECT * FROM `product` WHERE `Name`='"+product+"'";
            pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sq);
            
            rs=pst.executeQuery();
            if(rs.next()){
            product= rs.getString("id");
        
            }
           
                  rs.close();
                  pst.close();
                  con.close();
            }
            catch(Exception ex){
                
            }
       return product;
    }
    

      //For getting the supplier ID 
    String getSupplierId(String supplier){
       
       try{
            con=database_connect.connect();
            String sq="SELECT * FROM `pro_supplier` WHERE `Name`='"+supplier+"'";
            pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sq);
            
            rs=pst.executeQuery();
            if(rs.next()){
            supplier= rs.getString("id");
        
            }
           
                  rs.close();
                  pst.close();
                  con.close();
            }
            catch(Exception ex){
                
            }
       return supplier;
    }
    
    
    
        //getting the form data and validatig
    private boolean getData(){
        boolean check=true;
        if(!c_id.getText().trim().equals("")){
            id=c_id.getText();
            check=true;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Id field is empty");
            check=false;
        }
        
        if(!c_invoice.getText().trim().equals("")){
            invoice=c_invoice.getText();
            check=true;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Name field is empty");
            check=false;
        }
        
        if(!c_qty.getText().trim().equals("")){
            quantity=Integer.parseInt(c_qty.getText());
           
                check=true;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Quantity is empty");
            check=false;
        }
        
        if(!c_unit.getText().trim().equals("")){
            unit=Double.parseDouble(c_unit.getText());
           
                check=true;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Unit field is empty");
            check=false;
        }
        if(!c_total.getText().trim().equals("")){
            total=Double.parseDouble(c_total.getText());
           
                check=true;
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Total field is empty");
            check=false;
        }
        
         if(c_product.getSelectedIndex()!=0){
            products=c_product.getSelectedItem().toString();
            productId=getProductId(products);
          
            check=true;     
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please Select the product");
            check=false;
        }
           if(c_supplier.getSelectedIndex()!=0){
            suppliers=c_supplier.getSelectedItem().toString();
            supplierId=getSupplierId(suppliers);
          
            check=true;     
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Please Select the supplier");
            check=false;
        }
        return check;
    
    }
    
    
      
    //clearing the textfield
    public void clear(){
   
        c_invoice.setText("");
        c_product.setSelectedIndex(0);
        c_supplier.setSelectedIndex(0);
        c_qty.setText("");
        c_unit.setText("");
        c_total.setText("");
        autoId();
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
        c_unit = new javax.swing.JTextField();
        c_id = new javax.swing.JTextField();
        c_qty = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        itmTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        c_invoice = new javax.swing.JTextField();
        c_product = new javax.swing.JComboBox<>();
        c_supplier = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        c_total = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

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
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 20, 20));

        jLabel3.setText("Invoice No.");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 20));

        jLabel4.setText("Product");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, 20));

        jLabel5.setText("Supplier");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 20));
        jPanel1.add(c_unit, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 190, -1));

        c_id.setEditable(false);
        jPanel1.add(c_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 190, -1));
        jPanel1.add(c_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 190, -1));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 370, 300));

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 140, -1));

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 120, -1));

        jButton3.setBackground(new java.awt.Color(51, 153, 255));
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 140, -1));

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
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 120, -1));
        jPanel1.add(c_invoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 190, -1));

        c_product.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the product" }));
        jPanel1.add(c_product, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 190, -1));

        c_supplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select the Supplier" }));
        jPanel1.add(c_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 190, -1));

        jLabel8.setText("Quantity");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jLabel9.setText("Unit");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, 20));
        jPanel1.add(c_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 190, -1));

        jLabel10.setText("Total");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

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

    
    //for adding a data
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(getData()){
        
           try{
        
       
            con=database_connect.connect();
            String sql="INSERT INTO `purchase`(`id`, `productId`, `supplierId`, `Date`, `Quantity`, `Unit Price`, `Total`, `Invoice Number`)VALUES (?,?,?,?,?,?,?,?)";
            pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2,productId);
            pst.setString(3,supplierId);
            pst.setDate(4,date);
            pst.setInt(5, quantity);
            pst.setDouble(6, unit);
            pst.setDouble(7, total);
            pst.setString(8,invoice);
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

    
    
    //for clearing the data
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    //for getting the data from table
    private void itmTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itmTableMouseClicked
            String item;
            DefaultTableModel tModel=(DefaultTableModel) itmTable.getModel();
            int selectRowIndex=itmTable.getSelectedRow();
            item=(tModel.getValueAt(selectRowIndex,0).toString());
            try{
                con=database_connect.connect();
                 String sq="Select * From purchase where id='"+item+"'";
                 pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(sq);
                 rs=pst.executeQuery();
                 if(rs.next()){
                     c_id.setText(rs.getString("id"));
                     c_invoice.setText(rs.getString("invoice number"));
                     c_qty.setText(String.valueOf(rs.getInt("Quantity")));
                     c_unit.setText(String.valueOf(rs.getDouble("Unit Price")));
                     c_total.setText(String.valueOf(rs.getDouble("Total")));
                    rs.close();
                    pst.close();
                     con.close();
                    
                 }
            }catch(Exception ex){
                
            }
    }//GEN-LAST:event_itmTableMouseClicked

    
    //for updating the data
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(getData()){
       int result = JOptionPane.showConfirmDialog(rootPane,"Sure? You want to Update?", "Alert!!",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                 try{ 
         
                     con=database_connect.connect();
                     String squpdate="UPDATE `purchase` SET `productId`=?,`supplierId`=?,`Quantity`=?,`Unit Price`=?,`Total`=?,`Invoice Number`=? WHERE `id`='"+c_id.getText()+"'";
                     pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(squpdate);
                     pst.setString(1,productId);
                     pst.setString(2,supplierId);
                     pst.setInt(3,quantity);
                     pst.setDouble(4,unit);
                     pst.setDouble(5,total);
                     pst.setString(6,invoice);
                     
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
                    String delsql="Delete From purchase WHERE `id`='"+c_id.getText()+"'";
                    pst=(com.mysql.jdbc.PreparedStatement) con.prepareStatement(delsql);
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

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
       String searchItem=search.getText();
       
        try{
            con=database_connect.connect();
           
            String sq="Select * From purchase where Invoice Number LIKE'%"+searchItem+"%' ";
             
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
            java.util.logging.Logger.getLogger(Purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Purchase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField c_id;
    private javax.swing.JTextField c_invoice;
    private javax.swing.JComboBox<String> c_product;
    private javax.swing.JTextField c_qty;
    private javax.swing.JComboBox<String> c_supplier;
    private javax.swing.JTextField c_total;
    private javax.swing.JTextField c_unit;
    private javax.swing.JTable itmTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
