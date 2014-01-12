 package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.Event;
import com.util.DBUtil;

public class EventDao {


    public void addEvent(Event event) {
      Connection conn_ev=null;
      try {
           
            conn_ev = DBUtil.getConnection();
            PreparedStatement pStmt_ev = conn_ev
                    .prepareStatement("insert into evenimente(Ev_id,Nume,Ora,Data,Locatie,Comentarii) values (?, ?, ?, ?, ? , ?)");

            pStmt_ev.setInt(1, event.getEv_id());
            pStmt_ev.setString(2, event.getNume());
            pStmt_ev.setString(3,event.getOra());
            pStmt_ev.setString(4, event.getData());
            pStmt_ev.setString(5, event.getLocatie());
            pStmt_ev.setString(6, event.getComentarii());
            pStmt_ev.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn_ev);
        }
    }

    public void deleteEvent(int Ev_id) {
      Connection conn_ev = null;
      try {
            conn_ev = DBUtil.getConnection();
            PreparedStatement pStmt_ev = conn_ev.prepareStatement("delete from evenimente where Ev_id=?");
           
            pStmt_ev.setInt(1,Ev_id);
            pStmt_ev.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn_ev);
        }
    }

    public void updateEvent(Event event) {
      Connection conn_ev = null;
      try {
            conn_ev = DBUtil.getConnection();
            PreparedStatement pStmt_ev = conn_ev.prepareStatement("update evenimente set Nume=?, Ora=?, Data=?, Locatie=?,Comentarii=?" +
                            "where Ev_id=?");

            pStmt_ev.setString(1, event.getNume());
            pStmt_ev.setString(2, event.getOra());
            pStmt_ev.setString(3, event.getData());
            pStmt_ev.setString(4, event.getLocatie());
            pStmt_ev.setString(5, event.getComentarii());
            pStmt_ev.setInt(6, event.getEv_id());
            pStmt_ev.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn_ev);
        }
    }

    public List<Event> getAllEvents() {
      Connection conn_ev = null;
        List<Event> events = new ArrayList<Event>();
        try {
            conn_ev = DBUtil.getConnection();
            Statement stmt_ev = conn_ev.createStatement();
            ResultSet rSet_ev = stmt_ev.executeQuery("select * from evenimente");
            while (rSet_ev.next()) {
                Event event = new Event();
                event.setEv_id(rSet_ev.getInt("Ev_id"));
                event.setNume(rSet_ev.getString("Nume"));
                event.setOra(rSet_ev.getString("Ora"));
                event.setData(rSet_ev.getString("Data"));
                event.setLocatie(rSet_ev.getString("Locatie"));
                event.setComentarii(rSet_ev.getString("Comentarii"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn_ev);
        }

        return events;
    }

    public Event getEventById(int Ev_id) {
      Connection conn_ev = null;
        Event event = new Event();
        try {
            conn_ev = DBUtil.getConnection();
            PreparedStatement pStmt_ev = conn_ev.prepareStatement("select * from evenimente where Ev_id=?");
            pStmt_ev.setInt(1, Ev_id);
            ResultSet rSet_ev = pStmt_ev.executeQuery();

            if (rSet_ev.next()) {
                event.setEv_id(rSet_ev.getInt("Ev_id"));
                event.setNume(rSet_ev.getString("Nume"));
                event.setOra(rSet_ev.getString("Ora"));
                event.setData(rSet_ev.getString("Data"));
                event.setLocatie(rSet_ev.getString("Locatie"));
                event.setComentarii(rSet_ev.getString("Comentarii"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(conn_ev);
        }

        return event;
    }
}