package Controller;

import DAO.BaseDAO;
import DAO.UserDAO;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class MenuDaftarPenggunaController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane header;
    @FXML
    private TableView<User> tvDaftarPengguna;
    @FXML
    private TableColumn<User, String> colNamaLengkap;
    @FXML
    private TableColumn<User, String> colPassword;
    @FXML
    private TableColumn<User, String> colNomorID;
    @FXML
    private TableColumn<User, String> colNomorInduk;
    @FXML
    private TableColumn<User, String> colPeran;
    
    private ObservableList<User> UserList = FXCollections.observableArrayList();
    @FXML
    private Button btnkelolaPengguna;

    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();
        try {
            LinkedList<User> res = UserDAO.getAll(controller.LoginController.user);
            UserList.clear();
            if (res != null) {
                UserList.addAll(res);
            }

            tvDaftarPengguna.refresh();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Gagal memuat data pengguna: " + e.getMessage());
        }
        String peranLogin = controller.LoginController.user.getPeran();

    if ("dosen".equalsIgnoreCase(peranLogin)) {
        ObservableList<User> dataInput = FXCollections.observableArrayList(UserDAO.getLimitedUsers());
        tvDaftarPengguna.setItems(dataInput);
    } 
    }
    
private static MenuDaftarPenggunaController instance;

public MenuDaftarPenggunaController() {
    instance = this;
}

public static MenuDaftarPenggunaController getInstance() {
    return instance;
}

public void refreshData() {
    try {
        LinkedList<User> res = UserDAO.getAll(controller.LoginController.user);
        UserList.clear();
        UserList.addAll(res);
        tvDaftarPengguna.refresh();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void setupTableColumns() {

        colNomorID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNamaLengkap.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colNomorInduk.setCellValueFactory(new PropertyValueFactory<>("nomor_induk"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPeran.setCellValueFactory(new PropertyValueFactory<>("peran"));
        
        tvDaftarPengguna.setItems(UserList);
    }

@FXML
private void showkelolaPengguna(ActionEvent event) {
    try {
        User selected = tvDaftarPengguna.getSelectionModel().getSelectedItem();
        URL url = new File("src/main/java/view/kelolaPengguna.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        controller.KelolaPenggunaController kelolaCtrl = loader.getController();

        if (selected != null) {
            kelolaCtrl.setDataUser(selected);
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

    
}