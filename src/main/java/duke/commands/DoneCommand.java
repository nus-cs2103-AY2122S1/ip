package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;
import duke.exceptions.DoneOutOfBoundsException;
import duke.exceptions.WrongDoneFormatException;

/**
 * Class that specifies the properties
 * of a done command.
 */
public class DoneCommand extends Command {

    /**
     * Calls parent class to initialise the
     * done command with description "done"
     * followed by integer.
     * @param desc String description of
     * done command.
     */
    public DoneCommand(String desc) {
        super(desc);
    }

    /**
     * Indicates whether the program should
     * stop running, and in this is case no.
     * @return boolean that this command
     * should exit the application or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * marks selected tasks as done and return all tasks.
     * @param tasks TaskList to update change given by user.
     * @param ui Ui class to display messages to user.
     * @param storage Storage updates each time a command make changes
     * to the existing stored tasks.
     * @return String of all tasks with the task
     * updated to done as [X].
     * @throws WrongDoneFormatException if done
     * command is given more than 2 fields.
     * @throws DoneOutOfBoundsException if user gives an
     * integer greater than the number of tasks
     * stored.
     */
    @Override
    public String execute(
            TaskList tasks, Ui ui,
            Storage storage) throws WrongDoneFormatException,
            DoneOutOfBoundsException {
        String[] instructions = this.commandDescription.split(" ");
        if (instructions.length != 2) {
            throw new WrongDoneFormatException();
        }
        int taskNumber = Integer.valueOf(instructions[1]);
        Task completedTask = tasks.markTaskAsDone(taskNumber);
        storage.updateStorageList(tasks.getTaskList());
        return ui.displayCompletedMessage(completedTask.toString());
    }
}
