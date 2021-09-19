package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Message;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;

/**
 * Command to exit the Katheryne programme.
 */
public class ExitCommand extends Command {
    /**
     * The constant name to refer to this command by.
     */
    public static final String COMMAND = "BYE";

    ExitCommand() throws KatheryneException {
    }

    @Override public String getResponse(TaskList taskList, Storage storage) throws KatheryneException {
        try {
            storage.saveTasks(taskList, "tasks.json");
            return Message.goodbye();
        } catch (KatheryneException e) {
            return e.getMessage();
        }
    }

    /**
     * Quits the programme.
     *
     * @param taskList A container for tasks which contains Katheryne's tasks.
     * @param ui The Ui used for the user interface.
     * @param storage The storage object taking care of writing and reading the text file.
     * @throws KatheryneException
     */
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
