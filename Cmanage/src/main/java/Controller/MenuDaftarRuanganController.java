package controller;

import DAO.BaseDAO;
import DAO.RuanganDAO;
import Model.Ruangan;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class MenuDaftarRuanganController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane header;
    @FXML
    private Button btnKelolaRuangan;
    @FXML
    private TableView<Ruangan> tvDaftarRuangan;
    @FXML
    private TableColumn<Ruangan, String> colIdRuangan;
    @FXML
    private TableColumn<Ruangan, String> colNamaRuangan;
    @FXML
    private TableColumn<Ruangan, String> colLokasi;
    
    private ObservableList<Ruangan> RuangList = FXCollections.observableArrayList();
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();
        try {
            LinkedList<Ruangan> res = RuanganDAO.getAll();
            RuangList.clear();
            if (res != null) {
                RuangList.addAll(res);
            }
            tvDaftarRuangan.refresh();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Gagal memuat data ruangan: " + e.getMessage());
        }
        String peran = controller.LoginController.user.getPeran().trim();
        if ("mahasiswa".equalsIgnoreCase(peran) || "dosen".equalsIgnoreCase(peran) || "pj_matkul".equalsIgnoreCase(peran)) {
            btnKelolaRuangan.setVisible(false);
            btnKelolaRuangan.setManaged(false);
        }
        
    }    
private static MenuDaftarRuanganController instance;

public MenuDaftarRuanganController() {
    instance = this;
}

public static MenuDaftarRuanganController getInstance() {
    return instance;
}

public void refreshData() {
    try {
        LinkedList<Ruangan> res = RuanganDAO.getAll();
        RuangList.clear();
        RuangList.addAll(res);
        tvDaftarRuangan.refresh();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @FXML
    private void showKelolaRuangan(ActionEvent event) throws IOException {
                    try {
        Ruangan selected = tvDaftarRuangan.getSelectionModel().getSelectedItem();
        URL url = new File("src/main/java/view/kelolaRuangan.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        controller.KelolaRuanganController kelolaCtrl = loader.getController();

        if (selected != null) {
            kelolaCtrl.setDataRuangan(selected);
        }
        Stage newStage = new Stage();
        Controller.PageOpen.setAppIcon(newStage);
        newStage.setTitle("Kelola Pengguna");
        newStage.setScene(new Scene(root));
        newStage.show();

    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Gagal memuat file FXML: " + e.getMessage());
    }
    }

    private void setupTableColumns() {
       colIdRuangan.setCellValueFactory(new PropertyValueFactory<>("id"));
       colNamaRuangan.setCellValueFactory(new PropertyValueFactory<>("nama"));
       colLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasi"));
        
        tvDaftarRuangan.setItems(RuangList);
    }

}
