package duke.command;

import duke.misc.TaskList;
import duke.misc.Ui;
import duke.task.Event;
import duke.task.Task;

/**
 * AddEventCommand class which represents the command to add an Event task.
 */
public class AddEventCommand extends Command {
    private boolean isBye = false;
    private String[] args;

    /**
     * Constructor for AddEventCommand class.
     *
     * @param args Parsed input that contains the type and deadline of task.
     */
    public AddEventCommand(String[] args) {
        this.args = args;
    }

    /**
     * Executes the operation to add the Event task.
     *
     * @param tl The TaskList which the task is added to.
     * @return String to notify user of successful command execution.
     */
    public String execute(TaskList tl) {
        assert args.length == 3;
        Task task = new Event(args[0], args[1], args[2]);
        return Ui.ADD_MSG + tl.addTask(task);
    }

    public boolean getIsBye() {
        return isBye;
    }
}
