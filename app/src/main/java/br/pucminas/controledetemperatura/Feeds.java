package br.pucminas.controledetemperatura;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Felipe on 06/10/2017.
 */

public class Feeds {

    private int entry_id;
    private Date created_at;
    private String field1;
    //private int field2;
    //private String field3;

    public int getEntry_id() {
        return entry_id;
    }

    public Feeds(){

    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String toString() {

        String dataFormatada = "";

        if(created_at != null){
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dataFormatada = formatoData.format(created_at);
        }

        if (field1.contains("e")){
            int indiceChar = field1.indexOf("e");
            String temperatura = field1.substring(0,indiceChar);
            String umidade = field1.substring(indiceChar + 1,field1.length());

            return "Temp: " + temperatura + " | Umi: " + umidade + " | Data: " + dataFormatada;
        }else{

            return "Temp e Umi: " + field1 + " | Data: " + dataFormatada;

        }

    }
}
