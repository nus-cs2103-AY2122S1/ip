package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class EndCommand extends Command {
    private String command;

    /**
     * Creates EndCommand object.
     *
     * @param command command given by user.
     */
    public EndCommand(String command) {
        assert command.startsWith("bye");
        this.command = command;
    }

    /**
     * Executes the Command accordingly.
     *
     * @param storage Storage to store changes in text file.
     * @param tasks Tasks compiled in a TaskList.
     * @return A String array containing output.
     */
    public String[] execute(Storage storage, TaskList tasks) {
        return Ui.end();
    }
}
