package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

import java.io.IOException;

public class AddCommand extends Command {
    private final String type;
    private final String description;

    public AddCommand(String type, String description) {
        isExit = false;
        this.type = type;
        this.description = description;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (type.equals("todo")) {
            tasks.addTodoTask(description);
        } else if (type.equals("deadline")) {
            tasks.addDeadlineTask(description);
        } else {
            tasks.addEventtask(description);
        }
        ui.showAdd(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}
