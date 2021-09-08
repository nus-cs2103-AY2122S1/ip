package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.ui.TextUi;


public class DeadlineCommand extends Command {
    private String description;
    private String by;


    public DeadlineCommand(String description, String by) throws DukeException {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        tasks.addTask("DEADLINE", description, by);
        String response = TextUi.showTaskAdded(tasks);
        response += TextUi.showUpdatedNumberOfTasks(tasks);
        return response;
    }
}
