package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.text.View;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author loq
 */
public class PageOpen {
    private Pane view;
    
    public Pane getPage (String fileName) throws FileNotFoundException, IOException{
    
        try {
            String path = "/View/" + fileName + ".fxml";
            URL url = getClass().getResource(path);
            if(url == null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            view = new FXMLLoader().load(url);
        } catch (Exception e){
                System.out.println("No page " + fileName + " please check fxmlloader");
                e.printStackTrace();
            }
        return view;
    }
    
    public static void setAppIcon(Stage stage) {
    try {
        File file = new File("src/main/java/Images/LogoCmanage.png");
        if (file.exists()) {
            javafx.scene.image.Image icon = new javafx.scene.image.Image(
                file.toURI().toString(),512,512,true,true);
            stage.getIcons().clear(); 
            stage.getIcons().add(icon);
        } else {
            System.out.println("Gim, File logo tidak ditemukan di path: " + file.getAbsolutePath());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
