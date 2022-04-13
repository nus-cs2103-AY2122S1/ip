package duke;
import java.text.SimpleDateFormat;

 /**
  * <pre>
  * Child class Event from Task Class
  * </pre>
  */
public class Event extends Task{

    /**
     * Defult constructor,
     */
    public Event(String actionName, boolean compleated, String date, String type, Priority p){
        super(actionName, compleated, type, date, p);
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
        return "[E] " + check + " " + this.actionName + "(at: " + dateFormat.format(date) + ")"  + "#" + this.priority.toString();
    }
}
