package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A class which encapsulates the command of
 * listing all the tasks stored in Task List.
 */
public class ListCommand extends Command {

    /**
     * A public constructor which calls the parent
     * constructor with the command.
     *
     * @param command The command inputted by the user.
     */
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Executes the list command and lists all the tasks
     * stored.
     *
     * @param tasks   The list of tasks stored so far.
     * @param ui      The Ui to deal with interactions with user.
     * @param storage The storage which saves and edits file content.
     * @return The string containing the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList();
        int point = 0;
        String taskString = "";
        while (point < tasks.size()) {
            Task temp = tasks.get(point);
            taskString += ui.listNumber(point, temp);
            point++;
        }
        return ui.showList() + System.lineSeparator()
                + taskString + ui.numberOfTasks(tasks);
    }
}
