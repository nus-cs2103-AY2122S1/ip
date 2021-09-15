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

    /**
     * Constructor for date command.
     * @param description The description of the task.
     * @param date The deadline date.
     * @param taskList The list of tasks.
     */
    public DeadlineCommand(String description, String date, TaskList taskList) {
        this.taskList = taskList;
        this.date = date;
        this.description = description;
    }

    /**
     * Returns an output message after executing the deadline command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        Deadline deadline = new Deadline(description, date);
        return taskList.addItem(deadline);
    }
}
