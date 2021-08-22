package Duke;
import java.text.SimpleDateFormat;

 /**
  * <pre>
  * Child class Deadline from Task Class
  * </pre>
  */
public class Deadline extends Task{


    /**
     * Defult constructor,
     */
    public Deadline(String actionName, boolean compleated, String date, String type) {
        super(actionName, compleated, type, date);
    }
    
    /**
     * Defult to String.
     * @return String representation of task
     */    
    @Override
    public String toString(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm");
        
        String check = "[ ]";
        if (this.compleated){
            check = "[X]";
        }
        return "[D] " + check + " " + this.actionName + "(by: " + dateFormat.format(date) + ")";
    }
}


