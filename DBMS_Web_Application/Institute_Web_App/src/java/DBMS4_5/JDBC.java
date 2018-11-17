package DBMS4_5;

import com.DBMS.www.Assignment3.SupportingClasses.DBAO;
import java.sql.*;

public class JDBC {
    // JDBC driver name and database URL
   

    public static void main(String[] args) throws SQLException {
        
        
        DBAO conObj=new DBAO();            
        Connection conn=conObj.connectToDataBase();
        Statement stmt = null;
        
        String dummy = new String();
        int i=0;
        int len = args.length;

        while(i<len){
            dummy += args[i];
            dummy+= " ";
            System.out.println(dummy);
            i++;
        }

       
       
       
        try{
          
           
           stmt = conn.createStatement();
           String sql;
           sql = dummy;
           // question 2 & question 3
            if(args[0].equalsIgnoreCase("SELECT")){

               try (ResultSet rs = stmt.executeQuery(sql)) {
                   ResultSetMetaData rsmd = rs.getMetaData();
                   int columnsNumber = rsmd.getColumnCount();
                   
                   // Iterate through the data in the result set and display it.
                   
                   while (rs.next()) {
                       //Print one row
                       for(int j = 1 ; j <= columnsNumber; j++){
                           System.out.print(rs.getString(j) + " "); //Print one element of a row
                       }
                       System.out.println();//Move to the next line to print the next row.
                   }
               }
            }
            // question 1
            else if(args[0].equalsIgnoreCase("INSERT")){
                int xyz = stmt.executeUpdate(sql);
            }

            else if(args[0].equalsIgnoreCase("UPDATE")){
                int xyz = stmt.executeUpdate(sql);
            }
            
            else if(args[0].equalsIgnoreCase("delete")){
                int xyz = stmt.executeUpdate(sql);
            }
            
           //STEP 6: Clean-up environment
           stmt.close();
           conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC

        }catch(Exception e){
            //Handle errors for Class.forName

        }finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se2){
           }// nothing we can do
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
           }
        }
        System.out.println("Goodbye!");
    }
}