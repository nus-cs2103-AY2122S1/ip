import java.text.SimpleDateFormat;

public class Deadline extends Task{

    public Deadline(String actionName, boolean compleated, String date, String type) {
        super(actionName, compleated, type, date);

    }

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


