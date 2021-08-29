package duke.command;

import java.io.FileNotFoundException;

import duke.ResponseLogic;
import duke.Storage;
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
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) throws FileNotFoundException {
        return tasks.addDeadline(description, deadline, storage, responseLogic);
    }
}
