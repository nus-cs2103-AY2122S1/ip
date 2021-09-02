package duke.command;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;
import duke.task.Event;

/**
 * Command to create Event tasks.
 */
public class EventCommand extends Command {
    private String input;
    private String taskDesc;
    private String eventDate;

    /**
     * Constructor for EventCommand.
     *
     * @param input User input.
     */
    public EventCommand(String input) {
        this.input = input;
        this.taskDesc = input.replaceFirst("^event ", "").split(" /")[0];
        if (input.contains("/at")) {
            this.eventDate = input.substring(input.indexOf("/at") + 4);
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
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Event eTask = new Event(taskDesc, eventDate);
        ls.addTask(eTask);
        storage.rewriteFile(ls);
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
