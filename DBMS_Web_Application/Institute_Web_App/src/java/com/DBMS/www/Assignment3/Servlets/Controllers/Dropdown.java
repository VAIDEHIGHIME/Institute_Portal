/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DBMS.www.Assignment3.Servlets.Controllers;

import com.DBMS.www.Assignment3.SupportingClasses.DBAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Txmaniac
 */
public class Dropdown extends HttpServlet {
    
    public void createDropDown(ResultSet rs,HttpServletRequest request,HttpServletResponse response) throws IOException{
        ResultSetMetaData rsmd = null;
        try {
            rsmd = rs.getMetaData();
        }catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
         int columnsNumber = 0;                     
        try {
            columnsNumber = rsmd.getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
        try(PrintWriter out = response.getWriter()) {
           /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Dropdown</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>createDropDown(rs) using ResultSet rs</h1><br>");
            out.println("<select>");
            
            while (rs.next()) {
                //Print one row
                for(int i = 1 ; i <= columnsNumber; i++){
                    out.println("<option value="+rs.getString(i)+">"+rs.getString(i)+"</option>");
                }
                System.out.println();
            }
            out.println("</select>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createDropDown(String sql,Statement stmt,HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException{
        //Statement stmt = null;
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = null;
        try {
            rsmd = rs.getMetaData();
        }catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
         int columnsNumber = 0;                     
        try {
            columnsNumber = rsmd.getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
        try(PrintWriter out = response.getWriter()) {
           /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Dropdown</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>createDropDown(sql) using Query string sql</h1><br>");
            out.println("<select>");
            while (rs.next()) {
                //Print one row
                for(int i = 1 ; i <= columnsNumber; i++){
                    out.println("<option value="+rs.getString(i)+">"+rs.getString(i)+"</option>");
                }
                System.out.println();
            }
            out.println("</select>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             response.setContentType("text/html");  
        String n=request.getParameter("query");
        String opt = request.getParameter("option");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }

        //STEP 3: Open a connection
        
        Statement stmt = null;
        DBAO conObj=new DBAO();            
        Connection conn=conObj.connectToDataBase();

        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = n;

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            if(opt.equalsIgnoreCase("rs")){
                createDropDown(rs,request, response);
            }
            if(opt.equalsIgnoreCase("str")){
                createDropDown(sql,stmt,request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dropdown.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           
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
//        processRequest(request, response);
        
       
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
