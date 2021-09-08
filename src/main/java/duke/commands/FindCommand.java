package duke.commands;

import duke.DukeException;
import duke.TaskList;

public class FindCommand extends Command {
    private String input;

    protected FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        // return textui show tasks instead
        return tasks.findTask(input);
    }
}
