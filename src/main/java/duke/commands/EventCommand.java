package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.ui.TextUi;

public class EventCommand extends Command {
    private String description;
    private String at;

    public EventCommand(String description, String at) throws DukeException {
        this.description = description;
        this.at = at;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        tasks.addTask("EVENT", description, at);
        String response = TextUi.showTaskAdded(tasks);
        response += TextUi.showUpdatedNumberOfTasks(tasks);
        return response;
    }
}
