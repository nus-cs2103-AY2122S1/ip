package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.commands.TaskType;
import duke.DukeException;

public class FindCommand extends Command{
    private TaskType type;
    private String commands;

    public FindCommand(TaskType type, String commands) {
        this.type = type;
        this.commands = commands;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String response = "";
        switch (type) {
        case FIND_BY_DATE: {

            Task[] tasksOnDate = tasks.tasksOnDate(commands);
            for (Task task : tasksOnDate) {
                response += task + "\n";
            }
            break;
        }
        case FIND: {
            Task[] searchResults = tasks.findByKeyword(commands);
            for (Task task : searchResults) {
                response += task + "\n";
            }
            break;
        }
        default: {
            break;
        }
        }
        return response;
    }
}
