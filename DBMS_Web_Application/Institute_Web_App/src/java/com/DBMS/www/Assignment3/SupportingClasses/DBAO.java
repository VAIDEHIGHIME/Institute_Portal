
package com.DBMS.www.Assignment3.SupportingClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBAO
{
    String url="jdbc:mysql://localhost:3306/dbms2";
    String username="root";
    String password="";
    
    
    public Connection connectToDataBase()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            if(con!=null)
            {
                return(con);
            }
            else
            {
                return(null);
            }
           
        }
        catch(ClassNotFoundException | SQLException e)
        {
           return(null);
        }     
    }
}
