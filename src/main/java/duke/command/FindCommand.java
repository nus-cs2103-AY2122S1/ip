package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String target;

    public FindCommand(String target) {
        this.target = target;
    }

    /**
     * Checks if the bot needs to exit
     *
     * @return whether the bot should exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the parsing of user input and the message
     * to print to the command line
     *
     * @param tasks the current list of tasks
     * @param ui the ui that interacts with the user
     * @param storage the place where the list of tasks will be stored
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listFoundTasks(tasks.searchList(target));
    }
}
