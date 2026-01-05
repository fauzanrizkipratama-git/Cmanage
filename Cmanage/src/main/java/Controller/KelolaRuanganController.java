/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import DAO.RuanganDAO;
import Model.Ruangan;
import java.net.URL;
import java.util.ResourceBundle;
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
public class KelolaRuanganController implements Initializable {

    @FXML
    private TextField txtIdRuangan;
    @FXML
    private TextField txtNamaRuangan;
    @FXML
    private TextField txtLokasi;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnHapus;
    @FXML
    private Button btnTambah;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
private void handleButton(ActionEvent event) {
    if (event.getSource() == btnTambah) {
        Ruangan ruangBaru = new Ruangan(
            txtIdRuangan.getText(),
            txtNamaRuangan.getText(),
            txtLokasi.getText()
        );

        RuanganDAO.TambahRuangan(ruangBaru);
        System.out.println("Ruangan berhasil ditambahkan!");
        refreshAndClose();
    } 
    
    else if (event.getSource() == btnEdit) {
        Ruangan ruangUpdate = new Ruangan(
            txtIdRuangan.getText(),
            txtNamaRuangan.getText(),
            txtLokasi.getText()
        );

        RuanganDAO.editRuangan(ruangUpdate); 
        System.out.println("Ruangan berhasil diperbarui!");
        
        refreshAndClose();
    } 
    
    else if (event.getSource() == btnHapus) {
        String id = txtIdRuangan.getText();
        if (!id.isEmpty()) {
            RuanganDAO.hapusRuangan(id);
            System.out.println("Ruangan berhasil dihapus!");
            refreshAndClose();
        } else {
            System.out.println("ID Ruangan harus diisi untuk menghapus!");
        }
    }
}

private void refreshAndClose() {
    if (controller.MenuDaftarRuanganController.getInstance() != null) {
        controller.MenuDaftarRuanganController.getInstance().refreshData();
    }
    Stage stage = (Stage) txtIdRuangan.getScene().getWindow();
    stage.close();
}

public void setDataRuangan(Ruangan ruang) {
    txtIdRuangan.setText(ruang.getId());
    txtNamaRuangan.setText(ruang.getNama());
    txtLokasi.setText(ruang.getLokasi());

    txtIdRuangan.setEditable(false);
}
    
}
