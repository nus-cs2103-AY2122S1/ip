package duke.command;

import java.util.Arrays;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles the adding of new task into Duke.
 */
public class CommandAdd extends Command {
    /**
     * Contains the String representation of task description
     */
    private final String[] input;
    /**
     * Contains the int representation of task type
     */
    private final int taskType;

    /**
     * Constructor for the AddCommand.
     *
     * @param input The String representation of task description.
     * @param type  The type of task.
     */
    public CommandAdd(int type, String... input) {
        this.taskType = type;
        this.input = input;
    }

    /**
     * Adds the corresponding task into Duke.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui       The Ui of Duke.
     * @param storage  The Storage of Duke.
     * @return String from UI.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.add(taskType, input);
        return ui.displayAddUi(taskList, task);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof CommandAdd) {
            CommandAdd o = (CommandAdd) obj;
            return this.taskType == o.taskType && Arrays.equals(this.input, o.input);
        }

        return false;
    }
}
