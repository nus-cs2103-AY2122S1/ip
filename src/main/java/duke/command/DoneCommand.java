package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A command that changes the selected task status to 'done'.
 */
public class DoneCommand implements Command {

    private int startOfString;

    public DoneCommand(int startOfString) {
        this.startOfString = startOfString;
    }

    /**
     * Executes a command that changes the task in the list to done as well as change the item status to
     * 'done' in storage. Ui will output the task that has been changed to done afterwards.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @return A message that says that a task has been marked as done.
     * @throws DukeException catches an error when there is an error in input.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task taskToChange = taskList.retrieveTask(startOfString);
        storage.changeDone(taskToChange);
        return ui.markAsDone(taskToChange);
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
        if (!(obj instanceof DoneCommand)) {
            return false;
        }
        DoneCommand other = (DoneCommand) obj;
        return startOfString == other.startOfString;
    }
}
