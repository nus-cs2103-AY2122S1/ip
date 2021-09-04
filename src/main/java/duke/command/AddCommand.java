package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents the class to specify how to add command.
 */
public class AddCommand extends Command {
    private String response;
    private Operation type;

    /**
     * Adds the command.
     *
     * @param response The content of user input.
     * @param type The type of adding command.
     */
    public AddCommand(String response, Operation type) {
        this.response = response;
        this.type = type;
    }

    /**
     * Shows the task just be added.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = new Task("");
        switch (type) {
        case TODO:
            task = new Todo(response.substring(5));
            break;
        case DEADLINE:
            String[] deadlineParts = response.substring(9).split(" /by ");
            String deadlineContent = deadlineParts[0];
            String deadlineTime = deadlineParts[1];
            task = new Deadline(deadlineContent, deadlineTime);
            break;
        case EVENT:
            String[] eventParts = response.substring(6).split(" /at ");
            String eventContent = eventParts[0];
            String eventTime = eventParts[1];
            task = new Event(eventContent, eventTime);
            break;
        default:
            break;
        }
        tasks.addElement(task);
        String taskString = task.toString();
        storage.store(taskString);
        return ui.showTasks(taskString, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
