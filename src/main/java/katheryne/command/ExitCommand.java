package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;

public class ExitCommand extends Command {
    /**
     * The constant name to refer to this command by
     */
    public static final String COMMAND = "BYE";

    ExitCommand() throws KatheryneException {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        // try to save the file
        try {
            storage.saveTasks(taskList, "tasks.json");
            ui.say("I'll save your tasks now.");
        } catch (KatheryneException e) {
            System.out.println(e.getMessage());
        }

        ui.stopRunning();
    }
}
