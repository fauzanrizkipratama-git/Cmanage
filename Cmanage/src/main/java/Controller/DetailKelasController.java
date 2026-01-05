/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import DAO.JadwalDAO;
import DAO.MataKuliahDAO;
import DAO.RuanganDAO;
import DAO.UserDAO;
import Model.Jadwal;
import Model.MataKuliah;
import Model.Ruangan;
import Model.User;
import static controller.KelolaJadwalController.idJadwalTerpilih;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class DetailKelasController implements Initializable {

    private Jadwal jadwalTerpilih;
    @FXML
    private VBox vbKelolaRuangan;
    @FXML
    private Button btnBatal;
    @FXML
    private Label lblMatkul;
    @FXML
    private Label lblRuangan;
    @FXML
    private Label lblTanggal;
    @FXML
    private Label lblDurasi;
    @FXML
    private Label lblKelas;
    @FXML
    private Label lblDosen;
    @FXML
    private Label lblSks;
    @FXML
    private Button btnHapus;
    private RefreshCallback callback;
    private String idUntukDihapus;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (controller.LoginController.user != null) {
        String peran = controller.LoginController.user.getPeran().trim();

        if ("mahasiswa".equalsIgnoreCase(peran) || "dosen".equalsIgnoreCase(peran)) {
            btnHapus.setVisible(false);
            btnHapus.setManaged(false);
        }
    } else {
        System.out.println("User belum login!");
    }
    }    
    public void setData(Jadwal j) {
        MataKuliah mk = MataKuliahDAO.getByName(j.getNama_matkul());
        Ruangan ruangan = RuanganDAO.getById(j.getIdRuangan());
        User dosen = UserDAO.getById(mk.getId_user());
        this.idUntukDihapus = j.getIdJadwal();
        this.jadwalTerpilih = j;
        lblMatkul.setText(j.getNama_matkul());
        lblRuangan.setText(ruangan.getNama());
        lblTanggal.setText(j.getTanggal());
        lblDosen.setText(dosen.getNama());
        lblSks.setText(mk.getSks() + " SKS");
        lblDurasi.setText(j.getJam());
        lblKelas.setText(j.getKelas());
    }
    
    public interface RefreshCallback {
    void onRefresh();
}
    
    public void setRefreshCallback(RefreshCallback callback) {
        this.callback = callback;
    }

    @FXML
    private void handleButton(ActionEvent event) {
        if (event.getSource() == btnHapus) {
            if (JadwalDAO.hapus(idUntukDihapus)) {
                System.out.println("Gim, Data berhasil dihapus!");
                
                // Jalankan refresh sebelum menutup jendela
                if (callback != null) {
                    callback.onRefresh();
                }
                closeWindow(event);
            }
        } else if (event.getSource() == btnBatal) {
            closeWindow(event);
        }
    }

    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
    }
   
