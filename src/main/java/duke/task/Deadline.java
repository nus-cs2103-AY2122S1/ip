package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, boolean isDone, LocalDateTime by) {
        super(name, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    @Override
    public String getRecordString() {
        int done = this.isDone ? 1 : 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
        String record = String.format("D | %d | %s | %s", done, this.name, this.by.format(formatter));
        return record;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline dl = (Deadline) obj;
            return this.name.equals(dl.name) && this.by.equals(dl.by) && this.isDone == dl.isDone;
        }
        return false;
    }
}
