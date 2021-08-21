package duke.task;

import duke.DukeException;
import duke.Parser;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public LocalDate getTime() {
        return this.at;
    }

    @Override
    public String toString() {
        try {
            return "[E]" + super.toString() + " (at: " + Parser.parseLocalDate(this.at) + ")";
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return (this.isDone == other.getIsDone() &&
                    this.description.equals(other.getDescription()) &&
                    this.at.equals(other.getTime()));
        }
        return false;
    }
}
