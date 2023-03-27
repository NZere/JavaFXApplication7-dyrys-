package javafxapplication7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CallController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

    @FXML
    private TextArea ConArea;

    @FXML
    private TextArea BotArea;

    @FXML
    private Button C5;

    @FXML
    private Button C7;

    @FXML
    private Button C8;

    @FXML
    private Button C9;

    @FXML
    private Button C6;

    @FXML
    private Button C4;

    @FXML
    private Button C1;

    @FXML
    private Button C2;

    @FXML
    private Button C3;

   
    @FXML
    private Button OK;

    @FXML
    private Button C0;
    
   
    ObjectOutputStream outStream;
    ObjectInputStream inStream ;
    
    
  
    
    
    @FXML
    void initialize() {
        exitButton.setOnAction(event->{
            
            
            System.out.println("you enter to the Police Station");
            exitButton.getScene().getWindow().hide();
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
        
			
            try {
            Socket socket = new Socket("127.0.0.1", 2000);
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
               
               BotArea.setText((String)inStream.readObject());
               C1.setOnAction(event1->{
                   ConArea.setText("1");
               });
                 C2.setOnAction(event1->{
                   ConArea.setText("2");
               });
               C3.setOnAction(event1->{
                   ConArea.setText("3");
               });
               
               C4.setOnAction(event1->{
                   ConArea.setText("4");
               });
               C5.setOnAction(event1->{
                   ConArea.setText("5");
               }); 
               C6.setOnAction(event1->{
                   ConArea.setText("6");
               });
               
               C7.setOnAction(event1->{
                   ConArea.setText("7");
               });
               C8.setOnAction(event1->{
                   ConArea.setText("8");
               });
               C9.setOnAction(event1->{
                   ConArea.setText("9");
               });
                   OK.setOnAction(event2->{
                       String s=ConArea.getText();
                       
                       try {
                           System.out.println("bnm");
                           outStream.writeObject(s);
                            } catch (Exception ex) {
                           Logger.getLogger(CallController.class.getName()).log(Level.SEVERE, null, ex);
                       }
                           });
                            
                           System.out.println((String)inStream.readObject());
                           System.out.println((String)inStream.readObject());
                           
                      
                       
                             
                   
               
               }
        catch(Exception e){
            System.out.println("exy");
        }    
    }
               
        
}
