package duke.command;

import duke.misc.TaskList;
import duke.misc.Ui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * AddDeadlineCommand class which represents a command to add a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private boolean isBye = false;
    private String[] args;

    /**
     * Constructor for AddDeadlineCommand class.
     *
     * @param args Parsed the input that contains type and deadline of task.
     */
    public AddDeadlineCommand(String[] args) {
        this.args = args;
    }

    /**
     * Executes the operation to add the Deadline task.
     *
     * @param tl The TaskList which the task is added to.
     * @return String to notify user of successful command execution.
     */
    public String execute(TaskList tl) {
        assert args.length == 3;
        Task task = new Deadline(args[0], args[1], args[2]);
        return Ui.ADD_MSG + tl.addTask(task);
    }

    public boolean getIsBye() {
        return isBye;
    }
}
