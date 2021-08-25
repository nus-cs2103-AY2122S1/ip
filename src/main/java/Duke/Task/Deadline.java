package Duke.Task;

import Duke.Main.DukeException;
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
     * @throws DukeException if there is no timing indicated or if the
     *                       date format entered is incorrect
     */
    public Deadline(String taskName) {
        super(taskName.split("/by")[0].trim());
        if (!taskName.contains("/by")) {
            throw new DukeException("", DukeException.TYPE.INCOMPLETE);
        }
        String[] divide = taskName.split("/by");
        this.taskDescription = divide[0].trim();
        String taskTime = divide[1].trim();
        try {
            this.date = Parser.convert(LocalDate.parse(taskTime));
        } catch (DateTimeParseException e) {
            throw new DukeException("", DukeException.TYPE.INVALID_FORMAT);
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

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public String getDate() {
        return this.date;
    }
}
