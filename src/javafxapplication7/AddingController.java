package javafxapplication7;

import animations.Shake;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class AddingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;

    @FXML
    private CheckBox IsMan;

    @FXML
    private CheckBox IsWoman;

    @FXML
    private TextField NameField;

    @FXML
    private TextField SurnameField;

    @FXML
    private DatePicker BirthField;

    @FXML
    private CheckBox IsDetect;

    @FXML
    private CheckBox IsTC;

    @FXML
    private CheckBox IsOfficer;

    @FXML
    private Button exitButton;

    @FXML
    private Button AddButton;
    
    @FXML
    private TextArea Area;
    
    @FXML
    private Button InternButton;
    
    @FXML
    private Label LabelSkip;
    
    private Connection connection=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    static  PoliceMan pm=new PoliceMan();
    
    @FXML
    void initialize() {
    	try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/policemen?useUnicode=true&serverTimezone=UTC", "root", "");

        }catch (Exception ex) {
          ex.printStackTrace();
        }
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
        
                     
                        
                        
                        
        
         AddButton.setOnAction(event->{
         
             System.out.println("buttonAdd");
             
            String name=NameField.getText().trim(); 
            String surname=SurnameField.getText().trim();
            String bi=(String)BirthField.getEditor().getText();
            String birth="";

                    for(int j=6;j<=9;j++){
                        birth+=bi.charAt(j);
                        }
                        birth+="-";
                    for(int j=3;j<=4;j++){
                        birth+=bi.charAt(j);
                    }
                        birth+="-";
                    for(int j=0;j<=1;j++){
                        birth+=bi.charAt(j);
                    }
            String gender="";
                    int g=0;
                    if(IsMan.isSelected()){
                    gender="Male";
                    g++;
                    }
                    if(IsWoman.isSelected()){
                    gender="Female";
                    g++;
                    }
            Boolean HadTask=false;
                    
            boolean detective=false;
            boolean trafficCont=false;
            boolean officer=false;
            
                    if(IsTC.isSelected()){
                    trafficCont=true;
                    }
                    if(IsDetect.isSelected()){
                    detective=true;
                    }
                    if(IsOfficer.isSelected()){
                    officer=true;
                    }
            int Tasks=0;
            int f=0,t=0;
            if(detective==false){
            f++;
            }
            else 
                t++;
            if(trafficCont==false){
            f++;
            }
            else
                t++;
            if(officer==false){
            f++;
            }
            else
                t++;
            
          if(name.length()>=1 && surname.length()>=1 && birth.length()>=1 && g==1  && t==1 && f==2){
             
           //PoliceMan man=new PoliceMan(null,name,surname,birth, gender,HadTask,detective,trafficCont,officer,Tasks); 
           PoliceMan man=new PoliceMan(null,name,surname,birth, gender,HadTask,detective,trafficCont,officer,Tasks); 
           
           
           
                AddingP(man);
                InternButton.setVisible(true);
                LabelSkip.setVisible(true);
                Area.setVisible(true);
                Area.setText("Chef Wiggum:\n THANK YOU!! \n A Trainee \n"+ man.getName()+ " "+ man.getSurname()+"\n is added"+"\n If you don't \n want to intern \n we can give you\n a task to skip \n the intern ");
                }else{
                        Area.setVisible(true);
                        if(!(name.length()>=1)){
                        
                        Area.setText("Chef Wiggum(Server):\n YOU MUST \n INSERT NAME");
                        }
                        else if(!(surname.length()>=1)){
                        Area.setText("Chef Wiggum(Server):\n YOU MUST \n INSERT SURNAME");
                        }
                        else if(!(birth.length()>=1)){
                        Area.setText("Chef Wiggum(Server):\n YOU MUST \n INSERT BIRTH");
                        }
                        else if(g!=1){
                            if(g==0){
                            Area.setText("Chef Wiggum(Server):\n YOU MUST \n CHOOSE GENDER");
                            }
                            if(g==2){
                            Area.setText("Chef Wiggum(Server):\n YOU MUST \n CHOOSET JUST \n ONE GENDER");
                            }
                        }
                        else if(t!=1 && f!=2){
                        Area.setText("Chef Wiggum(Server):\n YOU MUST \n CHOOSE 1 POSITION ");
                        
                        }
                        
                        
                  }
          
                 
                  
                       
                            
                                  
            });
            
          InternButton.setOnAction(eventt->{
                      System.out.println("you enter to the InternSkip");
                            exitButton.getScene().getWindow().hide();
                            FXMLLoader loader=new FXMLLoader();
                             loader.setLocation(getClass().getResource("/javafxapplication7/SkipIntern.fxml"));

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

            /* 
            lissst
            if(detective==true){
              m=new Detective(null,name,surname,birth, gender,HadTask,detective,trafficCont,officer,Tasks); 
              }
              else if(trafficCont==true){
              m=new TrafficController(null,name,surname,birth, gender,HadTask,detective,trafficCont,officer,Tasks);
              }
              else if()*/
        
            
        
        
    
     
           public  void AddingP(PoliceMan man){
               pm=man;
                System.out.println("adP");
                try{

                    PreparedStatement st =
                    connection.prepareStatement("INSERT INTO trainees (id, name, surname, birth,Gender,HadTask,Detective,TrafficController,OfficerOfPolice,HowManyTasks)"
                            + " VALUES (NULL,  ?,  ?, ?, ?, ?, ?, ?, ?, ?) ");

            st.setString(1, man.getName());
            st.setString(2, man.getSurname());
            st.setString(3, man.getBirth());//birth
            st.setString(4,man.getGender());
            st.setBoolean(5, man.getHadtask());
            st.setBoolean(6, man.isDetective());
            st.setBoolean(7, man.isTrafficController());
            st.setBoolean(8, man.isSimple());
            st.setInt(9, man.getTasks());
                
            
            st.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
  }
        
        
        
        
        
    }
    

    
    
    

