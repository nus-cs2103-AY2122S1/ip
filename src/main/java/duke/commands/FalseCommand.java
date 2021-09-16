package duke.commands;

import duke.tasks.TaskList;
import duke.util.Ui;

public class FalseCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return "OOPS! Invalid command input! Please try again :)";
    }
}
