package javafxapplication7;


import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.*;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView Znachok;

    
    @FXML
    private Button buttonImage;
    
    @FXML
    void initialize() {
        
        buttonImage.setOnAction(event->{
            System.out.println("you enter to the Police Station");
            buttonImage.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            
            loader.setLocation(getClass().getResource("/javafxapplication7/PoliceStation.fxml"));
            
            try{loader.load();
            }catch(IOException e){
                
            e.printStackTrace();
            }
            Parent root=loader.getRoot();
            
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            
            stage.show();
            
        });
    }
    public void openNewScene(String window ){
    
    
    
    }
}

