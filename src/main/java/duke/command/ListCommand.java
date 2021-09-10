package duke.command;

import duke.Duke;
import duke.Storage;
import duke.task.TaskList;

/**
 * ListCommand class encapsulates command to list tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(Duke duke, TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + ". " + tasks.get(i) + "\n");
        }
        String message = sb.toString();
        duke.setResponse(message);
    }
}
