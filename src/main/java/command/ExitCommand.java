package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * Represents an ExitCommand.
 */
public class ExitCommand extends Command {

    /**
     * Saves taskList to file, then calls showResponse with exit message.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // perform saving of taskList to disk here
        storage.save(tasks);

        String response = respond();
        String result = ui.showResponse(response);

        return result;
    }

    /**
     * Returns true to indicate ExitCommand exits the program.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Returns exit message.
     *
     * @return Exit message.
     */
    public String respond() {
        String response = "Bye. Hope to see you again soon!";
        return response;
    }
}
