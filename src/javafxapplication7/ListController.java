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
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListController {

    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;

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
    
   

    @FXML
    private CheckBox IsFirst;

    @FXML
    private CheckBox IsLast;
    @FXML
    private CheckBox isDet;

    @FXML
    private CheckBox isTC;

    @FXML
    private CheckBox isOFF;

    @FXML
    private CheckBox ToSort;

    @FXML
    private CheckBox ToSave;
    
    @FXML
    private Button goButton;
       
    @FXML
    private TextArea Area;
    
    @FXML
    private TextArea FileArea;
    
    @FXML
    private CheckBox isWoman;

    @FXML
    private CheckBox isMan;
    
    
    private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private ObservableList<PoliceMan>data=FXCollections.observableArrayList();
    ArrayList<PoliceMan>pp=new ArrayList<>();
    @FXML
            
              
    void initialize() throws IOException {
        
        
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
      String query="SELECT * FROM polisiya ";
      
        setCellTable();
        loadDataFromDatabase(query);     
        
       
            goButton.setOnAction(event->{
                Area.setVisible(false);
                FileArea.setVisible(false);
                
                String queryA=query;
                
                if(isDet.isSelected()){
                    queryA+=" WHERE (   ( Detective = true  ";
                    
                }
                if(isTC.isSelected()){
                    if(isDet.isSelected()){
                        queryA+=" || TrafficController = true";
                    }
                    else{
                        queryA+="WHERE (  ( TrafficController = true    ";
                    }
                }
                if(isOFF.isSelected()){
                    int a=0;
                    if(   isDet.isSelected() ){
                       a++;
                    }
                    if ( isTC.isSelected() ){
                        a++;
                    }
                    if(a>=1){
                    queryA+=" || OfficerOfPolice = true ";
                    }
                    
                    if(a==0 ){
                         queryA+="WHERE(  ( OfficerOfPolice = true   ";
                    }
                    //queryA+=")";
                    
                }
                queryA+="   )";
                
                
                
                //queryA+=" )";
                if(isWoman.isSelected()){
                queryA+= " AND ( Gender='Female' ";
                }
                if(isMan.isSelected()){
                    if(isWoman.isSelected()){
                    queryA+= " || Gender='Male'";
                    
                    }
                    else{
                        queryA+= " AND (Gender='Male' ";
                        }
                    
                }
                queryA+=" )     )";
                
                
                
                
                if(ToSort.isSelected()){
                    queryA+=" ORDER BY HowManyTasks ";
                }
                
                if(IsFirst.isSelected()&& IsLast.isSelected()){
                    Area.setVisible(true);
                    Area.setText("CHOOSE JUST 1:\n FIRST OR LAST!!!");
                
                }
                else{
                    if(ToSort.isSelected()){
                        if(IsFirst.isSelected()){
                            queryA+=" ,id LIMIT 10 ";
                        }
                        if(IsLast.isSelected()){
                            queryA+=" ,id DESC LIMIT 10 ";
                        }
                    }
                    else{
                        if(IsFirst.isSelected()){
                            queryA+=" ORDER BY id LIMIT 10 ";
                        }
                        if(IsLast.isSelected()){
                            queryA+=" ORDER BY id DESC LIMIT 10 ";
                        }    
                    }
                }
                
                
                
                
                
                
                int d=0;
                if(isDet.isSelected()){
                d++;
                }
                if(isTC.isSelected()){
                
                d++;
                }
                if(isOFF.isSelected()){
                d++;
                }
                if(d==0){
                 queryA="";
                }
                
                if(ToSave.isSelected()){
                    try {
                        
                        WriteToFile(getWorkers(queryA));
                    } catch (IOException ex) {
                        System.out.println("kate file");}
                    FileArea.setVisible(true);
                    FileArea.setText("File done 'output.txt'");
                }
                
                
                if(!queryA.equals("")){
                    tablePerson.getItems().clear();
                setCellTable();
                loadDataFromDatabase(queryA); 
                }
                
                if(d==0){
                 tablePerson.getItems().clear();
                }
            
            System.out.println(queryA);
            });
        
        
        
        
        
        
        
        
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
    private void loadDataFromDatabase(String query){
        
        try {
            pst=con.prepareStatement(query);
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
       public void WriteToFile(ArrayList<PoliceMan> work) throws IOException{
            BufferedWriter writer=new BufferedWriter(new FileWriter("output.txt"));
            for(int i=0;i<work.size();i++){
                System.out.println(work.get(i).toString());
            writer.write(work.get(i).toString()+"\r\n");
            }
            writer.close();
       
       } 
        private  ArrayList<PoliceMan> getWorkers(String query){

        ArrayList<PoliceMan> work = new ArrayList<>();

        try{

            PreparedStatement st =
                    con.prepareStatement(query);
            System.out.println("query in st "+ query);
            ResultSet resultSet = st.executeQuery();

            while(resultSet.next()){

                
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String birth = resultSet.getString("birth");
                String gender = resultSet.getString("Gender");
                boolean HadTask= resultSet.getBoolean("HadTask");
                boolean detect= resultSet.getBoolean("Detective");
                boolean tc= resultSet.getBoolean("TrafficController");
                boolean off= resultSet.getBoolean("OfficerOfPolice");
                int Tasks=resultSet.getInt("HowManyTasks");
                

                //work.add(new PoliceMan(null, name, surname,birth, gender,HadTask,detect,tc,off,Tasks));
                //inherit
                if(detect=true){
                    PoliceMan p1=new Detective(null, name, surname,birth, gender,HadTask,detect,tc,off,Tasks);
                work.add(p1);
                }
                if(tc=true){
                    PoliceMan p2=new TrafficController(null, name, surname,birth, gender,HadTask,detect,tc,off,Tasks);
                work.add(p2);
                 }
                if(off=true){
                    PoliceMan p3=new Simple(null, name, surname,birth, gender,HadTask,detect,tc,off,Tasks);
                work.add(p3);
                }
                
                }      
            System.out.println(work);

        }catch (Exception e){
            e.printStackTrace();
        }

        return work;
    }
        
        
        
        
        
        
    }
    

