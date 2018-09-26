
package servlets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Fernando
 */
public class conexion {
    
    Connection c=null;
    Statement s;  
    public conexion(){
    }
    public void conecta(){
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        c = DriverManager.getConnection("jdbc:mysql://localhost/usuarios","root","n0m3l0");
        
        }catch(Exception e){
            System.out.print("Error");
        }
    }
    public void cierra() throws SQLException{
        c.close();
    }
    public int update(String update) throws SQLException{
        s = c.createStatement();
        return s.executeUpdate(update);
    }
    public ResultSet query(String query) throws SQLException{
        s = c.createStatement();
        return s.executeQuery(query);
    }
}