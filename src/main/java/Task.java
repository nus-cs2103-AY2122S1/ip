import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Task{
    String actionName;
    boolean compleated;
    String type;
    Date date;

    public Task(String actionName, boolean compleated, String type, String date){
        this.actionName = actionName;
        this.compleated = compleated;
        this.type = type;
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    } 

    public String toString(){
        String check = "[ ]";
        if (this.compleated){
            check = "[X]";
        }
        return check + " " + this.actionName;
    }

    public void doneTask(){
        this.compleated = true;
    }

    public String getActionName(){
        return this.actionName;
    }

    public boolean getCompleted(){
        return this.compleated;
    }

    public String getType(){
        return this.type;
    }

    public Date getDate(){
        return this.date;
    }
}
