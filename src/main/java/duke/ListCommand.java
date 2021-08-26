package duke;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList();
        int point = 0;
        while (point < tasks.size()) {
            Task temp = tasks.get(point);
            ui.listNumber(point, temp);
            point++;
        }
        ui.numberOfTasks(tasks);
    }
}
