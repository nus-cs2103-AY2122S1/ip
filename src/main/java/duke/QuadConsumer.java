package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a function interface callable with parameters taskList, ui, storage, versionManager.
 */
@FunctionalInterface
public interface QuadConsumer {
    void execute(TaskList taskList, Ui ui, Storage storage, HistoryManager verManager)
            throws DukeException, IOException, NumberFormatException, DateTimeParseException;
}
