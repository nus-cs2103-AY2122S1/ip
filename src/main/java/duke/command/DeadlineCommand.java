package duke.command;

import duke.Deadline;
import duke.DukeException;
import duke.TaskList;

/**
 * The command to add a Deadline typed task.
 */
public class DeadlineCommand extends Command {

    private String description;
    private String date;
    private TaskList taskList;

    public DeadlineCommand(String description, String date, TaskList taskList) {
        this.taskList = taskList;
        this.date = date;
        this.description = description;
    }

    @Override
    public String execute() throws DukeException {
        Deadline deadline = new Deadline(description, date);
        return taskList.addItem(deadline);
    }
}
