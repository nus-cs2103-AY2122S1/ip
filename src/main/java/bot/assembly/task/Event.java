package bot.assembly.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task {

    private LocalDateTime time;

    /**
     * Constructor
     * @param tasktitle
     * @param time
     */
    public Event (String tasktitle, LocalDateTime time) {
        super(tasktitle, "E");
        this.time = time;
    }

    /**
     * Constructor
     * @param isDone
     * @param taskTitle
     * @param time
     */
    public Event (boolean isDone, String taskTitle, LocalDateTime time) {
        super(taskTitle, isDone, "E");
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
        return String.format("[%s][%s] %s (at: %s %s)",
                this.getTaskType(), (this.getIsDone() ? "X" : " "),
                this.getTaskTitle(),
                this.getDate(),
                this.getTime());
    }
}
