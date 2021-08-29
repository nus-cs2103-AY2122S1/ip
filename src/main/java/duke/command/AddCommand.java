package duke.command;

import duke.storage.Storage;
//
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
//
import duke.ui.Ui;

/**
 * Represents the class to specify how to add command.
 */
public class AddCommand extends Command {
    private String response;
    private int type;

    /**
     * Adds the command.
     *
     * @param response The content of user input.
     * @param type The type of adding command.
     */
    public AddCommand(String response, int type) {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Task("");
        switch (type) {
        case 1:
            task = new Todo(response.substring(5));
            break;
        case 2:
            String[] parts2 = response.substring(9).split(" /by ");
            String content2 = parts2[0];
            String time2 = parts2[1];
            task = new Deadline(content2, time2);
            break;
        case 3:
            String[] parts3 = response.substring(6).split(" /at ");
            String content3 = parts3[0];
            String time3 = parts3[1];
            task = new Event(content3, time3);
            break;
        default:
            break;
        }
        tasks.addElement(task);
        String taskString = task.toString();
        storage.store(taskString);
        ui.showTasks(taskString, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
