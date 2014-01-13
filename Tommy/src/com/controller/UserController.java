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
import com.google.gson.Gson;
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
            	  System.out.println("listEvents");
            	  response.setContentType("text/html");
            	  PrintWriter out = response.getWriter();
            	  System.out.println("apel listEvent");
            	  Gson gson = new Gson();
            	  out.print("hiii"+gson.toJson(dao1.getAllEvents()));
            	  System.out.print("hiii2"+gson.toJson(dao1.getAllEvents()));
            	  out.flush();
            	  out.close();
                  request.setAttribute("event",gson.toJson(dao1.getAllEvents()));
              }
                else if (action.equalsIgnoreCase("addEvent")){
                	
                	Event e = new Event();
                	e.setNume(request.getParameter("nume"));
                	e.setData(request.getParameter("data"));
                	e.setOra(request.getParameter("ora"));
                	e.setLocatie(request.getParameter("locatie"));
                	e.setComentarii(request.getParameter("comentariu"));                	
                	dao1.addEvent(e);
                	request.setAttribute("event",dao1.getAllEvents());
                	                	                                 
              } 
      }

      public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//                  User user = new User();
//                  user.setFirstName(request.getParameter("firstName"));
//                  user.setLastName(request.getParameter("lastName"));
//                  user.setDob(request.getParameter("dob"));
//                  user.setEmail(request.getParameter("email"));
//                  String userid = request.getParameter("userid");
//                  if(userid == null || userid.isEmpty())
//                  {
//                        dao.addUser(user);
//                  }
//                  else
//                  {
//                        user.setUserid(Integer.parseInt(userid));
//                        dao.updateUser(user);
//                  }
                  request.setAttribute("users", dao.getAllUsers());
                  //////////////////////////////////////////////
                  String json = request.getParameter("json");
                  Gson gson = new Gson();
                  Event event = gson.fromJson(json, Event.class);
//                  event.setNume();
//                  event.setOra(request.getParameter("Ora"));
//                  event.setData(request.getParameter("Data"));
//                  event.setLocatie(request.getParameter("Locatie"));
//                  event.setComentarii(request.getParameter("Comentarii"));
//                  String Ev_id = request.getParameter("Ev_id");
                  if(event.getEv_id() == null)
                  {
                        dao1.addEvent(event);
                  }
                  else
                  {
//                        event.setEv_id(Integer.parseInt(Ev_id));
                        dao1.updateEvent(event);
                  }
                  request.setAttribute("evenimente", dao1.getAllEvents());
     
      }

}