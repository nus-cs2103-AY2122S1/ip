import java.lang.String;
import java.time.LocalDate;

public class Deadline extends Task{

    private LocalDate time;

    /**
     * Constructor
     * @param taskTitle
     * @param time
     */
    Deadline(String taskTitle, LocalDate time){
        super(taskTitle, "D");
        this.time = time;
    }

    Deadline (boolean isDone, String taskTitle, LocalDate time) {
        super (taskTitle, isDone, "D");
        this.time = time;
    }

    public LocalDate getTime() {
        return this.time;
    }

    /**
     * Returns the status of the task with its title
     * @return String task status (formatted)
     */
    @Override
    public String toString(){
        return String.format("[%s][%s] %s (at: %s)",
                this.getTaskType(),
                (this.isDone ? "X" : " "),
                this.getTaskTitle(),
                this.getTime().toString());
    }
}
