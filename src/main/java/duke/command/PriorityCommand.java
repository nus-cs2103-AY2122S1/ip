package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class PriorityCommand extends Command {
    private int index;
    private String priority;

    /**
     * Class constructor that receives an index of the Task in the TaskList to be given a new priority.
     * @param index The index of the Task in the TaskList.
     */
    public PriorityCommand(int index, String priority) {
        super();
        this.index = index;
        this.priority = priority;
    }

    /**
     * Executes the priority command, which changes a Task's priority.
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @return String The message to be displayed on successful execution.
     * @throws DukeException On index out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskDone = tasks.getTask(index);
            taskDone.changePriority(priority);
            return ui.displayChangedPriorityMessage(taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\t List number out of range, please enter a valid number\n");
        }
    }
}
