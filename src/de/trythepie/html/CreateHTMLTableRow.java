package de.trythepie.html;

import de.trythepie.db.Beleg;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.text.*;
public class CreateHTMLTableRow {
    public static String array2HTML(List<Beleg> belege) throws IOException{
        PrintWriter html = new PrintWriter(new FileWriter("/Users/lechners/IdeaProjects/HTML TryThePie/index2.html"));

        html.println("<!DOCTYPE html>\n" +
                "<html lang=\"de\">\n" +
                "<head>\n" +
                "    <title>Try The Pie</title>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
                "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div>\n" +
                "    <h2>Try the Pie</h2>\n" +
                "    <p>We will try the Pie now at Jura:</p>\n" +
                "    <table class=\"table table-striped\">\n" +
                "        <thead>\n" +
                "        <tr>\n" +
                "            <th>Belegnr</th>\n" +
                "            <th>kontrolldatum</th>\n" +
                "            <th>Lieferdatum</th>\n" +
                "            <th>Hinweis</th>\n" +
                "            <th>Status</th>\n" +
                "            <th>Absagegrund</th>\n" +
                "            <th>Nachgehakt am</th>\n" +
                "            <th>Veredler</th>\n" +
                "            <th>Versandart</th>\n" +
                "            <th>Absender</th>\n" +
                "            <th>Versandpapiere</th>\n" +
                "            <th>Einlieferdatum</th>\n" +
                "            <th>Ware am Lager</th>\n" +
                "            <th>Lagerort</th>\n" +
                "            <th>bf_blv_feld_11</th>\n" +
                "        </tr>\n" +
                "        </thead>\n" +
                "        <tbody>");

        for(int i = 0; i < belege.size(); i++){
            html.println("<tr>");
                html.println("<td>" + belege.get(i).returnBelegnr() + "</td>");
                html.println("<td>" + belege.get(i).returnKontrolldatum() + "</td>");
                html.println("<td>" + belege.get(i).returnLieferdatum() + "</td>");
                html.println("<td>" + belege.get(i).returnHinweis() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_1() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_2() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_3() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_4() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_5() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_6() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_7() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_8() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_9() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_10() + "</td>");
                html.println("<td>" + belege.get(i).return_bf_blv_feld_11() + "</td>");
            html.println("</tr>");

        }

        html.println(" </tbody>\n" +
                "    </table>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        html.close();
        return html.toString();
    }

}
