package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An abstract class to represent add command operation
 */
public abstract class AddCommand extends Command {
    private String taskDescription;

    /**
     * Initializes the instance of AddCommand with task type and description.
     * @param type Type of task
     * @param taskDescription: Task description
     */
    public AddCommand(String type, String taskDescription) {
        super(type);
        this.taskDescription = taskDescription;
    }

    /**
     * Returns task description.
     * @return Task description
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * An abstract method to execute an AddCommand task.
     * The method will be implemented in the derived classes.
     * @param taskList List of tasks
     * @param ui An instance of a class to handle i/o operations
     * @param storage An instance of a class to read from and write to file
     */
    @Override
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
