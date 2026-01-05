/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Controller.PageOpen;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class SideBarController implements Initializable {

    @FXML
    private BorderPane mainScene;
    @FXML
    private VBox sideBar;
    @FXML
    private ImageView btnHamburger;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnDaftarRuangan;
    @FXML
    private Button btnJadwal;
    @FXML
    private Button btnDaftarPengguna;
    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
@Override
public void initialize(URL url, ResourceBundle rb) {
    try {
        PageOpen object = new PageOpen();
        mainScene.setCenter(object.getPage("MenuDashboard"));
    } catch (Exception e) {
        e.printStackTrace();
    }

    if (controller.LoginController.user != null) {
        String peran = controller.LoginController.user.getPeran().trim();

        if ("mahasiswa".equalsIgnoreCase(peran) || "pj_matkul".equalsIgnoreCase(peran)) {
            btnDaftarPengguna.setVisible(false);
            btnDaftarPengguna.setManaged(false);
        }
    } else {
        System.out.println("User belum login!");
    }
}

@FXML
private void ShowDashboard(ActionEvent event) throws IOException {
    System.out.println("show dashboard");
    PageOpen object = new PageOpen();
    Pane view;
    view = object.getPage("MenuDashboard");
    mainScene.setCenter(view);
}   

    @FXML
    private void ShowDaftarRuangan(ActionEvent event) throws IOException {
        System.out.println("show Daftar Ruangan");
        PageOpen object = new PageOpen();
        Pane view;
        view = object.getPage("MenuDaftarRuangan");
        mainScene.setCenter(view);        
    }

    @FXML
    private void ShowJadwal(ActionEvent event) throws IOException {
        System.out.println("show Jadwal");
        PageOpen object = new PageOpen();
        Pane view;
        view = object.getPage("MenuJadwal");
        mainScene.setCenter(view);
    }

    @FXML
    private void ShowDaftarPengguna(ActionEvent event)throws IOException {
        System.out.println("show Daftar Pengguna");
        PageOpen object = new PageOpen();
        Pane view;
        view = object.getPage("MenuDaftarPengguna");
        mainScene.setCenter(view);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        URL url = new File("src/main/java/view/Login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(false);
        stage.setMaximized(true);
    }
   
}
