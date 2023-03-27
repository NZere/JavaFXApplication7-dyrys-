package javafxapplication7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import static javafxapplication7.AddingController.pm;

public class SkipInternController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;

    @FXML
    private Button exitButton;

    @FXML
    private TextArea Area;

    @FXML
    private Button InternButton;

    @FXML
    private Label LabelSkip;

    @FXML
    private TextArea TextArea;

    @FXML
    private RadioButton T;

    @FXML
    private RadioButton K;

    @FXML
    private RadioButton M;

    @FXML
    private RadioButton N;

    @FXML
    private Button OkButton;
    
    
    private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    
   PoliceMan pm=AddingController.pm;
           
    @FXML
    void initialize()  {
        
        
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/policemen?useUnicode=true&serverTimezone=UTC", "root", "");

                }catch (Exception exp) {
                  exp.printStackTrace();
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
        
        
        
         BufferedReader reader;
         String s,ss="";
         
        try {
            reader = new BufferedReader(new FileReader("Zadanie.txt"));
            
        while((s=reader.readLine())!=null){
         ss+="\r\n"+s;
        }
        TextArea.setText(ss);
        } catch (Exception ey) {
            System.out.println("kate tut");
            //Logger.getLogger(SkipInternController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        OkButton.setOnAction(event->{
            int a=0;
                if(T.isSelected()){
                    a++;
                }
                if(K.isSelected()){
                    a++;
                }
                if(M.isSelected()){
                    a++;
                }
               if(N.isSelected()){
                    a++;
                }
             if(a>1){
             TextArea.setText("Chef Wiggum:\n YOU MUST \n CHOOSE JUST 1 ");
             
             }
             if(a==0){
             TextArea.setText("Chef Wiggum:\n YOU MUST CHOOSE  ");
             }
             if(a==1 && !N.isSelected()){
               TextArea.setText("Chef Wiggum:\n Oh Sorry you CAN'T Skip an Intern  ");  
             }
             if(a==1 && N.isSelected()){
                 
                 String p="";
                 if(pm.isDetective()){
                 p="DETECTIVE";
                 }
                 
                 if(pm.isTrafficController()){
                 p="TRAFFIC CONTROLLER";
                 }
                 if(pm.isSimple()){
                 p="OFFICER OF POLICE";
                 }
               TextArea.setText("Chef Wiggum:\n WEEEELLLL DOOONE!!!! \n Congratulations! \n now you are not a trainee \n you are a "+ p );/////////////////////
               
               
               
          String queryDel="   delete From trainees Where  `trainees`.`name`='"+pm.getName()+"'   ";
               /*String queryDel=" delete From trainees Where ( `trainees`.`name`='"+pm.getName()+"'AND `trainees`.`surname`='"+pm.getSurname()+
                       "'AND `trainees`.`birth`='"+pm.getBirth()+  "'AND `trainees`.`Gender`='"  +pm.getGender()+ "' ) " ;
        */
                 System.out.println(queryDel);
        try {
            pst=con.prepareStatement(queryDel);
            System.out.println("tut norm");
           pst.executeUpdate();
             System.out.println("executy del");
        } catch (SQLException ex) {
            System.out.println("Kate sql");
            //Logger.getLogger(RemoveController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(queryDel);
        
                System.out.println("adP");
                try{

                    PreparedStatement st =
                    con.prepareStatement("INSERT INTO polisiya (id, name, surname, birth,Gender,HadTask,Detective,TrafficController,OfficerOfPolice,HowManyTasks)"
                            + " VALUES (NULL,  ?,  ?, ?, ?, ?, ?, ?, ?, ?) ");

            st.setString(1, pm.getName());
            st.setString(2, pm.getSurname());
            st.setString(3, pm.getBirth());//birth
            st.setString(4,pm.getGender());
            st.setBoolean(5, pm.getHadtask());
            st.setBoolean(6, pm.isDetective());
            st.setBoolean(7, pm.isTrafficController());
            st.setBoolean(8, pm.isSimple());
            st.setInt(9, pm.getTasks());
                
            
            st.executeUpdate();       
                    System.out.println("execute add");
        
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
        
        
        
        
        
               /*
               
               peredachamen
               
               String query="UPDATE `trainees` SET `HowManyTasks` = '1' WHERE ( `trainees`.`name`='"+pm.getName()+"'AND `trainees`.`surname`='"+pm.getSurname()+"'AND `trainees`.`birth`='"+pm.getBirth()+"'AND `trainees`.`Gender`='"+pm.getGender()+"' )" ;
               try{
               
               Class.forName("com.mysql.cj.jdbc.Driver");
               con= DriverManager.getConnection("jdbc:mysql://localhost:3306/policemen?useUnicode=true&serverTimezone=UTC", "root", "");
               pst=con.prepareStatement(query);
               System.out.println(query); 
               
               
               
               
               
               pst.executeUpdate();
               
               
               
               }catch (Exception ex) {
               ex.printStackTrace();
               }
               
               
               */
            
               
               
               
             }
             
             
             
             
        
        
        });
    }
     
    
    static PoliceMan ppp=new PoliceMan(); 
    
    
    
     @FXML
    public void Skip(Long id) throws Exception{
   
        
        
        
        
        
        
    
    
    }
    
    
    
}
