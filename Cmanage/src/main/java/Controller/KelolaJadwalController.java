package controller;

import DAO.JadwalDAO;
import DAO.MataKuliahDAO;
import DAO.RuanganDAO;
import Model.Jadwal;
import Model.Ruangan;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class KelolaJadwalController implements Initializable {

    @FXML
    private VBox vbKelolaRuangan;
    @FXML
    private ComboBox<String> cmbRuangan;
    private LinkedList<Ruangan> listRuanganDB;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnBatal;
    @FXML
    private DatePicker dttanggal;
    @FXML
    private TextField txtdurasikelas;
    @FXML
    private TextField txtnamaKelas;
    public static String idJadwalTerpilih;
    @FXML
    private TextField txtIdMatkul;
    private DetailKelasController.RefreshCallback callback;
    /**
     * Initializes the controller class.
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
    listRuanganDB = RuanganDAO.getAll(); 
    ObservableList<String> namaRuangan = FXCollections.observableArrayList();
    for (Ruangan r : listRuanganDB) {
        namaRuangan.add(r.getNama());
    }
    cmbRuangan.setItems(namaRuangan);
}

// Method untuk mendapatkan ID Ruangan yang dipilih user
private String getSelectedRuanganID() {
    String selectedName = cmbRuangan.getValue();
    for (Ruangan r : listRuanganDB) {
        if (r.getNama().equals(selectedName)) {
            return r.getId(); // Mengembalikan ID (misal: R01)
        }
    }
    return null;
}   
    
public void setRefreshCallback(DetailKelasController.RefreshCallback callback) {
    this.callback = callback;
}

@FXML
private void handleButton(ActionEvent event) {
    if (event.getSource() == btnTambah) {
        String idRuangan = getSelectedRuanganID(); 
        String idMatkul = txtIdMatkul.getText(); 
        
        Jadwal j = new Jadwal(
            txtdurasikelas.getText(),
            dttanggal.getValue().toString(),
            idRuangan,
            idMatkul,
            txtnamaKelas.getText()
        );

        if (JadwalDAO.tambah(j)) {
            System.out.println("Berhasil Tambah Data!");
            
            // 1. Panggil refresh dulu agar tabel di MenuJadwal update
            if (callback != null) {
                callback.onRefresh();
            }
            
            // 2. Baru tutup jendelanya
            closeWindow(event); 
        } else {
            System.out.println("Gagal Tambah Data!");
        }
    } 
    else if (event.getSource() == btnBatal) {
        closeWindow(event);
    }
}

// Gunakan satu method saja untuk menutup jendela agar efisien
private void closeWindow(ActionEvent event) {
    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    stage.close();
}
}



