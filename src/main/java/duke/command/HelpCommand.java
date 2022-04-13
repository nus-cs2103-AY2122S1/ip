package duke.command;

import duke.Storage;
import duke.Ui;
import duke.notes.NotesList;
import duke.tasks.TaskList;

/**
 * Provides users with a manual for them to refer to for the commands.
 */
public class HelpCommand extends Command {
    private String command;

    /**
     * Instantiates an object of the HelpCommand class.
     *
     * @param command User's input.
     */
    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the action of printing the manual.
     *
     * @param tasks List of tasks stored in the task manager.
     * @param notes List of notes stored in the task manager.
     * @param ui User interface of the task manager.
     * @param storage Hard disk containing all the tasks and notes of the task manager.
     * @return Message to be printed on the user interface to guide users on the commands available.
     */
    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
        return ui.respondToHelp();
    }

    /**
     * Returns a boolean value indicating if user wants to exit the task manager.
     *
     * @return Boolean value.
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
