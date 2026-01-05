package DAO;

import static DAO.BaseDAO.closeCon;
import static DAO.BaseDAO.getCon;
import Model.Ruangan;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;

public class RuanganDAO extends BaseDAO{
    private static PreparedStatement st;
    private static Connection con;
    
    
   public static LinkedList<Ruangan> getAll() {
    LinkedList<Ruangan> res = new LinkedList<>();
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
        con = getCon();
        String query = "SELECT * FROM ruangan";

        st = con.prepareStatement(query);
        rs = st.executeQuery();
        while (rs.next()) {
            Ruangan s = new Ruangan(
                rs.getString("id"),
                rs.getString("nama"), 
                rs.getString("lokasi")
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
   
   public static Ruangan getById(String id) {
    Ruangan r = null;
    String query = "SELECT * FROM ruangan WHERE id = ?";
    try (Connection con = getCon();
         PreparedStatement st = con.prepareStatement(query)) {
        st.setString(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            r = new Ruangan(rs.getString("id"), rs.getString("nama"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return r;
}
       public static void TambahRuangan(Ruangan r) {
    Connection con = null;
    PreparedStatement st = null;

    try {
        con = getCon(); 
        String query = "INSERT INTO ruangan (id, nama, lokasi) VALUES (?, ?, ?)";

        st = con.prepareStatement(query);
        st.setString(1, r.getId());
        st.setString(2, r.getNama());
        st.setString(3, r.getLokasi());

        int rowsInserted = st.executeUpdate();
        
        if (rowsInserted > 0) {
            System.out.println("Data ruangan berhasil ditambahkan!");
        }
    } catch (SQLException e) {
        System.err.println("Gagal tambah ruangan: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try { if (st != null) st.close(); } catch (SQLException e) {}
        closeCon(con);
    }
}
    
public static void hapusRuangan(String id) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        con = getCon();
        String query = "DELETE FROM ruangan WHERE id = ?";
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

public static void editRuangan(Ruangan r) {
    Connection con = null;
    PreparedStatement st = null;
    try {
        con = getCon();
        String query = "UPDATE ruangan SET nama = ?, lokasi = ? WHERE id = ?";
        st = con.prepareStatement(query);
        st.setString(1, r.getNama());
        st.setString(2, r.getLokasi());
        st.setString(3, r.getId());
        
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (st != null) st.close(); } catch (SQLException e) {}
        closeCon(con);
    }
}

public static int getTotalRuangan() {
    int total = 0;
    String query = "SELECT COUNT(*) AS total FROM ruangan";
    try (Connection con = getCon(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
        if (rs.next()) total = rs.getInt("total");
    } catch (SQLException e) { e.printStackTrace(); }
    return total;
}

public static int getRuanganTerpakai() {
    int terpakai = 0;
    // Menghitung ruangan yang ada di tabel jadwal untuk hari ini
    String query = "SELECT COUNT(DISTINCT id_ruangan) AS total FROM jadwal WHERE tanggal = ?";
    try (Connection con = getCon(); PreparedStatement st = con.prepareStatement(query)) {
        st.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
        ResultSet rs = st.executeQuery();
        if (rs.next()) terpakai = rs.getInt("total");
    } catch (SQLException e) { e.printStackTrace(); }
    return terpakai;
}
}