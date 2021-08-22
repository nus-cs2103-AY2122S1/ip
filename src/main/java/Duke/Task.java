package Duke;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

 /**
  * <pre>
  * Task object to hold the task,
  * this is to create each tasks framework.
  * </pre>
  * @param <String> actionName name of the action 
  * @param <boolean> compleated weather the task if completed 
  * @param <String> type action type 
  * @param <Date> date Date object of the task
  */
public class Task{
    String actionName;
    boolean compleated;
    String type;
    Date date;

    /**
     * Defult constructor,
     * prase the date into spedific format.
     * @param actionName name of the action 
     * @param compleated weather the task if completed 
     * @param type action type 
     * @param date Date object of the task
     */
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

    /**
     * To mark the task as
     * as compleated. 
     */
    public void doneTask(){
        this.compleated = true;
    }

    /**
     * To return the name of the action. 
     * @return action name
     */    
    public String getActionName(){
        return this.actionName;
    }

    /**
     * To return the boolean of compleateness.
     * @return compleated boolean
     */        
    public boolean getCompleted(){
        return this.compleated;
    }

    /**
     * To return the type of the action. 
     * @return action type name
     */    
    public String getType(){
        return this.type;
    }

    /**
     * To return the date of the task. 
     * @return date of task
     */        
    public Date getDate(){
        return this.date;
    }

    /**
     * Defult to String.
     * @return String representation of task
     */       
    public String toString(){
        String check = "[ ]";
        if (this.compleated){
            check = "[X]";
        }
        return check + " " + this.actionName;
    }
}
