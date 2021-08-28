package duke;

import java.time.LocalDate;

/**
 * Represents a specific type of Task with an additional deadline(ddl) field.
 */
public class Deadline extends Task {
    private LocalDate ddl;

    /**
     * The constructor for a Deadline Object.
     */
    public Deadline(String taskTitle, LocalDate ddl) {
        super(taskTitle);
        this.ddl = ddl;
    }

    public LocalDate getDdl() {
        return ddl;
    }

    /**
     * Customizes the string representation of a deadline object.
     *
     * @return string representation of a deadline in the form [D][{X}] {description} (by: {ddl})
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + ddl.getMonth().toString() + " "
                + ddl.getDayOfMonth() + " " + ddl.getYear() + ")";
    }


    /**
     * Two deadline objects are equal iff they are equal tasks and they have the same ddl.
     * */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Deadline) {
            Deadline anotherTask = (Deadline) another;
            return super.equals(another) && ddl.equals(anotherTask.getDdl());
        }
        return false;
    }
}
