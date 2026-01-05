/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.BaseDAO.closeCon;
import static DAO.BaseDAO.getCon;
import Model.Jadwal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author loq
 */
public class JadwalDAO extends BaseDAO {
        private static PreparedStatement st;
    private static Connection con;
    
    
   public static LinkedList<Jadwal> getByDate(LocalDate filterDate) {
    LinkedList<Jadwal> res = new LinkedList<>();
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
        con = getCon();
        String query = "SELECT j.*, m.nama AS nama_matkul FROM jadwal j " +
                       "JOIN mata_kuliah m ON j.idMatkul = m.id_matkul " +
                       "WHERE j.tanggal = ?";

        st = con.prepareStatement(query);
        st.setDate(1, java.sql.Date.valueOf(filterDate));
        rs = st.executeQuery();

        while (rs.next()) {
            Jadwal s = new Jadwal(
                rs.getString("id_jadwal"),
                rs.getString("jam"), 
                rs.getString("tanggal"), 
                rs.getString("id_ruangan"),
                rs.getString("idMatkul"),
                rs.getString("nama_matkul"),
                rs.getString("kelas")
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
   
   public static Jadwal getDetail(String namaMatkul, LocalDate tanggal) {
    Jadwal j = null;
    String query = "SELECT j.*, m.nama AS nama_matkul FROM jadwal j " +
                   "JOIN mata_kuliah m ON j.idMatkul = m.id_matkul " +
                   "WHERE m.nama = ? AND j.tanggal = ?";
    try (Connection con = getCon();
         PreparedStatement st = con.prepareStatement(query)) {
        st.setString(1, namaMatkul);
        st.setDate(2, java.sql.Date.valueOf(tanggal));
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            j = new Jadwal (rs.getString("id_jadwal"), rs.getString("jam"), 
                         rs.getString("tanggal"), rs.getString("id_ruangan"), 
                         rs.getString("idMatkul"), rs.getString("nama_matkul"), rs.getString("kelas"));
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return j;
}
   public static Map<String, Integer> getStatistikRuanganBulanan() {
    Map<String, Integer> dataStatistik = new LinkedHashMap<>();
    // Query mengambil nama ruangan dan jumlah pemakaiannya di bulan ini
    String query = "SELECT r.nama, COUNT(j.id_jadwal) as jumlah " +
                   "FROM ruangan r " +
                   "LEFT JOIN jadwal j ON r.id = j.id_ruangan " +
                   "AND MONTH(j.tanggal) = MONTH(CURRENT_DATE()) " +
                   "AND YEAR(j.tanggal) = YEAR(CURRENT_DATE()) " +
                   "GROUP BY r.nama " +
                   "ORDER BY jumlah DESC";
                   
    try (Connection con = getCon(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
        while (rs.next()) {
            dataStatistik.put(rs.getString("nama"), rs.getInt("jumlah"));
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return dataStatistik;
}

public static boolean tambah(Jadwal j) {
    String query = "INSERT INTO jadwal (jam, tanggal, id_ruangan, idMatkul, kelas) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection con = getCon();
         PreparedStatement st = con.prepareStatement(query)) {
        
        st.setString(1, j.getJam());
        st.setDate(2, java.sql.Date.valueOf(j.getTanggal()));
        st.setString(3, j.getIdRuangan());
        st.setString(4, j.getIdMatkul());
        st.setString(5, j.getKelas());
        
        int hasil = st.executeUpdate();
        return hasil > 0;
    } catch (SQLException e) {
        System.out.println("Gagal Tambah Jadwal: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
   
   public static boolean hapus(String idJadwal) {
    String query = "DELETE FROM jadwal WHERE id_jadwal = ?";
    try (Connection con = getCon();
         PreparedStatement st = con.prepareStatement(query)) {
        
        st.setString(1, idJadwal);
        return st.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   
}
