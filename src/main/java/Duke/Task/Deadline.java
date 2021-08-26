package Duke.Task;

import Duke.DukeException.DukeIncompleteException;
import Duke.DukeException.DukeInvalidFormatException;
import Duke.Main.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String taskDescription;
    private String date;

    /**
     * Constructor for a Deadline Task
     * @param taskName description of the task, to be divided into the
     *                 name and the time of task
     */
    public Deadline(String taskName) {
        super(taskName.split("/by")[0].trim());
        if (!taskName.contains("/by")) {
            throw new DukeIncompleteException();
        }
        String[] divide = taskName.split("/by");
        this.taskDescription = divide[0].trim();
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
        return "[D]" + super.toString() + " (by " + date + ")";
    }

}
