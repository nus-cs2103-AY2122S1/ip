package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Command to create Event tasks.
 */
public class EventCommand extends Command {
    private String taskDesc = "";
    private String eventDate = "";

    /**
     * Constructor for EventCommand.
     *
     * @param input User input.
     */
    public EventCommand(String input) throws DukeException {
        this.taskDesc = input.replaceFirst("^event", "").split(" /")[0];
        if (input.contains("/at")) {
            this.eventDate = input.substring(input.indexOf("/at") + 4);
        } else {
            throw new DukeException("☹ OOPS!!! Please use the /at command to include the time of event.");
        }
    }

    /**
     * Creates a new Event object and adds it to the current list.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If user input is invalid.
     */
    @Override
    public String execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Event eTask = new Event(taskDesc, eventDate);
        ls.addTask(eTask);
        storage.rewriteFile(ls);
        return ui.addTaskToList(eTask, ls.getSize());
    }

    /**
     * Signals to the system that the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
