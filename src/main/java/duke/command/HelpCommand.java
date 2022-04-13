package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to display the help message.
 */
public class HelpCommand extends Command {

    /**
     * Displays the help message.
     * @param tasks Optional, nullable.
     * @param ui Ui to display help message to.
     * @param storage Optional, nullable.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(getMessage(null));
    }

    /**
     * Returns the message to be displayed while performing the task.
     *
     * @param tasks TaskList of current tasks.
     * @return Message to display to the user.
     */
    @Override
    public String getMessage(TaskList tasks) {
        String message = "Duk docs: \n"
                + "help: lists all commands\n"
                + "bye: Exits Duk\n"
                + "list: Lists all tasks saved\n"
                + "done {n}: Marks the n-th task as done\n"
                + "delete {n}: Deletes the n-th task\n"
                + "find {str}: Finds a task with description matching str\n"
                + "event {desc} /at {date}: Adds a new event task with\n"
                + "description desc occuring at date\n"
                + "deadline {desc} /by {date}: Adds a new deadline task with\n"
                + "description desc to be done by date\n"
                + "todo {desc}: Adds a new todo task with\n"
                + "description desc\n";
        return message;
    }
}
