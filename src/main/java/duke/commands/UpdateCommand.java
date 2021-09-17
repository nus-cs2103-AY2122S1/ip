package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


public class UpdateCommand extends Command {
    private String commands;

    public UpdateCommand(String commands) {
        this.commands = commands;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String response;
        if (commands.matches("\\d+")) {
            Task item = tasks.getItem(Integer.parseInt(commands) - 1);
            if (item != null) {
                response = item.completeItem();
                storage.updateFile(tasks);
            } else {
                throw new DukeException("☹ OOPS!!! Input a valid index");
            }
        } else {
            throw new DukeException("☹ OOPS!!! Input a valid index");
        }
        return response;
    }
}
