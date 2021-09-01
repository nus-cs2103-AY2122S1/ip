package duke;

import java.time.LocalDate;

/**
 * Represents a specific type of Task with an additional deadline field.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * The constructor for a Deadline Object.
     */
    public Deadline(String taskTitle, LocalDate deadline) {
        super(taskTitle);
        this.deadline = deadline;
    }

    public LocalDate getDdl() {
        return deadline;
    }

    /**
     * Customizes the string representation of a deadline object.
     *
     * @return string representation of a deadline in the form [D][{X}] {description} (by: {deadline})
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.getMonth().toString() + " "
                + deadline.getDayOfMonth() + " " + deadline.getYear() + ")";
    }


    /**
     * Two deadline objects are equal iff they are equal tasks and they have the same ddl.
     * */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Deadline) {
            Deadline anotherTask = (Deadline) another;
            return super.equals(another) && deadline.equals(anotherTask.getDdl());
        }
        return false;
    }
}
