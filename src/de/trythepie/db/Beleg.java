package de.trythepie.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Beleg {

    int belegnr;
    Date kontrolldatum;
    Date lieferdatum;
    String hinweis;
    String bf_blv_feld_1;
    String bf_blv_feld_2;
    Date bf_blv_feld_3;
    String bf_blv_feld_4;
    String bf_blv_feld_5;
    String bf_blv_feld_6;
    String bf_blv_feld_7;
    String bf_blv_feld_8;
    String bf_blv_feld_9;
    String bf_blv_feld_10;
    String bf_blv_feld_11;

    public Beleg(   int belegnr,
                    Date kontrolldatum,
                    Date lieferdatum,
                    String hinweis,
                    String bf_blv_feld_1,
                    String bf_blv_feld_2,
                    Date bf_blv_feld_3,
                    String bf_blv_feld_4,
                    String bf_blv_feld_5,
                    String bf_blv_feld_6,
                    String bf_blv_feld_7,
                    String bf_blv_feld_8,
                    String bf_blv_feld_9,
                    String bf_blv_feld_10,
                    String bf_blv_feld_11) throws SQLException {
        this.belegnr=belegnr;
        this.kontrolldatum=kontrolldatum;
        this.lieferdatum=lieferdatum;
        this.hinweis=hinweis;
        this.bf_blv_feld_1=bf_blv_feld_1;
        this.bf_blv_feld_2=bf_blv_feld_2;
        this.bf_blv_feld_3=bf_blv_feld_3;
        this.bf_blv_feld_4=bf_blv_feld_4;
        this.bf_blv_feld_5=bf_blv_feld_5;
        this.bf_blv_feld_6=bf_blv_feld_6;
        this.bf_blv_feld_7=bf_blv_feld_7;
        this.bf_blv_feld_8=bf_blv_feld_8;
        this.bf_blv_feld_9=bf_blv_feld_9;
        this.bf_blv_feld_10=bf_blv_feld_10;
        this.bf_blv_feld_11=bf_blv_feld_11;
    }

    public void outputKonsole(){
        //Display values
        System.out.print("ID: "+belegnr);
        System.out.print(", Kontrolldatum: "+kontrolldatum+"\n\n\n");
    }

    public int returnBelegnr(){
        return belegnr;
    }

    public Date returnKontrolldatum(){
        return kontrolldatum;
    }

    public Date returnLieferdatum(){
        return lieferdatum;
    }

    public String returnHinweis(){
        return hinweis;
    }

    public String return_bf_blv_feld_1(){
        return bf_blv_feld_1;
    }

    public String return_bf_blv_feld_2(){
        return bf_blv_feld_2;
    }


    public Date return_bf_blv_feld_3(){
        return bf_blv_feld_3;
    }


    public String return_bf_blv_feld_4(){
        return bf_blv_feld_4;
    }


    public String return_bf_blv_feld_5(){
        return bf_blv_feld_5;
    }


    public String return_bf_blv_feld_6(){
        return bf_blv_feld_6;
    }


    public String return_bf_blv_feld_7(){
        return bf_blv_feld_7;
    }


    public String return_bf_blv_feld_8(){
        return bf_blv_feld_8;
    }



    public String return_bf_blv_feld_9(){
        return bf_blv_feld_9;
    }



    public String return_bf_blv_feld_10(){
        return bf_blv_feld_10;
    }



    public String return_bf_blv_feld_11(){
        return bf_blv_feld_11;
    }

}
