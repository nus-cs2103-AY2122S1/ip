<<<<<<< HEAD:src/main/java/tasks/Deadline.java
package tasks;

import tasks.Task;
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
>>>>>>> branch-Level-8:src/main/java/Deadline.java

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    @Override
    public String saveText() {
        return "D" + " / " + super.saveText() + " / " + by;
    }
}
