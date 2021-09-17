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
    public Event(String actionName, boolean compleated, String date, String type){
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
        return "[E] " + check + " " + this.actionName + "(at: " + dateFormat.format(date) + ")";
    }
}
