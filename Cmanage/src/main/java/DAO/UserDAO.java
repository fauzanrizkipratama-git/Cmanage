package DAO;

import static DAO.BaseDAO.closeCon;
import static DAO.BaseDAO.getCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.UUID;


public class UserDAO extends BaseDAO{
    private static PreparedStatement st;
    private static Connection con;

    public static User validate(String nomorInduk, String password) { 
    User u = null;
    String query = "SELECT * FROM users WHERE nomor_induk = ? AND password = ?";  
    try (Connection con = getCon();
         PreparedStatement st = con.prepareStatement(query)) {
        
        st.setString(1, nomorInduk);
        st.setString(2, password);
        
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                u = new User(
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("peran"),
                    rs.getString("nomor_induk"), 
                    rs.getString("password")
                );  
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return u;
}
 
    public static int getTotalUser() {
    int total = 0;
    String query = "SELECT COUNT(*) AS total FROM users";
    try (Connection con = getCon(); 
         Statement st = con.createStatement(); 
         ResultSet rs = st.executeQuery(query)) {
        if (rs.next()) {
            total = rs.getInt("total");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}
    
   public static LinkedList<User> getAll(User u) {
    LinkedList<User> res = new LinkedList<>();
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
        con = getCon();
        String query = "SELECT * FROM users";

        st = con.prepareStatement(query);
        rs = st.executeQuery();
        while (rs.next()) {
            User s = new User(
                rs.getString("id"),
                rs.getString("nama"), 
                rs.getString("nomor_induk"),
                rs.getString("password"),
                rs.getString("peran")
            );
            res.add(s);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (st != null) st.close(); } catch (Exception e) {}
        closeCon(con);
    }
    return res;
}
   
   public static User getById(String id) {
    User u = null;
    String query = "SELECT * FROM users WHERE id = ?";
    try (Connection con = getCon();
         PreparedStatement st = con.prepareStatement(query)) {
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            // Sesuaikan urutan constructor Model User kamu
            u = new User(rs.getString("id"), rs.getString("nama"), rs.getString("peran"));
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return u;
}
   
   public static LinkedList<User> getLimitedUsers() {
    LinkedList<User> res = new LinkedList<>();
    // Query hanya mengambil mahasiswa dan pj_matkul
    String query = "SELECT * FROM users WHERE peran IN ('mahasiswa', 'pj_matkul')";
    
    try (Connection con = getCon();
         PreparedStatement st = con.prepareStatement(query);
         ResultSet rs = st.executeQuery()) {
         
        while (rs.next()) {
            res.add(new User(
                rs.getString("id"),
                rs.getString("nama"),
                rs.getString("nomor_induk"),
                rs.getString("password"),
                rs.getString("peran")
            ));
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return res;
}
    public static void TambahPengguna(User u) {
    Connection con = null;
    PreparedStatement st = null;

    try {
        con = getCon(); 
        String query = "INSERT INTO users (id, nama, nomor_induk, password, peran) VALUES (?, ?, ?, ?, ?)";

        st = con.prepareStatement(query);
        st.setString(1, u.getId());
        st.setString(2, u.getNama());
        st.setString(3, u.getNomor_induk());
        st.setString(4, u.getPassword());
        st.setString(5, u.getPeran());

        int rowsInserted = st.executeUpdate();
        
        if (rowsInserted > 0) {
            System.out.println("Data user berhasil ditambahkan!");
        }
    } catch (SQLException e) {
        System.err.println("Gagal tambah pengguna: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try { if (st != null) st.close(); } catch (SQLException e) {}
        closeCon(con);
    }
}
    
public static void hapusPengguna(String id) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        con = getCon();
        String query = "DELETE FROM users WHERE id = ?";
        st = con.prepareStatement(query);
        st.setString(1, id);
        st.executeUpdate();
        System.out.println("Data berhasil dihapus!");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (st != null) st.close(); } catch (SQLException e) {}
        closeCon(con);
    }
}

public static void editPengguna(User u) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        con = getCon();
        String query = "UPDATE users SET nama = ?, nomor_induk = ?, password = ?, peran = ? WHERE id = ?";
        st = con.prepareStatement(query);
        st.setString(1, u.getNama());
        st.setString(2, u.getNomor_induk());
        st.setString(3, u.getPassword());
        st.setString(4, u.getPeran());
        st.setString(5, u.getId());
        
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (st != null) st.close(); } catch (SQLException e) {}
        closeCon(con);
    }
}

}
