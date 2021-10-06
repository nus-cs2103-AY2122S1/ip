package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * This class represents a {@code UpdateCommand}. User input for a
 * {@code UpdateCommand} starts with "update".
 *
 * @author Elizabeth Chow
 */
public class UpdateCommand extends Command {
    public enum UpdateType { TITLE, DATE, DATE_TITLE }

    private UpdateType updateType;

    /**
     * Constructor for a {@code UpdateCommand}
     *
     * @param type        Type of update
     * @param taskNo      String representation of the task number.
     * @param description Either a new date or description of a {@code Task}
     */
    public UpdateCommand(UpdateType type, String taskNo, String description) {
        super(taskNo, description);
        this.updateType = type;
    }

    /**
     * Constructor for a {@code UpdateCommand}
     *
     * @param type        Type of update
     * @param taskNo      String representation of the task number.
     * @param description New description of a {@code Task}
     * @param deadline    New date of a {@code Task}
     */
    public UpdateCommand(UpdateType type, String taskNo, String description, String deadline) {
        super(taskNo, description, deadline);
        this.updateType = type;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;
        switch (updateType) {
        case TITLE:
            task = tasks.updateTitle(Integer.parseInt(args[0]), args[1]);
            break;
        case DATE:
            task = tasks.updateDate(Integer.parseInt(args[0]), args[1]);
            break;
        case DATE_TITLE:
            task = tasks.updateDateTitle(Integer.parseInt(args[0]), args[1], args[2]);
            break;
        default:
            throw new DukeException("Unknown update type!");
        }
        storage.writeToFile(tasks);
        return ui.showUpdatedTask(task);
    }
}
