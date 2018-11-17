/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DBMS.www.Assignment3.Servlets.Controllers;

import com.DBMS.www.Assignment3.SupportingClasses.DBAO;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author h p
 */
public class Failures extends HttpServlet {

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
        try (PrintWriter out = response.getWriter())
        {        
            
            String query="select name from student, takes where takes.ID=student.ID and grade='F' having count(takes.ID)>1";
            
            DBAO conObj=new DBAO();            
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                ArrayList<String> resultList;
                PreparedStatement ps=con.prepareStatement(query);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    resultList = new ArrayList<>();
                    
                    resultList.add("Found Failures");
                    
                    while(rs.next())
                    {
                            
                            resultList.add(rs.getString(1));

                    }
                
                }
                else{
                    resultList = new ArrayList<>();
                    resultList.add("No Failures");
                    
                    
                }
                
                request.setAttribute("Result",resultList);
                request.getRequestDispatcher("FailureResult.jsp").forward(request, response);
            }
            else
            {
                out.println("Connection Failed\n");
            }
            try
            {
                if (con != null)
                {
                    con.close();
                    con = null;
                }
            }
            catch (SQLException sqle) 
            {
                System.out.println("SQL Exception thrown: " + sqle);
            }
        }
        catch(SQLException e)
        {
            out.println("Exception\n");
        }
            out.close();
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
