package duke.task.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Deadline;

/**
 * Class to execute the deadline command
 */
public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String description;
    private String at;

    /**
     * Instantiates a class object.
     *
     * @param taskList the list of tasks
     * @param description the description of the event
     * @param at the time of the event
     */
    public DeadlineCommand(TaskList taskList, String description, String at) {
        this.taskList = taskList;
        this.description = description;
        this.at = at;
    }

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        Deadline deadline = new Deadline(description, at);
        return taskList.addItem(deadline);
    }
}
