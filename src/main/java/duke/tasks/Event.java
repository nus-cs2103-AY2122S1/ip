package duke.tasks;

import java.time.LocalDate;

public class Event extends Task {
    private static final String IDENTIFIER = "E";

    LocalDate time;

    public Event(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }

    public String getIdentifier() {
        return IDENTIFIER;
    }

    public String getDetailsWithDelimiter(String delimiter) {
        return String.format("%s%s%s", taskName, delimiter, time);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event e = (Event) o;
        return taskName.equals(e.taskName) && isDone == e.isDone &&
                time.equals(e.time);
    }
}