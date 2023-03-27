
package javafxapplication7;

public class TrafficController extends PoliceMan {
    
    public TrafficController() {
    }

    public TrafficController(Long id, String name, String surname, String birth, String gender, boolean Hadtask, boolean detective, boolean trafficController, boolean simple, int Tasks) {
        super(id, name, surname, birth, gender, Hadtask, detective, trafficController, simple, Tasks);
    }
    
    @Override
    public String toString(){
        String result=" TrafficController "; 
        if(super.getGender().equals("Male")){
        result+=" (Man)";
        }
        else {
        result+=" (Woman) ";
        }
        result+=" - "+super.getName()+" "+ super.getSurname() ;
        
       
        result+=" who ";
        if(super.getHadtask()){ result+=" had task";}
        else{ result+=" had not task";}
        result+="( "+super.getTasks() +" )";
        
        System.out.println(result );
        
    return result;
    }
    
    
    
    @Override
    public String Say(){
        
       String result=" Traffic Controller "; 
        if(super.getGender().equals("Male")){
        result+=" (Man)";
        }
        else {
        result+=" (Woman) ";
        }
        result+=" - "+super.getName()+" "+ super.getSurname() ;
        
       
        super.setHadtask(true);
        result+=",who have just got a task ,";
        super.setTasks(super.getTasks()+1);    
        result+="is doing ";
        if(super.getGender().equals("Male")){
        result+=" his ";
        }
        else {
        result+=" her ";
        }
        result+=super.getTasks()+"th task";
        
        return(result );
        
    }

    
}
