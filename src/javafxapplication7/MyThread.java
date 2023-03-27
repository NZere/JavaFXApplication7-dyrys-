
package javafxapplication7;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread{
    
   
        
        
    @Override
    public void run(){
    
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
