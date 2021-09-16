package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class ListCommand extends Command {
    private String command;

    /**
     * Creates ListCommand object.
     *
     * @param command command given by user.
     */
    public ListCommand(String command) {
        assert command.startsWith("list");
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
        return tasks.printList(command);
    }
}
