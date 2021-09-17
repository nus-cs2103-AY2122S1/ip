package duke.command;

import duke.util.TaskList;

/**
 * Class that represents the Command to add to the TaskList.
 *
 * @author Benedict Chua
 */
public class AddCommand extends Command {
    private TaskList tasks;
    private String task;
    private String taskType;

    /**
     * Constructor for AddCommand.
     *
     * @param tasks TaskList containing current tasks.
     * @param task The Task to be added to the TaskList.
     * @param taskType Type of Task to be added (ToDo, Deadline, Event).
     */
    public AddCommand(TaskList tasks, String task, String taskType) {
        assert taskType.matches("ToDo|Deadline|Event") : "Invalid taskType";

        this.tasks = tasks;
        this.task = task;
        this.taskType = taskType;
    }

    /**
     * {@inheritDoc}
     *
     * This executes the add task command.
     */
    @Override
    public String execute() {
        return this.tasks.addToList(this.task, this.taskType);
    }
}
