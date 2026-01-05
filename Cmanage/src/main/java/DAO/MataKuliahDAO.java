/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.BaseDAO.closeCon;
import static DAO.BaseDAO.getCon;
import Model.MataKuliah;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author loq
 */
public class MataKuliahDAO {
    public static MataKuliah getByName(String nama) {
    MataKuliah mk = null;
    String query = "SELECT * FROM mata_kuliah WHERE nama = ?";
    try (Connection con = getCon();
         PreparedStatement st = con.prepareStatement(query)) {
        st.setString(1, nama);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            mk = new MataKuliah(
                rs.getString("id_matkul"),
                rs.getString("nama"),
                rs.getInt("sks"),
                rs.getString("id_user") // Ini ID Dosennya
            );
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return mk;
}
    public static String getIdUser(String namaMatkul) {
    String idUser = null;
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    // Query untuk mengambil id_user (dosen) berdasarkan nama mata kuliah
    String query = "SELECT id_user FROM mata_kuliah WHERE nama = ?";

    try {
        con = getCon();
        st = con.prepareStatement(query);
        st.setString(1, namaMatkul);
        rs = st.executeQuery();

        if (rs.next()) {
            idUser = rs.getString("id_user");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Menutup resource database
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (st != null) st.close(); } catch (Exception e) {}
        closeCon(con);
    }
    return idUser;
}
}

