<<<<<<< Updated upstream:src/main/java/Event.java
public class Event extends Task {

    private String date;
=======
package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime date;
>>>>>>> Stashed changes:src/main/java/duke/Event.java

    public Event(String description, String date) {
        super(description);

<<<<<<< Updated upstream:src/main/java/Event.java
=======
        DateTimeFormatter scanned = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.date = LocalDateTime.parse(date, scanned);
    }

>>>>>>> Stashed changes:src/main/java/duke/Event.java
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");
        return "[E]" + super.toString() + " (at: " + this.date.format(dateFormat) + ")";
    }
}
