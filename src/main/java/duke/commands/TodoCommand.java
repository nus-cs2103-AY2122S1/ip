package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.TaskType;
import duke.ui.TextUi;

import java.util.Map;

public class TodoCommand extends Command {
    private String description;

    protected TodoCommand(Map<String, String> command) throws DukeException {
        this.description = command.get("description");
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        tasks.addTask(TaskType.TODO, description);
        String response = TextUi.showTaskAdded(tasks);
        response += TextUi.showUpdatedNumberOfTasks(tasks);
        return response;
    }
}
