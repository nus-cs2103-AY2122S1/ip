package duke.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.NoDescriptionException;
import duke.exceptions.UserInputError;

public class Event extends Task {

    protected LocalDate when;

    public Event(String description, String when, boolean done) throws UserInputError {
        super(description, Task.Type.EVENT, done);
        try {
            this.when = LocalDate.parse(when.trim());
        } catch (DateTimeException e){
            throw new NoDescriptionException("Wrong datetime format");
        }
    }

    private void DateParser(String when) throws UserInputError {
        try {
            this.when = LocalDate.parse(when.trim());
        } catch (DateTimeException e){
            throw new UserInputError("wrong datetime format");
        }
    }

    @Override
    public String taskToString() {
        return super.taskToString() + when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
