package com.app.my.app1;

public class ModelOceny {
   private String nazwa;
   private int ocena;

    public ModelOceny(String nazwa, int ocena) {
        this.nazwa = nazwa;
        this.ocena = ocena;
    }
    public ModelOceny(String nazwa) {
        this.nazwa = nazwa;
        this.ocena=0;
    }
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
