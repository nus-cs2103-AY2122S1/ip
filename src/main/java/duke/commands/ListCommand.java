package duke.commands;

import duke.tasks.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.toString();
    }
}
