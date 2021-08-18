package duke.command;

import duke.exception.BadInputFormatException;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {
    public static ListCommand of(String content) throws BadInputFormatException {
        if (content.trim().length() > 0) {
            throw new BadInputFormatException();
        }
        return new ListCommand();
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.size() < 1) {
            ui.print("No tasks yet!");
            return;
        }
        ui.print("Here are the tasks in your list:");
        ui.print(tasks.toStringArray());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
