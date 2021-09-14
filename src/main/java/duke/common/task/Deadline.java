package duke.common.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.common.Duke;
import duke.common.enums.TaskField;

public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Task with a specified deadline.
     *
     * @param description description of task.
     * @param deadline deadline of task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            this.deadline = null;
            System.out.println("Deadline was not readable, saving as unspecified deadline");
        }
    }

    @Override
    public String update(TaskField taskField, String newItem) throws Duke.DukeException {
        if (taskField == TaskField.DESCRIPTION) {
            return super.update(taskField, newItem);
        }
        if (taskField != TaskField.DEADLINE) {
            throw new Duke.DukeException("Sorry, there does not seem to be such a field in this task.");
        }
        try {
            this.deadline = LocalDate.parse(newItem);
            return this.toString();
        } catch (DateTimeParseException e) {
            throw new Duke.DukeException("Sorry, new deadline could not be read.\n Aborting update.");
        }
    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[D]%s (by: %s)", stem, this.deadline == null
            ? "unspecified"
            : this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
