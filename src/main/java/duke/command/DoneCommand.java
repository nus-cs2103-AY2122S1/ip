package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.ui.Ui;
import duke.storage.Storage;

/**
 * The class for a user command that marks a task as done
 */
public class DoneCommand extends Command{
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Checks if the bot needs to exit
     *
     * @return whether the bot should exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the parsing of user input and the message
     * to print to the command line
     *
     * @param tasks the current list of tasks
     * @param ui the ui that interacts with the user
     * @param storage the place where the list of tasks will be stored
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task currTask = tasks.get(index);
        currTask.markAsDone();
        return ui.doneTask(currTask);
    }
}
