package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class ListCommand extends Command{

    private static final String TASK_MSG = "Here are your tasks:";

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        String message = TASK_MSG + "\n" + tasks.toString();

        ui.printResponse(message);

        return true;
    }
}