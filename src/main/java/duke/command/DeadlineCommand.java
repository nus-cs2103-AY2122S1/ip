package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

import java.io.FileNotFoundException;

public class DeadlineCommand extends Command {

    private String description;
    private String deadline;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException {
        tasks.addDeadline(description, deadline, storage, ui);
    }
}
