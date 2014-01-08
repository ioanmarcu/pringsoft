 package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.User;
import com.util.DBUtil;

public class UserDao {


    public void addUser(User user) {
      Connection conn=null;
      try {
           
            conn = DBUtil.getConnection();
            PreparedStatement pStmt = conn
                    .prepareStatement("insert into users(firstname,lastname,dob,email) values (?, ?, ?, ? )");

            pStmt.setString(1, user.getFirstName());
            pStmt.setString(2, user.getLastName());
            pStmt.setString(3,user.getDob());
            pStmt.setString(4, user.getEmail());
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn);
        }
    }

    public void deleteUser(int userId) {
      Connection conn = null;
      try {
            conn = DBUtil.getConnection();
            PreparedStatement pStmt = conn.prepareStatement("delete from users where userid=?");
           
            pStmt.setInt(1, userId);
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn);
        }
    }

    public void updateUser(User user) {
      Connection conn = null;
      try {
            conn = DBUtil.getConnection();
            PreparedStatement pStmt = conn.prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?" +
                            "where userid=?");

            pStmt.setString(1, user.getFirstName());
            pStmt.setString(2, user.getLastName());
            pStmt.setString(3, user.getDob());
            pStmt.setString(4, user.getEmail());
            pStmt.setInt(5, user.getUserid());
            pStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn);
        }
    }

    public List<User> getAllUsers() {
      Connection conn = null;
        List<User> users = new ArrayList<User>();
        try {
            conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rSet = stmt.executeQuery("select * from users");
            while (rSet.next()) {
                User user = new User();
                user.setUserid(rSet.getInt("userid"));
                user.setFirstName(rSet.getString("firstname"));
                user.setLastName(rSet.getString("lastname"));
                user.setDob(rSet.getString("dob"));
                user.setEmail(rSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn);
        }

        return users;
    }

    public User getUserById(int userId) {
      Connection conn = null;
        User user = new User();
        try {
            conn = DBUtil.getConnection();
            PreparedStatement pStmt = conn.prepareStatement("select * from users where userid=?");
            pStmt.setInt(1, userId);
            ResultSet rSet = pStmt.executeQuery();

            if (rSet.next()) {
                user.setUserid(rSet.getInt("userid"));
                user.setFirstName(rSet.getString("firstname"));
                user.setLastName(rSet.getString("lastname"));
                user.setDob(rSet.getString("dob"));
                user.setEmail(rSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn);
        }

        return user;
    }
}