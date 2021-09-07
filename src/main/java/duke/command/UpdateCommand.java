package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UpdateCommand extends Command {
    public enum UpdateType {TITLE, DATE, DATE_TITLE}

    UpdateType updateType;

    public UpdateCommand(UpdateType type, String taskNo, String description) {
        super(taskNo, description);
        this.updateType = type;
    }

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
