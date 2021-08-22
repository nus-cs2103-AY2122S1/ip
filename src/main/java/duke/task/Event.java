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

            // Check if done status, description and time are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);
            boolean isTimeSame = this.at.equals(other.at);

            return (isDoneStatusSame && isDescriptionSame && isTimeSame);
        }
        return false;
    }
}
