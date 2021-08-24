package command;
import task.*;
import duke.*;

public class ExitCommand extends Command {

    /**
     * Saves taskList to file, then calls showResponse with exit message.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // perform saving of taskList to disk here
        storage.save(tasks);

        String response = respond();
        ui.showResponse(response);
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
