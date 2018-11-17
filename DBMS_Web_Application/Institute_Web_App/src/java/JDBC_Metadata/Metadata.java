package JDBC_Metadata;

import com.DBMS.www.Assignment3.SupportingClasses.DBAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Metadata {
  

    public static void main(String[] args) {
    Connection conn = null;
  
    try{
        DBAO conObj=new DBAO();            
        conn=conObj.connectToDataBase();
 
      //STEP 4: Execute a query
        DatabaseMetaData metadata = conn.getMetaData();
        List<String> Tablelist = new ArrayList<>();
        int i=0;
        ResultSet resultSet = metadata.getTables(null,null, "%", null);
        while (resultSet.next()){
             Tablelist.add(resultSet.getString(3));
        }
        Statement stmt = conn.createStatement();
        String sql = null;
        try{
        for(int l1=0;l1<Tablelist.size();l1++){
            sql = null;
            sql="select * from " + Tablelist.get(l1);
            //System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            //System.out.println(sql);
            while(rs.next()){
                //System.out.println(sql);
                for(int l2=1;l2<=columnsNumber;l2++){
                    String s1,s2;
                    s1 = new String(rs.getString(l2).toLowerCase());
                    s2 = new String(args[0].toLowerCase());
                    if(s1.contains(s2)){
                        //System.out.println(sql);
                        for(int l3=1;l3<=columnsNumber;l3++){
                            System.out.print(rs.getString(l3) + " ");
                        }
                        System.out.println();
                    }
                }
            }
        }
        }catch(SQLException e){
            System.out.println("Program execution done completely");
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
       // nothing we can do
       try{
          if(conn!=null)
             conn.close();
       }catch(SQLException se){
       }
    }
    System.out.println("Goodbye!");
    }
}