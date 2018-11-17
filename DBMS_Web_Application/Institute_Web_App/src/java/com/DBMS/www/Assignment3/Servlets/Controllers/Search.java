/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DBMS.www.Assignment3.Servlets.Controllers;

import com.DBMS.www.Assignment3.SupportingClasses.DBAO;
import com.DBMS.www.Assignment3.SupportingClasses.StudentRecord;
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
public class Search extends HttpServlet {

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
            String rollNumber=request.getParameter("roll_no");
            String query="select name, student.dept_name,  takes.course_id,  title, credits, grade from student, takes, course where student.ID=takes.ID and takes.course_id=course.course_id and student.ID=?";
            
            DBAO conObj=new DBAO();            
            Connection con=conObj.connectToDataBase();
            if(con!=null)
            {
                PreparedStatement ps=con.prepareStatement(query);
                ps.setString(1,rollNumber);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    ArrayList<StudentRecord> ResultList=new ArrayList<>();
                    StudentRecord tempRec=new StudentRecord();
                    tempRec.setName(rs.getString(1));
                    tempRec.setDepartment(rs.getString(2));
                    tempRec.setCoursesRegisteredInfo(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)); 
                    while(rs.next()){                     
                        tempRec.setCoursesRegisteredInfo(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));                      
                    }
                    ResultList.add(tempRec);
                    request.setAttribute("Result",ResultList);
                    request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
                }       
                else
                {
                    out.println("String Not Executed\n");
                    
                }
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
