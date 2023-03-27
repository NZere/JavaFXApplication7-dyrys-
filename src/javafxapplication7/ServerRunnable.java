
package javafxapplication7;


import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServerRunnable extends Thread{
        private static Connection connection;
	private Socket socket;

	public ServerRunnable(Socket socket){
		this.socket = socket;
	}
        private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private ObservableList<PoliceMan>data=FXCollections.observableArrayList();
    ArrayList<PoliceMan>pp=new ArrayList<>();
    ArrayList<PoliceMan>tt=new ArrayList<>();
    ArrayList<PoliceMan>nuzhno=new ArrayList<>();
     String nuzhnoS="";   
        
        @Override
	public void run(){
          try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/policemen?useUnicode=true&serverTimezone=UTC", "root", "");

                }catch (Exception ex) {
                  ex.printStackTrace();
                }
            
            
        try {
                ObjectOutputStream outStream
                                = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inStream = 
				new ObjectInputStream(socket.getInputStream());
            
		outStream.writeObject("Hello!MY name is BOT! "
                        + "\n Call '1' If you are robbed "
                        + "\n Call '2' If you are in an Car accident"
                        + "\n Call '3' If you are a witness of ROBBERING "
                        + "\n Call '4' If you are a witness of MUREDERING and report a murder "
                        + "\n Call '5' If you are a witnesss of VANDALISM"
                        + "\n Call '6' If you want to write a Complaint"
                        + "\n Call '7' If you were kidnapped "
                        + "\n Call '8' If you want to report about kidnapping"
                        + "\n Call '9' If you want to report about ");
                
                if(((String)inStream.readObject()).equals("1")){
                    System.out.println("whooo");
                    
                     String query="SELECT * FROM `polisiya` WHERE (HadTask=false )";
        
            pst=con.prepareStatement(query);
            
                    System.out.println("there");
            rs=pst.executeQuery();
            while(rs.next()){
                // с базы данных добавляем в лист
                PoliceMan ppm=new Detective(rs.getLong("id"),rs.getString("name"),rs.getString("surname"),rs.getString("birth"),rs.getString("Gender"), rs.getBoolean("HadTask"),rs.getBoolean("C"),rs.getBoolean("TrafficController"),rs.getBoolean("OfficerOfPolice"), rs.getInt("HowManyTasks"));
                pp.add(ppm);
                System.out.println(ppm);
                
            }
            for(int i=0;i<pp.size();i++){
            if(pp.get(i).isDetective()==true){
                nuzhno.add(pp.get(i));
                break;            
            }                
            }
                
                nuzhnoS+= nuzhno.get(0).Say();
                
                
                outStream.writeObject(nuzhnoS);
                
                //Thread.sleep(7000);
                
                nuzhno.get(0).setHadtask(false);
                
                outStream.writeObject("The Robber is Robin Hood");
                
                
                nuzhnoS="";
                
                }
            
			inStream.close();
			outStream.close();
			socket.close();
                                
        
        
                
            
            } catch (Exception ex) {
            }
           
        
        }
        public  void WhoBosPol(){
            String query="SELECT * FROM `polisiya` WHERE (HadTask=false )";
        try{
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next()){
                // с базы данных добавляем в лист
                
                pp.add(new PoliceMan(rs.getLong("id"),rs.getString("name"),rs.getString("surname"),rs.getString("birth"),rs.getString("Gender"), rs.getBoolean("HadTask"),rs.getBoolean("C"),rs.getBoolean("TrafficController"),rs.getBoolean("OfficerOfPolice"), rs.getInt("HowManyTasks")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        }
        
         public void WhoBosTr(){
            String query="SELECT * FROM trainees WHERE ('HadTask'=false) ";
        try{
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next()){
                // с базы данных добавляем в лист
                tt.add(new PoliceMan(rs.getLong("id"),rs.getString("name"),rs.getString("surname"),rs.getString("birth"),rs.getString("Gender"), rs.getBoolean("HadTask"),rs.getBoolean("Detective"),rs.getBoolean("TrafficController"),rs.getBoolean("OfficerOfPolice"), rs.getInt("HowManyTasks")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        }
        
        
        
           
            
        }