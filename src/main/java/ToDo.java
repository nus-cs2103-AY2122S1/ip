import java.lang.String;

public class ToDo extends Task{

    private final String TYPE = "T";

    /**
     * Constructor
     * @param taskTitle
     */
    ToDo(String taskTitle){
        super(taskTitle);
    }

    /**
     * Returns the status of the task with its title
     * @return String task status (formatted)
     */
    @Override
    public String toString(){
        return String.format("[%s][%s] %s",
                this.TYPE,
                (this.isDone ? "X" : " "),
                this.taskTitle);
    }
}