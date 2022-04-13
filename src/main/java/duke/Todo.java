package duke;

import java.io.FileWriter;
import java.io.IOException;


/**
 * This class represents a task that is a todo.
 */
public class Todo extends Task {

    /**
     * Constructor.
     * @param description description of the task
     * @throws DukeException a DukeException
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.strip().equals("")) {
            throw new DukeException("Your todo cannot be empty :(");
        }
    }

    /**
     * Writes to a file using a FileWriter.
     * @param myWriter the given FileWriter
     * @throws IOException ...
     */
    public void writeToFile(FileWriter myWriter) throws IOException {
        myWriter.write("todo" + description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
