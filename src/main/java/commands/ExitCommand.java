package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class ExitCommand extends Command {
    private static final String EXIT_MSG = "Goodbye Courier!";

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return EXIT_MSG;
    }
}
