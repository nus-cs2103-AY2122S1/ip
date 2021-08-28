package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.ui.Ui;
import duke.storage.Storage;

/**
 * The class for a user command that marks a task as done
 */
public class DoneCommand extends Command{
    int index;

    public DoneCommand(int index) {
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
        Task currTask = TaskList.get(index);
        currTask.markAsDone();
        ui.doneTask(currTask);
    }
}
