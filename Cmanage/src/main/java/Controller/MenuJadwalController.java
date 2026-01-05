package controller;

import DAO.JadwalDAO;
import DAO.RuanganDAO;
import Model.Jadwal;
import Model.Ruangan;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class MenuJadwalController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private AnchorPane header;
    @FXML
    private TableView<ObservableList<String>> tvJadwal;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnBooking;

    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        try {
        setupDynamicTable();
        datePicker.setValue(LocalDate.now());
        loadJadwalMatrix(LocalDate.now());
        datePicker.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            if (date != null) {
                loadJadwalMatrix(date); 
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
    }
        if (controller.LoginController.user != null) {
        String peran = controller.LoginController.user.getPeran().trim();

        if ("mahasiswa".equalsIgnoreCase(peran) || "dosen".equalsIgnoreCase(peran)) {
            btnBooking.setVisible(false);
            btnBooking.setManaged(false);
        }
    } else {
        System.out.println("User belum login!");
    }
        
    } 

    public void setupDynamicTable() {
        tvJadwal.getColumns().clear();
        TableColumn<ObservableList<String>, String> colJam = new TableColumn<>("Jam");
        colJam.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        tvJadwal.getColumns().add(colJam);

        LinkedList<Ruangan> listRuangan = RuanganDAO.getAll(); 
        if (listRuangan == null) return;
        
        int index = 1;
        for (Ruangan r : listRuangan) {
            final int colIndex = index;
            TableColumn<ObservableList<String>, String> colRuang = new TableColumn<>(r.getNama());
            
            colRuang.setCellValueFactory(data -> {
                if (data.getValue().size() > colIndex) {
                    return new SimpleStringProperty(data.getValue().get(colIndex));
                }
                return new SimpleStringProperty("");
            });

colRuang.setCellFactory(column -> new TableCell<ObservableList<String>, String>() {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        
        if (item == null || empty || item.isEmpty()) {
            setText(null);
            setStyle("-fx-border-color: #e2e2e2; -fx-border-width: 0.1;");
            setOnMouseClicked(null);
        } else {
            int currentRow = getIndex();
            boolean isFirstRow = true;
            
            if (currentRow > 0) {
                String itemAbove = getTableView().getItems().get(currentRow - 1).get(getTableColumn().getTableView().getColumns().indexOf(getTableColumn()));
                if (item.equals(itemAbove)) {
                    isFirstRow = false;
                }
            }
            setText(isFirstRow ? item : ""); 
            
            setStyle("-fx-background-color: #2C8E8E; " +
                     "-fx-text-fill: #333333; " +
                     "-fx-alignment: center; " +
                     "-fx-font-weight: bold; ");
            setOnMouseClicked(event -> {
                if (event.getClickCount() == 1) { 
                    showDetailPopup(item);
                }
            });
        }
    }
});
            
            tvJadwal.getColumns().add(colRuang);
            index++;
        }
    }

    public void loadJadwalMatrix(LocalDate filterDate) {
    ObservableList<ObservableList<String>> dataMatrix = FXCollections.observableArrayList();

    String[] daftarJam = {"06:00","06:30","07:00","07:30","08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30", 
                            "12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00",
                            "17:30","18:00","18:30", "19:00","19:30", "20:00"};
    
    LinkedList<Ruangan> listRuangan = RuanganDAO.getAll();
    LinkedList<Jadwal> listJadwal = JadwalDAO.getByDate(filterDate);

    if (listRuangan == null || listJadwal == null) return;

    for (String jamSesi : daftarJam) {
        ObservableList<String> row = FXCollections.observableArrayList();
        row.add(jamSesi);

        for (Ruangan r : listRuangan) {
            String isiSel = ""; 
            
            for (Jadwal j : listJadwal) {
                if (j.getIdRuangan().equals(r.getId())) {
                    String[] parts = j.getJam().split(" - ");
                    int mulai = Integer.parseInt(parts[0].replace(":", "")); 
                    int selesai = Integer.parseInt(parts[1].replace(":", ""));
                    int sesiSekarang = Integer.parseInt(jamSesi.replace(":", ""));

                    if (sesiSekarang >= mulai && sesiSekarang < selesai) {
                        isiSel = j.getNama_matkul().trim(); 
                        break; 
                        
                    }
                }
            }
            row.add(isiSel);
        }
        dataMatrix.add(row);
    }
    tvJadwal.setItems(dataMatrix);
}

private void showDetailPopup(String namaMatkul) {
    LocalDate tgl = datePicker.getValue();
    Jadwal detail = JadwalDAO.getDetail(namaMatkul, tgl);

    if (detail != null) {
        try {
            URL url = new File("src/main/java/view/DetailKelasFinal.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            DetailKelasController controller = loader.getController();
            controller.setData(detail);
            controller.setRefreshCallback(() -> {
                loadJadwalMatrix(tgl);
            });

            Stage stage = new Stage();
            Controller.PageOpen.setAppIcon(stage);
            stage.setTitle("Detail Jadwal");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) { e.printStackTrace(); }
    }
}

    @FXML
    private void pickdate(ActionEvent event) {
    }

  @FXML
private void showKelolaJadwal(ActionEvent event) {
    try {
        URL url = new File("src/main/java/view/KelolaJadwal.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        KelolaJadwalController controller = loader.getController();
        controller.setRefreshCallback(() -> {
            loadJadwalMatrix(datePicker.getValue());
        });
        Stage newStage = new Stage();
        Controller.PageOpen.setAppIcon(newStage);
        newStage.setTitle("Booking Kelas");
        newStage.setScene(new Scene(root));
        newStage.initModality(Modality.APPLICATION_MODAL); 
        newStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }
