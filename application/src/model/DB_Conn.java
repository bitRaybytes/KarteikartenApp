package model;

import javax.swing.*;
import java.sql.*;

public class DB_Conn {

    private static Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private static String[] fail =
            {

                    "Fehler! Treiber nicht gefunden.\n",
                    "Fehler beim Schließen der Datenbank.\n"
            };

    private static String DBurl = DB_CRED.getDBurl();
    private static String DBuser= DB_CRED.getDBuser();
    private static String DBpass= DB_CRED.getDBpw();

    private static Connection db_connection() throws SQLException{
        try{
            Class.forName(DB_CRED.getDBdriver());

        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, fail[0]+e.getMessage());
        }

        conn = DriverManager.getConnection(DBurl, DBuser, DBpass);
        System.out.println("Verbindung hergestellt");

        return conn;
    }

    public DB_Conn() throws SQLException{
        conn = db_connection();
    }

    private void dbClose() throws SQLException{
        try{
            if (db_connection() != null && db_connection().isClosed()){
                db_connection().close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,fail[1]+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException{
        new DB_Conn();
    }

}
