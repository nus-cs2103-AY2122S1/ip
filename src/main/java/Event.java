import java.lang.String;


public class Event extends Task{

    private final String TYPE = "E";
    private String time;

    /**
     * Constructor
     * @param tasktitle
     * @param time
     */
    Event(String tasktitle, String time){
        super(tasktitle);
        this.time = time;
    }

    /**
     * Returns the status of the task with its title
     * @return String task status (formatted)
     */
    @Override
    public String toString(){
        return String.format("[%s][%s] %s (at: %s)",
                this.TYPE,
                (this.isDone ? "X" : " "),
                this.taskTitle,
                this.time);
    }
}
