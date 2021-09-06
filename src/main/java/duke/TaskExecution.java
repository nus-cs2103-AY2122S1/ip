package duke;

/**
 * This functional interface takes in a TaskList, and performs operations on this TaskList.
 */
public interface TaskExecution {
    String execute(TaskList task, MainStorage mainStorage, ArchiveStorage archiveStorage) throws DukeException;
}
