package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * This class deals with the add task command.
 */
public class AddCommand extends Command {
    private TaskList myTasks;
    private String next;

    /**
     * Constructs a new AddCommand
     *
     * @param myTasks all tasks now
     * @param next instruction
     */
    public AddCommand(TaskList myTasks, String next) {
        this.myTasks = myTasks;
        this.next = next;
    }

    /**
     * Executes instructions according to the Command type (here is adding a task)
     */
    @Override
    public void execute() {
        try {
            myTasks.addTask(next);
        } catch (DukeException dukeException) {
            Ui.showError(dukeException);
        }
    }
}
