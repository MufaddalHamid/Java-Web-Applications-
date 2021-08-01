/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class ticketprint extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       Connection conn = null;
        Statement stmt = null;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String exam_id = request.getParameter("n1");//exam id
            String prn = request.getParameter("n2");//prn
            Cookie ck[] = request.getCookies();
            String subjname=ck[0].getName();
            String name=ck[0].getValue();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");                 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Scholarship Details</h1>");
            out.println("<table border=3><th>College</th><th>Name</th>");
            out.println("<th>Major</th><th>Cgpa</th>");
            out.println("<tr><td>SICSR</td><td>Kabir</td><td> CS </td><td>8</td></tr>");
            out.println("</body></html>");

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");          
                stmt = conn.createStatement();     
                String sql = "INSERT INTO exam_ticket VALUES (?, ?,?,?)";

                PreparedStatement s = conn.prepareStatement(sql);          
                s.setString(1,prn);   
                s.setString(2,subjname); 
                s.setString(3,name); 
                s.setString(4,exam_id); 
                   

                int r = s.executeUpdate();
                if(r>=0)
                    out.print("<font size=18 color='green'><b> Data  Added successfully<b></font>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TotalbillServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TotalbillServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
