/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import DAO.JadwalDAO;
import DAO.RuanganDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author loq
 */
public class MenuDashboarController implements Initializable {

    @FXML
    private AnchorPane header;
    @FXML
    private Label lblGreeting;
    @FXML
    private Label lblRuanganTersedia;
    @FXML
    private Label lblTotalRuangan;
    @FXML
    private BarChart<String, Number> barChartStatistik;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Label lblJumlahPengguna; // Tambahkan juga untuk sumbu Y jika perlu
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (controller.LoginController.user != null) {
            lblGreeting.setText("Hi, " + controller.LoginController.user.getNama());
        }
        int total = RuanganDAO.getTotalRuangan();
        lblTotalRuangan.setText(String.valueOf(total));
        int terpakai = RuanganDAO.getRuanganTerpakai();
        int tersedia = total - terpakai;
        lblRuanganTersedia.setText(tersedia + "/" + total);
        int totalUser = DAO.UserDAO.getTotalUser();
        lblJumlahPengguna.setText(String.valueOf(totalUser));
        
        tampilkanGrafik();
    }   
    
    private void tampilkanGrafik() {
        Map<String, Integer> stats = JadwalDAO.getStatistikRuanganBulanan();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Pemakaian Ruangan");

        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChartStatistik.getData().clear();
        barChartStatistik.getData().add(series);
        if (xAxis != null) {
            xAxis.setTickLabelRotation(45); 
        }
    }
}