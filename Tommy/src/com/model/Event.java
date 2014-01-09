 package com.model;

public class Event {

    private int Ev_id;
    private String Nume;
    private String Ora;
    private String Data;
    private String Locatie;
    private String Comentarii;
    
    public int getEv_id() {
        return Ev_id;
    }
    public void setEv_id(int Ev_id) {
        this.Ev_id = Ev_id;
    }
    public String getNume() {
        return Nume;
    }
    public void setNume(String Nume) {
        this.Nume = Nume;
    }
    public String getOra() {
        return Ora;
    }
    public void setOra(String Ora) {
        this.Ora = Ora;
    }
    public String getData() {
        return Data;
    }
    public void setData(String Data) {
        this.Data = Data;
    }
    public String getLocatie() {
        return Locatie;
    }
    public void setLocatie(String Locatie) {
        this.Locatie = Locatie;
    }
    public String getComentarii() {
        return Comentarii;
    }
    public void setComentarii(String Comentarii) {
        this.Comentarii = Comentarii;
    }
    @Override
    public String toString() {
        return "Eveniment=" + Ev_id + "," + Nume
                + "," + Ora + "," + Data + ","
                + Locatie + ","+Comentarii;
    }   
}