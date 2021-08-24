import java.lang.String;

public class Deadline extends Task{

    private String time;

    /**
     * Constructor
     * @param taskTitle
     * @param time
     */
    Deadline(String taskTitle, String time){
        super(taskTitle, "D");
        this.time = time;
    }

    Deadline (boolean isDone, String taskTitle, String time) {
        super (taskTitle, isDone, "D");
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    /**
     * Returns the status of the task with its title
     * @return String task status (formatted)
     */
    @Override
    public String toString(){
        return String.format("[%s][%s] %s (by: %s)",
                this.taskType,
                (this.isDone ? "X" : " "),
                this.taskTitle,
                this.time);
    }
}
