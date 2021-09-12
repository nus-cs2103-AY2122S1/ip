package duke.commands;

import duke.tasks.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return "Bye. Hope to see you again soon!";
    }
}
