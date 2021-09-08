package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        return tasks.findTask(input);
    }
}
