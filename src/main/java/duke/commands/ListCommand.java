package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;

/**
 * The ListCommand class extends the Command class and is the Command that * edits a Task to the
 * TaskList.
 */
public class ListCommand extends Command {

    /** The constructor for the ListCommand object. */
    public ListCommand() {
        super(CommandType.LIST, false);
    }

    /**
     * The execute command that executes the necessary actions when issued with the List Command.
     *
     * @param tasks The TaskList to be added to
     * @param response The Ui object to interact with the user
     * @param storage The Storage object that stores the TaskList on the Local Machine
     */
    public String execute(TaskList tasks, Response response, Storage storage) {
        return response.showTaskList(tasks);
    }
}
