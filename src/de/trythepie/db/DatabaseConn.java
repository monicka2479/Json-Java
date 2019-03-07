package de.trythepie.db;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Simon.

public class DatabaseConn {
     public void connectDatabase(String DBUrl, String username, String password) {


     }*/
 /*



 Connection con = DriverManager.getConnection( DBUrl, username, password);
     Statement stmt = con.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT belegnr, kontrolldatum, lieferdatum, hinweis FROM hs.vk_beleg where belegdatum >= DATEADD(DAY, 2 - DATEPART(WEEKDAY, GETDATE()), CAST(GETDATE() AS DATE)) order by belegdatum desc");

         /*
         while (rs.next()) {

             int x = rs.getInt("a");
             String s = rs.getString("b");
             float f = rs.getFloat("c");
         }










public class de.trythepie.db.DatabaseConn {
    private final static Logger LOGGER = Logger.getLogger(de.trythepie.db.DatabaseConn.class.getName());
    private Connection connection;

    public void connect(String connection_str, String username, String password) throws SQLException {
        LOGGER.config(String.format("Connecting to %s", connection_str));
        this.connection = DriverManager.getConnection(connection_str, username, password);
    }

    public void disconnect() throws SQLException {
        LOGGER.config("disconnecting");
        this.connection.close();
        this.connection = null;
    }

    public void expandChain(List<Auftrag> auftraege) throws SQLException {
        while (!allChainsExpanded(auftraege)) {
            String typeSet = this.getTypeSet(auftraege);
            String auftragsSet = this.getAuftragsSet(auftraege);
            String sql = String.format("SELECT * FROM hs.vk_abrufkette WHERE ublg_belegart IN %s AND ublg_belegnr IN %s ORDER BY abruf_am ASC", typeSet, auftragsSet);
            LOGGER.config(sql);
            Statement stmt = this.connection.createStatement();


        }
    }

}*/