import java.lang.String;
import java.time.LocalDate;

public class Event extends Task{

    private LocalDate time;

    /**
     * Constructor
     * @param tasktitle
     * @param time
     */
    Event (String tasktitle, LocalDate time) {
        super(tasktitle, "E");
        this.time = time;
    }

    Event (boolean isDone, String taskTitle, LocalDate time) {
        super(taskTitle, isDone, "E");
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
