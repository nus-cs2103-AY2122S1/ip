package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a function interface callable with parameters taskList, ui, storage.
 */
@FunctionalInterface
public interface TriConsumer {
    void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException, IOException, NumberFormatException, DateTimeParseException;
}
