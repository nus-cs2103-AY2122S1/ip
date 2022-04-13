package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A command that deletes a current task from the task list.
 */
public class DeleteCommand implements Command {

    private int startOfString;

    public DeleteCommand(int startOfString) {
        this.startOfString = startOfString;
    }

    /**
     * Executes a command to the task list to delete the current task. Removes the task from the memory
     * in storage, and outputs what task has been deleted from the list.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @return A message that tells the user that task has been deleted.
     * @throws DukeException catches an error where someone inputs something wrong.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskToDelete = taskList.deleteTask(startOfString);
        storage.deleteFromFile(taskToDelete);
        return ui.markAsDeleted(taskToDelete);
    }

    /**
     * A method that checks whether the current command will cause the program to exit or not.
     *
     * @return a boolean that prompts the program whether to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand other = (DeleteCommand) obj;
        return startOfString == other.startOfString;
    }
}
