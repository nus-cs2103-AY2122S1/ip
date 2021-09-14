package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

/**
 * Represents a todo command. A <code>ToDoCommand</code> describes
 * the action to be executed when a user input a todo task description.
 */
public class ToDoCommand extends Command {
    private static final String ADD_MESSAGE = "Got it. I've added this task:\n";
    private Task task;
    private Storage storage;

    /**
     * Public constructor for ToDoCommand
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The tasklist to be updated.
     * @param description The description of the task.
     * @param storage The storage to handle modifications to the file.
     */
    public ToDoCommand(Ui ui, TaskList taskList,
                       String description, Storage storage) {
        super(ui, taskList);
        task = new ToDo(description);
        this.storage = storage;
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "todo <description>  |"
                + " add a todo task to your list with the given description";
    }

    /**
     * Check if the given command alters the task list.
     *
     * @return true if it updates the task list.
     */
    @Override
    public boolean isUpdatesTaskList() {
        return true;
    }

    /**
     * Adds the ToDo task to the task list.
     */
    @Override
    public String execute() {
        taskList = taskList.add(task);
        storage.addTaskToFile(task);

        return String.format("%s\n%s\n%s",
                ADD_MESSAGE, task, taskList.status());
    }

}

