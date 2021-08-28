package duke.command;

import java.io.FileNotFoundException;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class DeadlineCommand extends Command {

    private String description;
    private String deadline;

    /**
     * Create a new DeadlineCommand object.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException {
        tasks.addDeadline(description, deadline, storage, ui);
    }
}
