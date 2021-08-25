<<<<<<< HEAD:src/main/java/tasks/Event.java
package tasks;

import tasks.Task;
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
>>>>>>> branch-Level-8:src/main/java/Event.java

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() 
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                + ")";
    }

    @Override
    public String saveText() {
        return "E" + " / " + super.saveText() + " / " + at;
    }
}
