package model;

import java.awt.image.DataBufferUShort;

public class DB_CRED {
    private static String DB = "/karteikartenApp";
    private static String DB_URL = "jdbc:msql://localhost"+DB;
    private static String DB_USER = "root";
    private static String DB_PW = "";
    private static String driver = "com.mysql.cj.jdbc.Driver";


    public static String getDBurl(){
        return DB_URL;
    }

    public static String getDBuser(){
        return DB_USER;
    }

    public static String getDBpw(){
        return DB_PW;
    }

    public static String getDBdriver(){
        return driver;
    }

}
