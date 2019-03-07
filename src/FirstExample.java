//STEP 1. Import required packages
import java.io.FileWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.sybase.jdbc4.jdbc.*;
import de.trythepie.db.*;
import de.trythepie.html.CreateHTMLTableRow;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import de.trythepie.html.*;

public class FirstExample {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.sybase.jdbc4.jdbc.SybDriver";


   // Jura Live
    static final String DB_URL = "jdbc:sybase:Tds:192.168.1.101:2638?ServiceName=HSABJura_DB";
    //  Database credentials
    static final String USER = "lechners";
    static final String PASS = "Heidelberg";

    // JSON File Path

    static final String JSONUrl = "/Users/lechners/IdeaProjects/TryThePie/out/json/jura-live.json";
    //static final String JSONUrl = "/var/www/jura/trythepie/json/jura-belege.json";


    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT belegart, belegnr, kontrolldatum, lieferdatum, hinweis, notiz, auftragsnummer, bf_blv_feld_1, bf_blv_feld_2, bf_blv_feld_3, bf_blv_feld_4, bf_blv_feld_5, bf_blv_feld_6, bf_blv_feld_7, bf_blv_feld_8, bf_blv_feld_9, bf_blv_feld_10, bf_blv_feld_11 FROM hs.vk_beleg where kontrolldatum >= DATEADD(DAY, 2 - DATEPART(WEEKDAY, GETDATE()), CAST(GETDATE() AS DATE)) AND belegart = 'AB ' order by kontrolldatum desc";
            ResultSet rs = stmt.executeQuery(sql);



            //STEP 5 Write JSON

            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy", Locale.GERMAN);
            DateFormat dateFormatSort = new SimpleDateFormat("YYYY/MM/DD");
            boolean flag = false;
            try {
                FileWriter fileWriter = new FileWriter(JSONUrl, false);
                fileWriter.write("[ \n");
                fileWriter.flush();

            while(rs.next()) {
                JSONObject jsonObject = new JSONObject();

                if (flag)
                    fileWriter.write(",\n");
                jsonObject.put("belegnr", Integer.toString(rs.getInt("belegnr") ));
                jsonObject.put("belegart", rs.getString("belegart"));
                if (rs.getDate("kontrolldatum")!=null)
                jsonObject.put("kontrolldatum", dateFormat.format(rs.getDate("kontrolldatum")) );
                else
                    jsonObject.put("kontrolldatum", "");
                jsonObject.put("kontrolldatum-sort", dateFormat.format(rs.getDate("kontrolldatum")) );

                if (rs.getDate("lieferdatum")!=null)
                jsonObject.put("lieferdatum", dateFormat.format( rs.getDate("lieferdatum")));
                else
                    jsonObject.put("lieferdatum", "");
               // if (rs.getString("bf_blv_feld_1")!=null)
                jsonObject.put("auftragsnummer", rs.getString("auftragsnummer"));
                jsonObject.put("notiz", rs.getString("notiz"));
                jsonObject.put("bf_blv_feld_1", rs.getString("bf_blv_feld_1"));
                jsonObject.put("bf_blv_feld_2", rs.getString("bf_blv_feld_2"));
                if (rs.getDate("bf_blv_feld_3")!=null)
                    jsonObject.put("bf_blv_feld_3", dateFormat.format(rs.getDate("bf_blv_feld_3")));
                else
                    jsonObject.put("bf_blv_feld_3", "");
                jsonObject.put("bf_blv_feld_4", rs.getString("bf_blv_feld_4"));
                jsonObject.put("bf_blv_feld_5", rs.getString("bf_blv_feld_5"));
                jsonObject.put("bf_blv_feld_6", rs.getString("bf_blv_feld_6"));
                jsonObject.put("bf_blv_feld_7", rs.getString("bf_blv_feld_7"));
                jsonObject.put("bf_blv_feld_8", rs.getString("bf_blv_feld_8"));
                jsonObject.put("bf_blv_feld_9", rs.getString("bf_blv_feld_9"));
                jsonObject.put("bf_blv_feld_10", rs.getString("bf_blv_feld_10"));
                jsonObject.put("bf_blv_feld_11", rs.getString("bf_blv_feld_11"));


                // writing the JSONObject into a file(String: JSONUrl)
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.flush();
                flag = true;
                }

                fileWriter.write("]");
                fileWriter.flush();
            }catch (Exception e) {
                e.printStackTrace();
            }



            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            System.out.print("SE: ");
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            System.out.print("S: ");
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try





        System.out.println("Goodbye!");
    }//end main
}//end FirstExample
