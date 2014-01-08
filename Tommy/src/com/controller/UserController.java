 package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.User;
import com.dao.UserDao;
@WebServlet("/UserController")
public class UserController extends HttpServlet {
		
       private static final long serialVersionUID = 1L;
          private UserDao dao;

          public UserController() {
              super();
              dao = new UserDao();
          }

      public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             String forward="";
              String action = request.getParameter("action");

              if (action.equalsIgnoreCase("delete")){
                  int userId = Integer.parseInt(request.getParameter("userId"));
                  dao.deleteUser(userId);
                  request.setAttribute("users", dao.getAllUsers());   
              } else if (action.equalsIgnoreCase("edit")){
                  int userId = Integer.parseInt(request.getParameter("userId"));
                  User user = dao.getUserById(userId);
                  request.setAttribute("user", user);
              } else if (action.equalsIgnoreCase("listUser")){
            	  response.setContentType("text/html");
            	  PrintWriter out = response.getWriter();
            	  out.print(dao.getAllUsers());
                  request.setAttribute("users",dao.getAllUsers());
              } 
      }

      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                  User user = new User();
                  user.setFirstName(request.getParameter("firstName"));
                  user.setLastName(request.getParameter("lastName"));
                  user.setDob(request.getParameter("dob"));
                  user.setEmail(request.getParameter("email"));
                  String userid = request.getParameter("userid");
                  if(userid == null || userid.isEmpty())
                  {
                        dao.addUser(user);
                  }
                  else
                  {
                        user.setUserid(Integer.parseInt(userid));
                        dao.updateUser(user);
                  }
                  request.setAttribute("users", dao.getAllUsers());
     
      }

}