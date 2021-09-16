package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a deadline in the task list.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate deadline;

    /**
     * Creates the deadline.
     *
     * @param description the description of this task.
     * @param by the time that this deadline task is due by.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.deadline = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new DukeException("Bad date format. Please input as yyyy-mm-dd.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(FORMATTER) + ")";
    }

    /**
     * Formats the task into a string that is compliant with the save file format.
     *
     * @return a string representing the task to be saved in save file
     */
    public String saveData() {
        return "D | " + this.getStatusIcon() + " | " + description + " | " + deadline;
    }
}
