 package com.util;

import java.sql.*;

public class DBUtil {

      private static Connection conn=null;
     
      public static Connection getConnection(){
     
            try{
                  String username = "Sima";
                  String passwd = "";
                 
                  Class.forName("com.mysql.jdbc.Driver");
           conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/schema1",username,passwd);
            }catch(Exception e){
                  e.printStackTrace();
            }
           
            return conn;
      }
     
      public static void closeConnection(Connection conn){
            try{
                  conn.close();
            }catch(Exception e){
                  e.getMessage();
            }
      }
}
