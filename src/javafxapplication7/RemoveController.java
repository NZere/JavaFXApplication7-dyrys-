package javafxapplication7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RemoveController {

    
   

    @FXML
    private Button exit;
     @FXML
    private Button RemoveButton;

    @FXML
    private TableView<PoliceMan> tablePerson;

    @FXML
    private TableColumn<PoliceMan, Long> idTable;

    @FXML
    private TableColumn<PoliceMan, String> nameTable;

    @FXML
    private TableColumn<PoliceMan, String> surTable;

    @FXML
    private TableColumn<PoliceMan, String> birthTable;

    @FXML
    private TableColumn<PoliceMan, String> genTable;
    
    @FXML
    private TableColumn<PoliceMan, ?> TasksTable;

    @FXML
    private TableColumn<PoliceMan, Boolean> HadTaskTable;

    @FXML
    private TableColumn<PoliceMan, Integer> CountTable;

    @FXML
    private TableColumn<PoliceMan, ?> PosTable;

    @FXML
    private TableColumn<PoliceMan, Boolean> detectTable;

    @FXML
    private TableColumn<PoliceMan, Boolean> TcTable;

    @FXML
    private TableColumn<PoliceMan, Boolean> offTable;
    

    
    
    private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private ObservableList<PoliceMan>data=FXCollections.observableArrayList();
    ArrayList<PoliceMan>pp=new ArrayList<>();
    @FXML
            
              
    void initialize()  {
        
        
       try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/policemen?useUnicode=true&serverTimezone=UTC", "root", "");

                }catch (Exception ex) {
                  ex.printStackTrace();
                }
            
            
        
        exit.setOnAction(event->{
            
            
            System.out.println("you enter to the Police Station");
            exit.getScene().getWindow().hide();
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
      
        setCellTable();
        loadDataFromDatabase();     
        
       
            
                
            
            
            
            
}
     @FXML
    private void deleteRow(ActionEvent event){
        
        
        PoliceMan m= tablePerson.getSelectionModel().getSelectedItem();
        String query="delete From polisiya Where  id =?";
        
        
        try {
            pst=con.prepareStatement(query);
            
            pst.setLong(1, m.getId());
            
            pst.executeUpdate();
            
            
            tablePerson.getItems().clear();
            
            setCellTable();
             loadDataFromDatabase();
               
            
        } catch (SQLException ex) {
//            Logger.getLogger(RemoveController.class.getName()).log(Level.SEVERE, null, ex);
System.out.println("kate");
        }
        
        
        
        
        System.out.println(query);
        
    } 
    
    private void setCellTable(){
        idTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        surTable.setCellValueFactory(new PropertyValueFactory<>("surname"));
        birthTable.setCellValueFactory(new PropertyValueFactory<>("birth"));
        genTable.setCellValueFactory(new PropertyValueFactory<>("gender"));
        HadTaskTable.setCellValueFactory(new PropertyValueFactory<>("Hadtask"));
        CountTable.setCellValueFactory(new PropertyValueFactory<>("Tasks"));
        detectTable.setCellValueFactory(new PropertyValueFactory<>("detective"));
        TcTable.setCellValueFactory(new PropertyValueFactory<>("trafficController"));
        offTable.setCellValueFactory(new PropertyValueFactory<>("simple"));
       // чтобы записать в ячейки Полисманнын элементтерин
        
    }
    private void loadDataFromDatabase(){
        
        try {
            pst=con.prepareStatement("SELECT * FROM polisiya");
            rs=pst.executeQuery();
            while(rs.next()){
                data.add(new PoliceMan(rs.getLong("id"),rs.getString("name"),rs.getString("surname"),rs.getString("birth"),rs.getString("Gender"), rs.getBoolean("HadTask"),rs.getBoolean("Detective"),rs.getBoolean("TrafficController"),rs.getBoolean("OfficerOfPolice"), rs.getInt("HowManyTasks") ) );
                // с базы данных добавляем в лист
                pp.add(new PoliceMan(rs.getLong("id"),rs.getString("name"),rs.getString("surname"),rs.getString("birth"),rs.getString("Gender"), rs.getBoolean("HadTask"),rs.getBoolean("Detective"),rs.getBoolean("TrafficController"),rs.getBoolean("OfficerOfPolice"), rs.getInt("HowManyTasks")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
         tablePerson.setItems(data);
         // лис добавляем в таблицу
    }
       
        
        
    }
    

