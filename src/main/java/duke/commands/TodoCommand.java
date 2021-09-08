package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.ui.TextUi;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        tasks.addTask("TODO", description);
        String response = TextUi.showTaskAdded(tasks);
        response += TextUi.showUpdatedNumberOfTasks(tasks);
        return response;
    }
}
