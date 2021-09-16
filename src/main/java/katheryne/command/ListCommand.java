package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;

public class ListCommand extends Command {
    /**
     * The constant name to refer to this command by
     */
    public static final String COMMAND = "LIST";

    ListCommand() throws KatheryneException {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        ui.say("Here's the list I've stored for you:");
        ui.listTasks(taskList);
    }
}
