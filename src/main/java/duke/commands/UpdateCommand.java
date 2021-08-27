package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;

public class UpdateCommand extends Command {
    private String commands;

    public UpdateCommand(String commands) {
        this.commands = commands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (commands.matches("\\d+")) {
            Task item = tasks.getItem(Integer.parseInt(commands) - 1);
            if (item != null) {
                ui.printOutput(item.completeItem());
                storage.updateFile(tasks);
            } else {
                throw new DukeException("☹ OOPS!!! Input a valid index");
            }
        } else {
            throw new DukeException("☹ OOPS!!! Input a valid index");
        }
    }
    
}
