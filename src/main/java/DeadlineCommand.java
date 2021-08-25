import java.io.IOException;
import java.time.LocalDate;

/**
 * The DeadlineCommand class encapsulates the execution of the deadline command from the user.
 */
public class DeadlineCommand extends Command {
    /** The deadline command inputted by the user. */
    String fullCommand;

    /**
     * Constructor to intialise a DeadlineCommand.
     * @param fullCommand The deadline command inputted by the user.
     */
    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the response to the deadline command from the user.
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException, DukeException {
        try {
            if (fullCommand.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }

            String deadlineDescription = fullCommand.split(" ", 2)[1];
            int pos = deadlineDescription.indexOf("/");
            String description = deadlineDescription.substring(0, pos - 1);
            String by = deadlineDescription.substring(pos + 4);
            LocalDate byDate = LocalDate.parse(by);

            Deadline deadline = new Deadline(description, byDate);
            taskList.storeTask(deadline);
            storage.saveFile(taskList.getAllTasks());
            ui.showTaskAdded(deadline, taskList);

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
