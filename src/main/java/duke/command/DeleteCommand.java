package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * The class for a user command that deletes a task from the list
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Method that checks if the bot needs to exit
     * @return whether the bot should exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Method that executes the parsing of user input and the message
     * to print to the command line
     * @param taskList the current list of tasks
     * @param ui the ui that interacts with the user
     * @param storage the place where the list of tasks will be stored
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.get(this.index);
        taskList.deleteTask(this.index);
        ui.deleteTask(task);
    }
}
