package util;

 
import java.time.LocalDateTime; 

public class CurrentDateTime {
    public static LocalDateTime dateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now;      
    }  
}
