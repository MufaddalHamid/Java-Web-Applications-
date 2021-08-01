/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.io.PrintWriter;
import java.sql.*;
 
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
import javax.servlet.annotation.MultipartConfig;
 @MultipartConfig
public class Upload extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con= null;
        String n = request.getParameter("name");
        Part part = request.getPart("image");
        String x=part.getSubmittedFileName();
        System.out.println(x);

       try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
        PreparedStatement ps = con.prepareStatement("insert into upload values(?,?)");
        InputStream is = part.getInputStream();

        ps.setString(1, n);
        ps.setBlob(2,is);

        int result = ps.executeUpdate(); 
       if(result>=0){
              out.println("Name - "+n);
            
              out.println("<br><br><img src="+x+"</img>");
               out.println("<br><br><font size=16 color='red'><b>Data added to database<b></font> ");

           }
       }catch(SQLException ee){ out.print(ee.getMessage());}
       catch(ClassNotFoundException e1){out.print(e1.getMessage());}

    }

}