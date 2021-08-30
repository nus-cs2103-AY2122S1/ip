package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class FindByDescriptionCommand extends Command {

    private String searchPhrase;
    private TaskList tasks;
    private Ui ui;

    /**
     * Finds a task by a matching line of text within its description.
     *
     * @param searchPhrase The text to be searched.
     * @param tasks The task list by which the text is to be searched from.
     * @param ui Ui to handle interactions.
     */
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
