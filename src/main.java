import javax.print.DocFlavor;
import java.util.Scanner;
import java.util.*;
import java.sql.*;
import java.lang.String;
import de.trythepie.db.*;
/*
public class main {
    public static void main(String[] args) {

       // ArrayList<String> databaselist = new ArrayList<>();

    //connectDatabase("jdbc:sybase:Tds:192.168.1.101:2638?ServiceName=JuraTestDB","ab","tortola");



    }
}

//STEP 1. Import required packages


public class FirstExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:sybase:Tds:192.168.1.101:2638?ServiceName=JuraTestDB";

    //  Database credentials
    static final String USER = "ab";
    static final String PASS = "tortola";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            //Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT belegnr, hinweis FROM hs.vk_beleg where belegdatum >= DATEADD(DAY, 2 - DATEPART(WEEKDAY, GETDATE()), CAST(GETDATE() AS DATE)) order by belegdatum desc";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("belegnr");
                String first = rs.getString("hinweis");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Hinweis: " + first);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
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












/*
        String[] artikel = new String[5];
        int i = 0;
        while(true) {
            // Get the users input
            Scanner Input = new Scanner(System.in);
            System.out.println("Enter text:");
            artikel[i] = Input.nextLine();
            System.out.println(artikel[i]);
            if (artikel[i].equals("show")) {
                 for (int p=0;p<artikel.size();p++){
                    System.out.println(artikel[p]);
                }
            }
            if (artikel[i].isEmpty()) {
                System.out.println("Please enter valid infomation.");
                break;
            }

            if (artikel[i].equals("stop")) {
                System.out.println("Ok we stop");
                break;
            }
            continue;
        }


        // create an empty arraylist with an initial capacity
        List arrlist = new ArrayList<Integer>(5);

        // use add() method to add elements in the list
        arrlist.add(15);
        arrlist.add(20);
        arrlist.add(25);
        arrlist.add(22);

        // let us print all the elements available in list
        for (Integer number : arrlist) {
            System.out.println("Number = " + number);
        }

        // this will print the size of this list
        int retval = arrlist.size();
        System.out.println("Size of list = " + retval);


// Index         0                1          2
        String[] a = { "Asus",       "Elitegroup", "MSI" };
        String[] b = { "Elitegroup", "MSI",        "Shuttle" };

        System.out.println( Arrays.asList( a ).subList( 1, 3 ).
                equals( Arrays.asList( b ).subList( 0, 2 ) ) );  // true
                */