/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
   
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      String dirrec="";
      response.setContentType("text/html");
      HttpSession sesion = request.getSession();
      String titulo = null;
      conexion con = null;
      String nombre = request.getParameter("name");
      String pass = request.getParameter("password");
      sesion.setAttribute("name", nombre);
      sesion.setAttribute("pass", pass);
      String asd="mal"; 
        try {
            //Conexion a la base de datos, utiliza la clase conexion
            con = new conexion();
            con.conecta();
            ResultSet res = con.query("select * from usuario where nombre='" + nombre + "' and contra='" + pass + "';");

            if (res.next()) {
               asd="good";
                if(res.getString("rango").equals("Administrador"))
                {
                    dirrec="<script>window.location.href='adminpage.html';</script>";                 
                }    
                else
                {
                    dirrec="<script>window.location.href='userpage.html';</script>";     
                }
                sesion.setAttribute("rango",res.getString("rango"));
            }else{
                dirrec="<script>window.location.href='index.html';alert('No esta registrado');</script>";   
            }

            con.cierra();
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = response.getWriter();
        out.println("<script>"+dirrec+"</script>");
        out.println(asd+sesion.getAttribute("rango"));
    }

}
