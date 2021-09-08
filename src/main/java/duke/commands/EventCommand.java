package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.TaskType;
import duke.ui.TextUi;

import java.util.Map;

public class EventCommand extends Command {
    private String description;
    private String by;

    protected EventCommand(Map<String, String> command) throws DukeException {
        this.description = command.get("description");
        this.by = command.get("time");
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        tasks.addTask(TaskType.EVENT, description, by);
        String response = TextUi.showTaskAdded(tasks);
        response += TextUi.showUpdatedNumberOfTasks(tasks);
        return response;
    }
}
