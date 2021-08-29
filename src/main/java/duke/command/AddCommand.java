package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class AddCommand extends Command {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(input);
    }

    public boolean isExit() {
        return false;
    }
}
