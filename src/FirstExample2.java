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

public class FirstExample2 {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";


   // Jura Live
    static final String DB_URL = "jdbc:mysql://localhost:3306/sonoo";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    // JSON File Path

    static final String JSONUrl = "src/out/json/jura-live7.json";
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
            sql = "SELECT belegart, belegnr, kontrolldatum, lieferdatum, hinweis, notiz, auftragsnummer, bf_blv_feld_1, bf_blv_feld_2, bf_blv_feld_3, bf_blv_feld_4, bf_blv_feld_5, bf_blv_feld_6, bf_blv_feld_7, bf_blv_feld_8, bf_blv_feld_9, bf_blv_feld_10, bf_blv_feld_11 FROM vk_beleg where belegart = 'AB ' order by kontrolldatum desc";
           ResultSet rs = stmt.executeQuery(sql);
          

           //STEP 5 Write JSON

           DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy", Locale.GERMAN);
           DateFormat dateFormatSort = new SimpleDateFormat("YYYY/MM/DD");
           boolean flag = false;
           try {

               FileWriter fileWriter = new FileWriter(JSONUrl, false);

               fileWriter.flush();

               fileWriter.write("{ \n");
               fileWriter.write("\"draw\": 1,");
               rs.last();
               fileWriter.write("\"recordsTotal\" :"+rs.getRow()+", ");
               fileWriter.write("\"recordsFiltered\": "+ rs.getRow()+", ");
               fileWriter.write("\n \"data\": \n");
               fileWriter.write("[ \n");
               rs.beforeFirst();
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
//                   jsonObject.put("kontrolldatum-sort", dateFormat.format(rs.getDate("kontrolldatum")) );

                   if (rs.getDate("lieferdatum")!=null)
                       jsonObject.put("lieferdatum", dateFormat.format( rs.getDate("lieferdatum")));
                   else
                       jsonObject.put("lieferdatum", "");
                   // if (rs.getString("bf_blv_feld_1")!=null)

                   stmt = conn.createStatement();
                   String query;
                   query = "SELECT belegnr, zeilenschluessel, positionsnr, artikelnr, menge_le, "
                   		+ "text FROM vk_beleg_pos where belegnr = "+ Integer.toString(rs.getInt("belegnr"));
                  stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_READ_ONLY);
                   ResultSet innerSet = stmt.executeQuery(query);
                  List<JSONObject> list = new ArrayList<JSONObject>();
                   while(innerSet.next()) {
                       JSONObject jsonObject1 = new JSONObject();
                       
                       jsonObject1.put("positionn", innerSet.getString("positionsnr"));
                       jsonObject1.put("artikelnr",innerSet.getString("artikelnr"));
                       jsonObject1.put("menge",innerSet.getString("menge_le"));
                       
                       String text="";
                       try {
                    	   Integer.parseInt(innerSet.getString("artikelnr"));
                       if((innerSet.getString("artikelnr").equals("")) && 
                       ((Long.parseLong(innerSet.getString("artikelnr")))==999999990)){
                    	   jsonObject.put("text",text); 	
                       }
                       else {   
					   String query1;
					   query1 = "SELECT artikelnr, bezeichnung1, bf_art_feld_6, bf_art_feld_7,  bf_schluessel"
					   		+ "  FROM vk_artikel WHERE artikelnr = '"+ innerSet.getString("artikelnr")+"'";
					 // System.out.println("query1: "+query1);
					   stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							   ResultSet.CONCUR_READ_ONLY);
					   ResultSet innerSet1 = stmt.executeQuery(query1);
					 
						   while(innerSet1.next()) {
							 text = innerSet1.getString("bezeichnung1")+ " "+ innerSet1.getString("bf_art_feld_6") 
							 + " "+innerSet1.getString("bf_art_feld_7")
							 +" "+innerSet1.getString("bf_schluessel");	
							 jsonObject.put("text",text); 	
						   }
                       }
                       
					   
					   } catch (NumberFormatException e) {
						 }
                   
                       list.add(jsonObject1);
                   }
                   jsonObject.put("position", list);
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

               fileWriter.write("] \n }");
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
