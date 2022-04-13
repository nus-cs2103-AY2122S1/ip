package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a task that is a deadline, and contains a timing that the task needs to be
 * done by.
 */
public class Deadline extends Task {
    private LocalDateTime timing;

    /**
     * Constructor.
     *
     * @param description description of the task
     * @throws DukeException an exception that occurs due user faults
     */
    public Deadline(String description) throws DukeException {
        super(description);
        if (description.strip().equals("")) {
            throw new DukeException("Your Deadline cannot be empty :(");
        }

        int index = description.indexOf("/by");
        if (index == -1) {
            throw new DukeException("Please use the correct format! deadline <name> /by <date-time>");
        }
        timing = LocalDateTime.parse(description.substring(index + 3).strip(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        this.description = description.substring(0, index);
    }

    /**
     * Writes to a file using a FileWriter.
     *
     * @param myWriter the given FileWriter
     * @throws IOException ...
     */
    public void writeToFile(FileWriter myWriter) throws IOException {
        myWriter.write("deadline" + description + "/by "
                + timing.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + timing.format(DateTimeFormatter.ofPattern("MMM d yyy HH:mm")) + ")";
    }
}
