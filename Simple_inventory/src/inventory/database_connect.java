/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Bimal Kafle
 */
public class database_connect {
        public static Connection connect(){
        Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/simple_inventory","root","");
            
        }
        catch(Exception ex){
         
        }
        return conn;
    }
}
