package duke;

/**
 * Represents a function interface callable with parameters taskList, ui, storage
 */
@FunctionalInterface
public interface TriConsumer {
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, Exception;
}
