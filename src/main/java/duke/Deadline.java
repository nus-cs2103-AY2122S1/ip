package duke;

import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate ddl;

    public Deadline(String taskTitle, LocalDate ddl) {
        super(taskTitle);
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + ddl.getMonth().toString() + " "  +
                ddl.getDayOfMonth() + " " + ddl.getYear() + ")";
    }
}
