import java.lang.String;


public class Deadline extends Task{

    private final String TYPE = "D";
    private String time;

    /**
     * Constructor
     * @param taskTitle
     * @param time
     */
    Deadline(String taskTitle, String time){
        super(taskTitle);
        this.time = time;
    }

    /**
     * Returns the status of the task with its title
     * @return String task status (formatted)
     */
    @Override
    public String toString(){
        return String.format("[%s][%s] %s (by: %s)",
                this.TYPE,
                (this.isDone ? "X" : " "),
                this.taskTitle,
                this.time);
    }
}
