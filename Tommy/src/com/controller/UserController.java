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
import com.model.Event;
import com.dao.UserDao;
import com.dao.EventDao;
@WebServlet("/UserController")
public class UserController extends HttpServlet {
		
       private static final long serialVersionUID = 1L;
          private UserDao dao;
          private EventDao dao1;

          public UserController() {
              super();
              dao = new UserDao();
              dao1=new EventDao();
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
              //////////////////////////////////////
              if (action.equalsIgnoreCase("delete")){
                  int Ev_id = Integer.parseInt(request.getParameter("Ev_id"));
                  dao1.deleteEvent(Ev_id);
                  request.setAttribute("evenimente", dao1.getAllEvents());   
              } else if (action.equalsIgnoreCase("edit")){
                  int Ev_id = Integer.parseInt(request.getParameter("Ev_id"));
                  Event event = dao1.getEventById(Ev_id);
                  request.setAttribute("event", event);
              } else if (action.equalsIgnoreCase("listEvent")){
            	  response.setContentType("text/html");
            	  PrintWriter out = response.getWriter();
            	  out.print(dao1.getAllEvents());
                  request.setAttribute("event",dao1.getAllEvents());
              }
                else if (action.equalsIgnoreCase("addEvent")){
                	Event e = new Event();
                	e.setEv_id(5);
                	e.setNume("Eveniment");
                	e.setData("multe date");
                	e.setOra("18:00");
                	e.setLocatie("Acolo");
                	e.setComentarii("pentru ca eveniment");                	
                	dao1.addEvent(e);
                	request.setAttribute("event",dao1.getAllEvents());
                	                	                                 
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
                  //////////////////////////////////////////////
                  Event event = new Event();
                  event.setNume(request.getParameter("Nume"));
                  event.setOra(request.getParameter("Ora"));
                  event.setData(request.getParameter("Data"));
                  event.setLocatie(request.getParameter("Locatie"));
                  event.setComentarii(request.getParameter("Comentarii"));
                  String Ev_id = request.getParameter("Ev_id");
                  if(Ev_id == null || Ev_id.isEmpty())
                  {
                        dao1.addEvent(event);
                  }
                  else
                  {
                        event.setEv_id(Integer.parseInt(Ev_id));
                        dao1.updateEvent(event);
                  }
                  request.setAttribute("evenimente", dao1.getAllEvents());
     
      }

}