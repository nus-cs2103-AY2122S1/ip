package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class FindByDescriptionCommand extends Command{

    String searchPhrase;
    TaskList tasks;
    Ui ui;

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
