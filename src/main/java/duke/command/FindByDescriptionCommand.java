package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class FindByDescriptionCommand extends Command {

    private String searchPhrase;
    private TaskList tasks;
    private Ui ui;

    public FindByDescriptionCommand(String searchPhrase, TaskList tasks, Ui ui) {
        this.searchPhrase = searchPhrase;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public void execute() {
        TaskList foundTasks = tasks.findByDescription(searchPhrase);
        ui.findByDescription(foundTasks);
    }


}
