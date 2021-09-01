package bot.assembly.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Deadline extends Task {

    private LocalDateTime time;

    /**
     * Constructor
     * @param taskTitle
     * @param time
     */
    public Deadline(String taskTitle, LocalDateTime time) {
        super(taskTitle, "D");
        this.time = time;
    }

    /**
     * Constructor
     * @param isDone
     * @param taskTitle
     * @param time
     */
    public Deadline (boolean isDone, String taskTitle, LocalDateTime time) {
        super (taskTitle, isDone, "D");
        this.time = time;
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.time;
    }

    public LocalDate getDate() {
        return this.time.toLocalDate();
    }

    public LocalTime getTime() {
        return this.time.toLocalTime();
    }

    /**
     * Returns the status of the task with its title
     * @return String task status (formatted)
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s %s)",
                this.getTaskType(), (this.getIsDone() ? "X" : " "),
                this.getTaskTitle(),
                this.getDate(),
                this.getTime());
    }
}
