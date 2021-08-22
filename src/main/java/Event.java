import java.text.SimpleDateFormat;

public class Event extends Task{

    public Event(String actionName, boolean compleated, String date, String type){
        super(actionName, compleated, type, date);
    }


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
