package task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Todo extends Task {

    /**
     * A public constructor to initialize the task.
     *
     * @param description The given description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A public method to generate the type icon of the task.
     *
     * @return A String. The type icon of the task.
     */
    public String getTypeIcon() {
        return "T";
    }

    /**
     * A override toString() method.
     *
     * @return A String. The String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.getTypeIcon(), this.getStatusIcon(), this.getDescription());
    }

    /**
     * A method to get the date of the task.
     *
     * @return A LocalDate, it is 9999-12-31 by default
     * since this type of task do not have a date.
     */
    public LocalDate getDate() {
        return LocalDate.of(9999, 12, 30);
    }

    /**
     * A method to get the time of the task.
     *
     * @return A LocalTime, it is 2400 by default
     * since this type of task do not have a time.
     */
    public LocalTime getTime() {
        return LocalTime.of(23, 59);
    }
}
