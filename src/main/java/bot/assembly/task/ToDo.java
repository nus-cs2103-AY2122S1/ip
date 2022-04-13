package bot.assembly.task;

import java.time.LocalDateTime;

public class ToDo extends Task {

    /**
     * Constructor
     * @param taskTitle
     */
    public ToDo (String taskTitle) {
        super(taskTitle, "T");
    }

    public ToDo (boolean isDone, String taskTitle) {
        super(taskTitle, isDone, "T");
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    /**
     * Returns the status of the task with its title
     * @return String task status (formatted)
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.getTaskType(), (this.getIsDone() ? "X" : " "),
                this.getTaskTitle());
    }
}
