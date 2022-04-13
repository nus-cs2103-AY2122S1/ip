package duke;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents a task that is an event, and contains a description of the timing of the event.
 */
public class Event extends Task {

    private String timing;

    /**
     * Constructor.
     *
     * @param description description of the task
     * @throws DukeException an exception that occurs due user faults
     */
    public Event(String description) throws DukeException {
        super(description);
        if (description.strip().equals("")) {
            throw new DukeException("Your Event cannot be empty :(");
        }
        int index = description.indexOf("/at");
        if (index == -1) {
            throw new DukeException("Please use the correct format! event <name> /at <text>");
        }
        timing = description.substring(index + 3);
        this.description = description.substring(0, index - 1);

    }

    /**
     * Writes to a file using a FileWriter.
     *
     * @param myWriter the given FileWriter
     * @throws IOException ...
     */
    public void writeToFile(FileWriter myWriter) throws IOException {
        myWriter.write("event" + description + " /at" + timing);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + timing + ")";
    }

}
