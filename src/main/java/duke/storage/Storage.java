package duke.storage;

import duke.constant.MessageType;
import duke.exception.DukeIoException;
import duke.listener.Message;
import duke.task.TaskList;

/**
 * The is the Storage class for task operations.
 */
public class Storage {
    private final Message message;

    /**
     * Constructs a Storage object.
     *
     * @param message Message interface.
     */
    public Storage(Message message) {
        this.message = message;
    }

    /**
     * Loads tasks.
     *
     * @param taskList TaskList object.
     */
    public void loadTasks(TaskList taskList) {
        try {
            taskList.loadTasksFromFile();
        } catch (DukeIoException e) {
            message.show(MessageType.ERROR, e.getMessage());
        }
    }

    /**
     * Saves tasks to file.
     *
     * @param taskList TaskList object.
     */
    public void saveTasksToFile(TaskList taskList) {
        try {
            taskList.saveTasksToFile();
        } catch (DukeIoException e) {
            message.show(MessageType.ERROR, e.getMessage());
        }
    }
}
