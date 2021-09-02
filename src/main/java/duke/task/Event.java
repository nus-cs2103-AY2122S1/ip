package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeInvalidFormatException;
import duke.exceptions.DukeWrongCommandException;
import duke.main.Parser;

public class Event extends Task {

    private final String date;

    /**
     * Constructor of an Event Task
     * @param taskName contains the task description and task time that
     *                 is to be divided
     */
    public Event(String taskName) {
        super(taskName.split("/at")[0].trim());
        if (!taskName.contains("/at")) {
            if (taskName.contains("/by")) {
                throw new DukeWrongCommandException("Deadline");
            } else {
                throw new DukeWrongCommandException("Todo");
            }
        }
        String[] divide = taskName.split("/at");
        String taskTime = divide[1].trim();
        try {
            this.date = Parser.convert(LocalDate.parse(taskTime));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidFormatException();
        }
    }

    /**
     * Save task description to file on the initial date format
     * @return a String that represents user initial input
     */
    @Override
    public String save() {
        return this.toString().replaceAll(date, Parser.reverse(date));
    }

    /**
     * String representation of a Task
     * @return String of Task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at " + date + ")";
    }

    @Override
    public String getDate() {
        return this.date;
    }
}
