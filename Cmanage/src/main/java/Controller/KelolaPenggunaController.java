package controller;

import DAO.UserDAO;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class KelolaPenggunaController implements Initializable {

    @FXML
    private TextField txtIdPengguna;
    @FXML
    private TextField txtNamaLengkap;
    @FXML
    private TextField txtNomorInduk;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnHapus;
    @FXML
    private ComboBox<String> cmbPeran;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
public void initialize(URL url, ResourceBundle rb) {
    String peranLogin = controller.LoginController.user.getPeran();
    if ("dosen".equalsIgnoreCase(peranLogin)) {
        ObservableList<String> pilihanPeran = FXCollections.observableArrayList("mahasiswa", "pj_matkul");
        cmbPeran.setItems(pilihanPeran);
        btnEdit.setVisible(false);
        btnEdit.setManaged(false);
        btnHapus.setVisible(false);
        btnHapus.setManaged(false);
    } else {
        ObservableList<String> pilihanPeran = FXCollections.observableArrayList("admin", "dosen", "mahasiswa", "pj_matkul");
        cmbPeran.setItems(pilihanPeran);
    }
} 
    
@FXML
private void handleButton(ActionEvent event) {
    if (event.getSource() == btnTambah) {
        if (txtIdPengguna.getText().isEmpty() || txtNamaLengkap.getText().isEmpty() || 
            txtPassword.getText().isEmpty() || cmbPeran.getValue() == null) {
            
            System.out.println("Semua kolom harus diisi!");
            return;
        }
        User userBaru = new User(
            txtIdPengguna.getText(),
            txtNamaLengkap.getText(),
            txtNomorInduk.getText(),
            txtPassword.getText(),
            cmbPeran.getValue()
        );

        UserDAO.TambahPengguna(userBaru);
        System.out.println("User berhasil ditambahkan!");
        refreshAndClose();
    } 
    
    else if (event.getSource() == btnEdit) {
        User userUpdate = new User(
            txtIdPengguna.getText(),
            txtNamaLengkap.getText(),
            txtNomorInduk.getText(),
            txtPassword.getText(),
            cmbPeran.getValue()
        );

        UserDAO.editPengguna(userUpdate); 
        System.out.println("User berhasil diperbarui!");
        
        refreshAndClose();
    } 
    
    else if (event.getSource() == btnHapus) {
        String id = txtIdPengguna.getText();
        if (!id.isEmpty()) {
            UserDAO.hapusPengguna(id);
            System.out.println("User berhasil dihapus!");
            refreshAndClose();
        } else {
            System.out.println("ID Pengguna harus diisi untuk menghapus!");
        }
    }
}

private void refreshAndClose() {
    if (Controller.MenuDaftarPenggunaController.getInstance() != null) {
        Controller.MenuDaftarPenggunaController.getInstance().refreshData();
    }
    Stage stage = (Stage) txtIdPengguna.getScene().getWindow();
    stage.close();
}

public void setDataUser(User user) {
    txtIdPengguna.setText(user.getId());
    txtNamaLengkap.setText(user.getNama());
    txtNomorInduk.setText(user.getNomor_induk());
    txtPassword.setText(user.getPassword());
    cmbPeran.setValue(user.getPeran());

    txtIdPengguna.setEditable(false);
}
    
}
