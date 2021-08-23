package duke.command;

import duke.UI;
import duke.task.TaskList;

public class ListCommand extends Command {

    private final TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        System.out.println(
                UI.tabAndFormat(this.tasks.toString())
        );
    }
}
