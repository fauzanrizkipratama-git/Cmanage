package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    
    private static final String DB_NAME = "cmanage";
    private static final String DB_HOST = "localhost";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public static Connection getCon() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME;
            con = DriverManager.getConnection(url, DB_USER, DB_PASS);
        } catch (Exception e) {
            System.err.println("Gagal koneksi ke database: " + e.getMessage());
        }
        return con;
    }

    public static void closeCon(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
