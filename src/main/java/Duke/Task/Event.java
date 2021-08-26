package Duke.Task;

import Duke.DukeException.DukeIncompleteException;
import Duke.DukeException.DukeInvalidFormatException;
import Duke.Main.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private String taskDescription;
    private String date;

    /**
     * Constructor of an Event Task
     * @param taskName contains the task description and task time that
     *                 is to be divided
     */
    public Event(String taskName) {
        super(taskName.split("/at")[0].trim());
        if (!taskName.contains("/at")) {
            throw new DukeIncompleteException();
        }
        String[] divide = taskName.split("/at");
        this.taskDescription = divide[0];
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

}
