
package javafxapplication7;


public class PoliceMan {
    private Long id;
    private String name;
    private String surname;
    private String gender;
    private String birth;
    private boolean Hadtask;
    private boolean detective;
    private boolean trafficController;
    private boolean simple;
    private int Tasks;

  

    public PoliceMan() {
    }

    public PoliceMan(Long id, String name, String surname,String birth, String gender, boolean Hadtask, boolean detective, boolean trafficController, boolean simple, int Tasks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth=birth;
        this.gender=gender;
        this.Hadtask = Hadtask;
        this.detective = detective;
        this.trafficController = trafficController;
        this.simple = simple;
       this.Tasks=Tasks;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    

    public boolean isDetective() {
        return detective;
    }

    public void setDetective(boolean detective) {
        this.detective = detective;
    }

    public boolean isTrafficController() {
        return trafficController;
    }

    public void setTrafficController(boolean trafficController) {
        this.trafficController = trafficController;
    }

    public boolean isSimple() {
        return simple;
    }

    public void setSimple(boolean simple) {
        this.simple = simple;
    }

    

    public boolean getHadtask() {
        return Hadtask;
    }

    public void setHadtask(boolean Hadtask) {
        this.Hadtask = Hadtask;
    }
      public int getTasks() {
        return Tasks;
    }

    public  void setTasks(int Tasks) {
        this.Tasks = Tasks;
    } 
    public String Say(){
        return("Policeman");
    }
    public String toString(){
        
        
        
        String result=" "; 
        if(gender.equals("Male")){
        result+=" PoliceMan";
        }
        else {
        result+=" PolicWoman ";
        }
        result+=" - "+name+ surname +"with position -";
        if(detective){result+=" Detective";}
        if(trafficController){result+=" traffic Controller ";}
        if(simple){result+=" Officer of Police  ";}
        result+=" who ";
        if(Hadtask){ result+=" had task";}
        else{ result+=" had not task";}
        result+="( "+Tasks +" )";
        
    return result;
    }
    
    
    
    
    
}
