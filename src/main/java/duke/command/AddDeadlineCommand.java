package duke.command;

import java.io.IOException;

import duke.misc.TaskList;
import duke.misc.Ui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * AddDeadlineCommand class which represents a command to add a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final boolean isBye;
    private final String[] args;

    /**
     * Constructor for AddDeadlineCommand class.
     *
     * @param args Parsed input that contains type, date and time of task.
     */
    public AddDeadlineCommand(String[] args) {
        this.args = args;
        isBye = false;
    }

    /**
     * Executes the operation to add the Deadline task.
     *
     * @param tl The current TaskList.
     * @return String to notify user of successful command execution.
     * @throws IOException In case of invalid file directory.
     */
    public String execute(TaskList tl) throws IOException {
        assert args.length == 3;
        Task task = new Deadline(args[0], args[1], args[2]);
        return Ui.ADD_MSG
                + tl.addTask(task)
                + "Current tasks count: "
                + tl.getSize()
                + "\n";
    }

    public boolean getIsBye() {
        return isBye;
    }
}
