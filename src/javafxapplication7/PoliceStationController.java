package javafxapplication7;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PoliceStationController  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonList;

    @FXML
    private Button ButtonCall;

    @FXML
    private Button ButtonRemove;

    @FXML
    private Button ButtonAdd;
    
    @FXML
    private Label L1;

    @FXML
    private Label L2;

    @FXML
    private Label L3;

    @FXML
    private Label L4;

    @FXML
    private Label L5;

    @FXML
    void initialize() {
        
        ButtonList.setOnAction(event->{
            System.out.println("you enter to the List");
            ButtonList.getScene().getWindow().hide();
            
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication7/List.fxml"));
            try{loader.load();
            }catch(IOException e){
            e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            
        });
        
        ButtonAdd.setOnAction(event->{
            System.out.println("you enter to the Adding");
            ButtonAdd.getScene().getWindow().hide();
            
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication7/Adding1.fxml"));
            try{loader.load();
            }catch(IOException e){
                System.out.println("kate");
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });
        
        ButtonRemove.setOnAction(event->{
            System.out.println("you enter to the Removing");
            ButtonList.getScene().getWindow().hide();
            
            FXMLLoader loader2=new FXMLLoader();
            loader2.setLocation(getClass().getResource("/javafxapplication7/Remove.fxml"));
            try{loader2.load();
            }catch(IOException e){
                System.out.println("kate removing");
                e.printStackTrace();
            }
            Parent root=loader2.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
        });
        
        
        ButtonCall.setOnAction(event->{
            System.out.println("you enter to the Call");
            ButtonList.getScene().getWindow().hide();
            
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication7/Call.fxml"));
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
    
}
